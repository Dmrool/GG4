package com.cgvsu.render_engine;

import java.util.ArrayList;
import java.util.Arrays;

import com.cgvsu.math.Point2f;
import com.cgvsu.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import javax.vecmath.*;
import com.cgvsu.model.Model;
import javafx.scene.paint.Color;
import static com.cgvsu.render_engine.GraphicConveyor.*;

public class RenderEngine {

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Model mesh,
            final int width,
            final int height,
            final float scaleX,
            final float scaleY,
            final float scaleZ,
            final float rotateX,
            final float rotateY,
            final float rotateZ,
            final float translateX,
            final float translateY,
            final float translateZ) {

        com.cgvsu.math.Matrix4f modelMatrix = GraphicConveyor.rotateScaleTranslate(scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, translateX, translateY, translateZ);
        com.cgvsu.math.Matrix4f viewMatrix = camera.getViewMatrix();
        com.cgvsu.math.Matrix4f projectionMatrix = camera.getProjectionMatrix();

        com.cgvsu.math.Matrix4f modelViewProjectionMatrix = new com.cgvsu.math.Matrix4f(modelMatrix);
        modelViewProjectionMatrix = com.cgvsu.math.Matrix4f.multiply(modelViewProjectionMatrix, viewMatrix);
        modelViewProjectionMatrix = com.cgvsu.math.Matrix4f.multiply(modelViewProjectionMatrix, projectionMatrix);

        // Инициализация Z-буфера
        float[] zBuffer = new float[width * height];
        Arrays.fill(zBuffer, Float.MAX_VALUE);

        final int nPolygons = mesh.polygons.size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.polygons.get(polygonInd).getVertexIndices().size();

            ArrayList<com.cgvsu.math.Point2f> resultPoints = new ArrayList<>();
            ArrayList<Float> resultZ = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = mesh.vertices.get(mesh.polygons.get(polygonInd).getVertexIndices().get(vertexInPolygonInd));

                Vector3f vertexVecmath = new Vector3f(vertex.x, vertex.y, vertex.z);
                com.cgvsu.math.Point2f resultPoint = GraphicConveyor.vertexToPoint(GraphicConveyor.multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertexVecmath), width, height);
                resultPoints.add(resultPoint);

                // Сохранение Z-координаты
                resultZ.add(vertexVecmath.z);
            }

            // Рисование контура полигона с использованием Z-буфера
            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                drawLineWithZBuffer(graphicsContext, resultPoints.get(vertexInPolygonInd - 1), resultPoints.get(vertexInPolygonInd), resultZ.get(vertexInPolygonInd - 1), resultZ.get(vertexInPolygonInd), zBuffer, width, height);
            }

            if (nVerticesInPolygon > 0) {
                drawLineWithZBuffer(graphicsContext, resultPoints.get(nVerticesInPolygon - 1), resultPoints.get(0), resultZ.get(nVerticesInPolygon - 1), resultZ.get(0), zBuffer, width, height);
            }
        }
    }

    // Метод для отрисовки линий с использованием z-буфера
    private static void drawLineWithZBuffer(GraphicsContext graphicsContext, com.cgvsu.math.Point2f p1, Point2f p2, float z1, float z2, float[] zBuffer, int width, int height) {
        int x1 = (int) p1.x;
        int y1 = (int) p1.y;
        int x2 = (int) p2.x;
        int y2 = (int) p2.y;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (x1 >= 0 && x1 < width && y1 >= 0 && y1 < height) {
                int index = y1 * width + x1;
                float z = z1 + (x1 - p1.x) * (z2 - z1) / (p2.x - p1.x);
                if (z < zBuffer[index]) {
                    zBuffer[index] = z;
                    graphicsContext.getPixelWriter().setColor(x1, y1, Color.BLACK);
                }
            }

            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}