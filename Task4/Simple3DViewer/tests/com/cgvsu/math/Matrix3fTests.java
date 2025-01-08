package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix3fTests {

    @Test
    void testMatrix3fConstructorWithArray() {
        float[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f matrix = new Matrix3f(values);
        assertEquals(1, matrix.mat[0][0]);
        assertEquals(9, matrix.mat[2][2]);
    }

    @Test
    void testMatrix3fDefaultConstructor() {
        Matrix3f matrix = new Matrix3f();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, matrix.mat[i][j]);
            }
        }
    }

    @Test
    void testMatrix3fConstructorWithNumber() {
        Matrix3f matrix = new Matrix3f(5);
        for (int i = 0; i < 3; i++) {
            assertEquals(5, matrix.mat[i][i]);  // Диагональные элементы должны быть 5
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    assertEquals(0, matrix.mat[i][j]);  // Недиагональные элементы должны быть 0
                }
            }
        }
    }

    @Test
    void testAddMatrices() {
        float[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] mat2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        Matrix3f m1 = new Matrix3f(mat1);
        Matrix3f m2 = new Matrix3f(mat2);
        Matrix3f result = Matrix3f.add(m1, m2);

        assertEquals(10, result.mat[0][0], 0.0001f);
        assertEquals(10, result.mat[2][2], 0.0001f);
        assertEquals(10, result.mat[1][0], 0.0001f);
    }


    @Test
    void testMultiplyMatrices() {
        float[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] mat2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        Matrix3f m1 = new Matrix3f(mat1);
        Matrix3f m2 = new Matrix3f(mat2);
        Matrix3f result = Matrix3f.multiply(m1, m2);

        assertEquals(90, result.mat[0][0], 0.0001f);
        assertEquals(30, result.mat[2][2], 0.0001f);
    }

    @Test
    void testMulVector() {
        float[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f matrix = new Matrix3f(mat);
        Vector3f vector = new Vector3f(1, 2, 3);
        Vector3f result = matrix.mulVector(vector);

        assertEquals(1, result.x);
        assertEquals(4, result.y);
        assertEquals(7, result.z);
    }

    @Test
    void testTranspose() {
        float[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f matrix = new Matrix3f(mat);
        matrix.transpose();

        assertEquals(1, matrix.mat[0][0]);
        assertEquals(2, matrix.mat[1][0]);
        assertEquals(3, matrix.mat[2][0]);
        assertEquals(9, matrix.mat[2][2]);
    }

    @Test
    void testEquals() {
        float[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] mat2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3f m1 = new Matrix3f(mat1);
        Matrix3f m2 = new Matrix3f(mat2);
        assertTrue(m1.equals(m2));
    }

    @Test
    void testNotEquals() {
        float[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        float[][] mat2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 10}
        };
        Matrix3f m1 = new Matrix3f(mat1);
        Matrix3f m2 = new Matrix3f(mat2);
        assertFalse(m1.equals(m2));
    }
}

