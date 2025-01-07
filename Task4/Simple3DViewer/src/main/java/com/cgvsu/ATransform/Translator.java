package com.cgvsu.ATransform;

import com.cgvsu.math.Matrix4f;
import com.cgvsu.math.Vector3f;
import java.util.Objects;

public class Translator implements ATransform {
    private final Matrix4f translateMatrix;
    public Translator(float tx, float ty, float tz) {
        this.translateMatrix = new Matrix4f(new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        });
    }

    //конструктор для единичной матрицы (без сдвигов)
    public Translator() {
        this.translateMatrix = new Matrix4f(new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    @Override
    public Matrix4f getMatrix() {
        return translateMatrix;
    }

    @Override
    public Vector3f transform(Vector3f v) {
        //применяем сдвиг к вектору
        float newX = translateMatrix.mat[0][3] + v.x;
        float newY = translateMatrix.mat[1][3] + v.y;
        float newZ = translateMatrix.mat[2][3] + v.z;
        return new Vector3f(newX, newY, newZ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translator that = (Translator) o;
        return Objects.equals(translateMatrix, that.translateMatrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translateMatrix);
    }
}