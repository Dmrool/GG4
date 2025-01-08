package com.cgvsu.ATransform;

import com.cgvsu.math.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslateRotateScaleTests {

    @Test
    public void testRotateTransform() {
        Vector3f expectedVec = new Vector3f(-1, 1, 1);  //ожидаемое значение
        ATransform affineTransformation = new Transformation(
                new Rotator(90, Rotator.Axis.X),
                new Rotator(90, Rotator.Axis.Y),
                new Rotator(90, Rotator.Axis.Z)
        );
        Vector3f vec = new Vector3f(1, 1, 1);

        Vector3f result = affineTransformation.transform(vec);

        System.out.println("Expected: " + expectedVec);
        System.out.println("Result: " + result);
        Assertions.assertTrue(equalsEpsilon(expectedVec, result, 0.0001f));
    }
    public boolean equalsEpsilon(Vector3f vec1, Vector3f vec2, float epsilon) {
        return Math.abs(vec1.x - vec2.x) < epsilon &&
                Math.abs(vec1.y - vec2.y) < epsilon &&
                Math.abs(vec1.z - vec2.z) < epsilon;
    }


    @Test
    public void testScale() {
        Vector3f expectedVec = new Vector3f(2, 3, 5);

        ATransform affineTransformation = new Scaling(2, 3, 5);
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }

    @Test
    public void testScaleTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        ATransform affineTransformation = new Transformation(new Scaling(9, -5, 2));
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }

    @Test
    public void testDefaultTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        ATransform affineTransformation = new Transformation();
        Vector3f vec = new Vector3f(9, -5, 2);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));

    }

    @Test
    public void testTranslateTransform() {
        //ожидаемое значение
        Vector3f expectedVec = new Vector3f(3, 7, -4);
        ATransform affineTransformation = new Transformation(new Translator(3, 7, -4));
        Vector3f vec = new Vector3f(0, 0, 0);
        //прим. трансформацию
        Vector3f result = affineTransformation.transform(vec);
        System.out.println("Expected: " + expectedVec);
        System.out.println("Result: " + result);
        Assertions.assertTrue(equalsEpsilon(expectedVec, result, 0.0001f));
    }

    @Test
    public void testTranslate() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        ATransform affineTransformation = new Translator(9, -5, 2);
        Vector3f vec = new Vector3f(0, 0, 0);

        Assertions.assertTrue(expectedVec.equals(affineTransformation.transform(vec)));
    }
}

