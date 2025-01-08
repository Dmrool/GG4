package com.cgvsu.ATransform;

import com.cgvsu.math.Matrix4f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTests {

    @Test
    public void testScaleMatrix() {
        Matrix4f expectedMatrix = new Matrix4f(new float[][]{
                {2, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 5, 0},
                {0, 0, 0, 1}
        });

        Scaling s = new Scaling(2, 3, 5);
        Assertions.assertTrue(expectedMatrix.equals(s.getMatrix()));

    }

    @Test
    public void testTranslateMatrix() {
        Matrix4f expectedMatrix = new Matrix4f(new float[][]{
                {1, 0, 0, 5},
                {0, 1, 0, -2},
                {0, 0, 1, 3},
                {0, 0, 0, 1}
        });

        Translator t = new Translator(5, -2, 3);
        Assertions.assertTrue(expectedMatrix.equals(t.getMatrix()));
    }

    @Test
    public void testRotateMatrix() {
        float EPS = 1e-5f;
        Matrix4f expectedMatrix = new Matrix4f(new float[][]{
                {0, -0, -1, 0},
                {0, 1, -0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1}
        });
        ATransform affineTransformation = new Transformation(
                new Rotator(90, Rotator.Axis.X),
                new Rotator(90, Rotator.Axis.Y),
                new Rotator(90, Rotator.Axis.Z)
        );
        Assertions.assertTrue(expectedMatrix.equals(affineTransformation.getMatrix()));
    }
}
