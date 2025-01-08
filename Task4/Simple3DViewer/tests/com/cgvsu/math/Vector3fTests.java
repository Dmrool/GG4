package com.cgvsu.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class Vector3fTests {

    @Test
    void testAdd() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(2, 3, 4);
        Vector3f vfinal = new Vector3f(3, 5, 7);

        v1.add(v2);
        Assertions.assertAll(
                () -> assertEquals(vfinal.x, v1.x, "X component should be updated"),
                () -> assertEquals(vfinal.y, v1.y, "Y component should be updated"),
                () -> assertEquals(vfinal.z, v1.z, "Z component should be updated")
        );
    }

    @Test
    void additionTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(2, 3, 4);
        Vector3f vfinal = new Vector3f(3, 5, 7);

        Vector3f v3 = Vector3f.addition(v1, v2);
        Assertions.assertAll(
                () -> assertEquals(vfinal.x, v3.x, "X component should be updated"),
                () -> assertEquals(vfinal.y, v3.y, "Y component should be updated"),
                () -> assertEquals(vfinal.z, v3.z, "Z component should be updated")
        );
    }

    @Test
    void subTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(2, 3, 4);
        Vector3f vfinal = new Vector3f(1, 1, 1);

        v2.sub(v1);
        Assertions.assertAll(
                () -> assertEquals(vfinal.x, v2.x, "X component should be updated"),
                () -> assertEquals(vfinal.y, v2.y, "Y component should be updated"),
                () -> assertEquals(vfinal.z, v2.z, "Z component should be updated")
        );
    }

    @Test
    void subtractionTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(2, 3, 4);
        Vector3f vfinal = new Vector3f(1, 1, 1);

        Vector3f v3 = Vector3f.subtraction(v2, v1);
        Assertions.assertAll(
                () -> assertEquals(vfinal.x, v3.x, "X component should be updated"),
                () -> assertEquals(vfinal.y, v3.y, "Y component should be updated"),
                () -> assertEquals(vfinal.z, v3.z, "Z component should be updated")
        );
    }

    @Test
    void sub2Test() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(2, 3, 4);
        Vector3f vfinal = new Vector3f(1, 1, 1);

        v2.sub(v2, v1);
        Assertions.assertAll(
                () -> assertEquals(vfinal.x, v2.x, "X component should be updated"),
                () -> assertEquals(vfinal.y, v2.y, "Y component should be updated"),
                () -> assertEquals(vfinal.z, v2.z, "Z component should be updated")
        );
    }

    @Test
    void multiplyTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = v1.multiply(4);

        Assertions.assertAll(
                () -> assertEquals(4, v2.x, "X component should be updated"),
                () -> assertEquals(8, v2.y, "Y component should be updated"),
                () -> assertEquals(12, v2.z, "Z component should be updated")
        );
    }

    @Test
    void multTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        v1.mult(4);

        Assertions.assertAll(
                () -> assertEquals(4, v1.x, "X component should be updated"),
                () -> assertEquals(8, v1.y, "Y component should be updated"),
                () -> assertEquals(12, v1.z, "Z component should be updated")
        );
    }

    @Test
    void divideTest() {
        Vector3f v1 = new Vector3f(4, 8, 12);
        Vector3f v2 = v1.divide(4);

        Assertions.assertAll(
                () -> assertEquals(1, v2.x, "X component should be updated"),
                () -> assertEquals(2, v2.y, "Y component should be updated"),
                () -> assertEquals(3, v2.z, "Z component should be updated")
        );

        //Проверка деления на 0
        try {
            Vector3f v3 = v1.divide(0);
            Assertions.fail();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void divTest() {
        Vector3f v1 = new Vector3f(4, 8, 12);
        v1.div(4);

        Assertions.assertAll(
                () -> assertEquals(1, v1.x, "X component should be updated"),
                () -> assertEquals(2, v1.y, "Y component should be updated"),
                () -> assertEquals(3, v1.z, "Z component should be updated")
        );
    }

    @Test
    void lengthTest() {
        Vector3f v1 = new Vector3f(3, 4, 12);
        float result = v1.length();
        assertEquals(13, result);
    }

    @Test
    void lengthBetweenVectorsTest() {
        Vector3f v1 = new Vector3f(0, 0, 0);
        Vector3f v2 = new Vector3f(0, 3, 4);
        float result = v1.lengthBetweenVectors(v2, v1);
        assertEquals(5, result);
    }

    @Test
    void normalTest() {
        Vector3f vector = new Vector3f(3, 4, 0);

        Vector3f normalizedVector = vector.normal();

        assertEquals(0.6f, normalizedVector.x, 0.001f);
        assertEquals(0.8f, normalizedVector.y, 0.001f);
        assertEquals(0.0f, normalizedVector.z, 0.001f);

        //тест при нулевом векторе
        Vector3f zeroVector = new Vector3f(0, 0, 0);
        try {
            zeroVector.normal();
            Assertions.fail();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void normalizeTest() {
        Vector3f vector = new Vector3f(3, 4, 0);

        vector.normalize();

        // Проверяем, что длина вектора теперь равна 1
        assertEquals(1.0f, vector.length(), 0.00001f);

        // Проверяем, что компоненты вектора соответствуют ожидаемым после нормализации
        assertEquals(0.6f, vector.x, 0.00001f);
        assertEquals(0.8f, vector.y, 0.00001f);
        assertEquals(0.0f, vector.z, 0.00001f);


        // Проверка при нулевом векторе
        Vector3f zeroVector = new Vector3f(0, 0, 0);

        try {
            zeroVector.normalize();
            Assertions.fail();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void dotProductTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);

        float result = Vector3f.dotProduct(v1, v2);

        // Проверяем, что результат совпадает с ожидаемым значением
        assertEquals(32.0f, result);

    }

    @Test
    void dotTest() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);

        float result = v1.dot(v2);

        // Проверяем, что результат совпадает с ожидаемым значением
        assertEquals(32.0f, result);
    }

    @Test
    void crossProductTest() {
        // Создаем два вектора
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);

        // Вызываем метод crossProduct()
        Vector3f result = Vector3f.crossProduct(v1, v2);

        // Проверяем, что результат совпадает с ожидаемыми значениями
        assertEquals(-3.0f, result.x);
        assertEquals(6.0f, result.y);
        assertEquals(-3.0f, result.z);
    }

    @Test
    void crossTest() {
        // Создаем два вектора
        Vector3f var1 = new Vector3f(1, 2, 3);
        Vector3f var2 = new Vector3f(4, 5, 6);

        // Выполняем операцию кросс-продукта
        var1.cross(var1,var2);

        // Проверяем, что компоненты вектора var1 изменились соответствующим образом
        assertEquals(-3.0f, var1.x, 0.00001f);
        assertEquals(6.0f, var1.y, 0.00001f);
        assertEquals(-3.0f, var1.z, 0.00001f);
    }
}