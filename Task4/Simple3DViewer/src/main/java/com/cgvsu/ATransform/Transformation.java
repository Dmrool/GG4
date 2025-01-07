package com.cgvsu.ATransform;

import com.cgvsu.math.Matrix4f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformation implements ATransform, DataList<ATransform> {
    private final List<ATransform> atransformations = new ArrayList<>();
    private boolean isCalculated = false;
    private Matrix4f trsMatrix;

    public Transformation(ATransform... ats) {
        Collections.addAll(atransformations, ats);
    }

    public Transformation() {
    }

    public boolean isCalculated() {
        return isCalculated;
    }


    @Override
    public Matrix4f getMatrix() {
        if (isCalculated()) {
            return trsMatrix;
        }

        trsMatrix = new Matrix4f(new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });

        for (ATransform at : atransformations) {
            trsMatrix = Matrix4f.multiply(trsMatrix, at.getMatrix());
        }
        isCalculated = true;
        return trsMatrix;
    }

    @Override
    public void add(ATransform at) {
        atransformations.add(at);
        isCalculated = false;
    }

    @Override
    public void remove(int index) {
        atransformations.remove(index);
        isCalculated = false;
    }

    @Override
    public void remove(ATransform at) {
        atransformations.remove(at);
        isCalculated = false;
    }

    @Override
    public void set(int index, ATransform at) {
        atransformations.set(index, at);
        isCalculated = false;
    }

    @Override
    public ATransform get(int index) {
        return atransformations.get(index);
    }
}
