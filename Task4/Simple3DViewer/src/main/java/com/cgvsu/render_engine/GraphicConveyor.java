package com.cgvsu.render_engine;
import com.cgvsu.ATransform.Rotator;
import com.cgvsu.ATransform.Scaling;
import com.cgvsu.ATransform.Translator;
import com.cgvsu.math.Point2f;
import javax.vecmath.*;

public class GraphicConveyor {
    public static com.cgvsu.math.Matrix4f rotateScaleTranslate(float scaleX, float scaleY, float scaleZ, float rotateX, float rotateY, float rotateZ, float translateX, float translateY, float translateZ) {
        //матрица масштабирования
        Scaling scaling = new Scaling(scaleX, scaleY, scaleZ);
        com.cgvsu.math.Matrix4f scaleMatrix = scaling.getMatrix();

        //матрица вращения
        com.cgvsu.math.Matrix4f rotationMatrix = createRotationMatrix(rotateX, rotateY, rotateZ);

        //матрица перемещения
        Translator translator = new Translator(translateX, translateY, translateZ);
        com.cgvsu.math.Matrix4f translationMatrix = translator.getMatrix();

        com.cgvsu.math.Matrix4f modelMatrix = new com.cgvsu.math.Matrix4f(scaleMatrix);
        modelMatrix = com.cgvsu.math.Matrix4f.multiply(modelMatrix, rotationMatrix);
        modelMatrix = com.cgvsu.math.Matrix4f.multiply(modelMatrix, translationMatrix);

        return modelMatrix;
    }

    public static com.cgvsu.math.Matrix4f createRotationMatrix(float rotateX, float rotateY, float rotateZ) {
        //вращение вокруг оси X
        Rotator rotatorX = new Rotator(rotateX, Rotator.Axis.X);
        com.cgvsu.math.Matrix4f rotateXMatrix = rotatorX.getMatrix();

        //вращение вокруг оси Y
        Rotator rotatorY = new Rotator(rotateY, Rotator.Axis.Y);
        com.cgvsu.math.Matrix4f rotateYMatrix = rotatorY.getMatrix();

        //вращение вокруг оси Z
        Rotator rotatorZ = new Rotator(rotateZ, Rotator.Axis.Z);
        com.cgvsu.math.Matrix4f rotateZMatrix = rotatorZ.getMatrix();

        //комбинируем матрицы вращения
        com.cgvsu.math.Matrix4f rotationMatrix = new com.cgvsu.math.Matrix4f(rotateXMatrix);
        rotationMatrix = com.cgvsu.math.Matrix4f.multiply(rotationMatrix, rotateYMatrix);
        rotationMatrix = com.cgvsu.math.Matrix4f.multiply(rotationMatrix, rotateZMatrix);

        return rotationMatrix;
    }

    public static com.cgvsu.math.Matrix4f lookAt(com.cgvsu.math.Vector3f eye, com.cgvsu.math.Vector3f target) {
        return lookAt(eye, target, new com.cgvsu.math.Vector3f(0F, 1.0F, 0F));
    }

    public static com.cgvsu.math.Matrix4f lookAt(com.cgvsu.math.Vector3f eye, com.cgvsu.math.Vector3f target, com.cgvsu.math.Vector3f up) {
        com.cgvsu.math.Vector3f resultX = new com.cgvsu.math.Vector3f();
        com.cgvsu.math.Vector3f resultY = new com.cgvsu.math.Vector3f();
        com.cgvsu.math.Vector3f resultZ = new com.cgvsu.math.Vector3f();

        resultZ.sub(target, eye);
        resultX.cross(up, resultZ);
        resultY.cross(resultZ, resultX);

        resultX.normalize();
        resultY.normalize();
        resultZ.normalize();

        float[][] matrix = new float[][]{
                {resultX.x, resultY.x, resultZ.x, 0},
                {resultX.y, resultY.y, resultZ.y, 0},
                {resultX.z, resultY.z, resultZ.z, 0},
                {-resultX.dot(eye), -resultY.dot(eye), -resultZ.dot(eye), 1}
        };
        return new com.cgvsu.math.Matrix4f(matrix);
    }

    public static com.cgvsu.math.Matrix4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        com.cgvsu.math.Matrix4f result = new com.cgvsu.math.Matrix4f();
        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov * 0.5F)));
        result.mat[0][0] = tangentMinusOnDegree / aspectRatio;
        result.mat[1][1] = tangentMinusOnDegree;
        result.mat[2][2] = (farPlane + nearPlane) / (farPlane - nearPlane);
        result.mat[2][3] = 1.0F;
        result.mat[3][2] = 2 * (nearPlane * farPlane) / (nearPlane - farPlane);
        return result;
    }

    public static com.cgvsu.math.Vector3f multiplyMatrix4ByVector3(final com.cgvsu.math.Matrix4f matrix, final com.cgvsu.math.Vector3f vertex) {
        final float x = (vertex.x * matrix.mat[0][0]) + (vertex.y * matrix.mat[1][0]) + (vertex.z * matrix.mat[2][0]) + matrix.mat[3][0];
        final float y = (vertex.x * matrix.mat[0][1]) + (vertex.y * matrix.mat[1][1]) + (vertex.z * matrix.mat[2][1]) + matrix.mat[3][1];
        final float z = (vertex.x * matrix.mat[0][2]) + (vertex.y * matrix.mat[1][2]) + (vertex.z * matrix.mat[2][2]) + matrix.mat[3][2];
        final float w = (vertex.x * matrix.mat[0][3]) + (vertex.y * matrix.mat[1][3]) + (vertex.z * matrix.mat[2][3]) + matrix.mat[3][3];
        return new com.cgvsu.math.Vector3f(x / w, y / w, z / w);
    }

    public static com.cgvsu.math.Point2f vertexToPoint(final com.cgvsu.math.Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.x * width + width / 2.0F, -vertex.y * height + height / 2.0F);
    }
}