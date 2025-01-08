package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Matrix4fTests {

    @Test
    void testMatrix4fConstructorWithArray() {
        float[][] values = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f matrix = new Matrix4f(values);
        assertEquals(1, matrix.mat[0][0]);
        assertEquals(16, matrix.mat[3][3]);
    }

    @Test
    void testMatrix4fDefaultConstructor() {
        Matrix4f matrix = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0, matrix.mat[i][j]);
            }
        }
    }

    @Test
    void testMatrix4fConstructorWithNumber() {
        Matrix4f matrix = new Matrix4f(5);
        for (int i = 0; i < 4; i++) {
            assertEquals(5, matrix.mat[i][i]);  //Диагональные элементы должны быть равны 5
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    assertEquals(0, matrix.mat[i][j]);  //Недиагональные элементы должны быть равны 0
                }
            }
        }
    }

    @Test
    void testAddMatrices() {
        float[][] mat1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] mat2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4f m1 = new Matrix4f(mat1);
        Matrix4f m2 = new Matrix4f(mat2);

        Matrix4f result = Matrix4f.add(m1, m2);

        assertEquals(17, result.mat[0][0], 0.0001f);
        assertEquals(17, result.mat[3][3], 0.0001f);
        assertEquals(17, result.mat[1][2], 0.0001f);
    }

    @Test
    void testSubMatrices() {
        float[][] mat1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] mat2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4f m1 = new Matrix4f(mat1);
        Matrix4f m2 = new Matrix4f(mat2);

        Matrix4f result = Matrix4f.sub(m1, m2);

        assertEquals(-15, result.mat[0][0]);
        assertEquals(-1, result.mat[1][3]);
    }

    @Test
    void testMultiplyMatrices() {
        float[][] mat1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] mat2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };

        Matrix4f m1 = new Matrix4f(mat1);
        Matrix4f m2 = new Matrix4f(mat2);

        Matrix4f result = Matrix4f.multiply(m1, m2);

        assertEquals(80, result.mat[0][0], 0.0001f);
        assertEquals(386, result.mat[3][3], 0.0001f);
    }

    @Test
    void testMulVector() {
        float[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f matrix = new Matrix4f(mat);
        Vector4f vector = new Vector4f(1, 2, 3, 4);

        Vector4f result = matrix.mulVector(vector);

        assertEquals(30, result.x);
        assertEquals(70, result.y);
        assertEquals(110, result.z);
        assertEquals(150, result.w);
    }

    @Test
    void testTranspose() {
        float[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f matrix = new Matrix4f(mat);

        matrix.transpose();

        assertEquals(1, matrix.mat[0][0]);
        assertEquals(2, matrix.mat[1][0]);
        assertEquals(3, matrix.mat[2][0]);
        assertEquals(4, matrix.mat[3][0]);
        assertEquals(16, matrix.mat[3][3]);
    }

    @Test
    void testEquals() {
        float[][] mat1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] mat2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4f m1 = new Matrix4f(mat1);
        Matrix4f m2 = new Matrix4f(mat2);

        assertTrue(m1.equals(m2));
    }

    @Test
    void testNotEquals() {
        float[][] mat1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        float[][] mat2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {17, 18, 19, 20}
        };
        Matrix4f m1 = new Matrix4f(mat1);
        Matrix4f m2 = new Matrix4f(mat2);

        assertFalse(m1.equals(m2));
    }
}
