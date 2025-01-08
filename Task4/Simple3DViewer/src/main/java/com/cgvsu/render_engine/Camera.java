package com.cgvsu.render_engine;
import com.cgvsu.ATransform.ATransform;
import com.cgvsu.ATransform.Rotator;
import com.cgvsu.ATransform.Transformation;
import com.cgvsu.ATransform.Translator;
import com.cgvsu.math.Matrix4f;
import com.cgvsu.math.Vector3f;

public class Camera {

    private Vector3f position;
    private Vector3f target;
    private final float fov;
    private float aspectRatio;
    private final float nearPlane;
    private final float farPlane;

    public Camera(
            final Vector3f position,
            final Vector3f target,
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        this.position = position;
        this.target = target;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }

    public void setAspectRatio(final float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(final Vector3f position) {
        this.position = position;
    }

    public Vector3f getTarget() {
        return target;
    }

    public void setTarget(final Vector3f target) {
        this.target = target;
    }

    public void movePosition(final Vector3f translation) {
        this.position.add(translation);
    }

    public void Rotate(float x, float y, float z) {
        ATransform affineTransformation = new Transformation(
                new Rotator(x, Rotator.Axis.X),
                new Rotator(y, Rotator.Axis.Y),
                new Rotator(z, Rotator.Axis.Z));
        this.position = affineTransformation.transform(this.position);
    }

    public void Rotate(int x, int y, int z) {
        ATransform affineTransformation = new Transformation(
                new Rotator(x, Rotator.Axis.X),
                new Rotator(y, Rotator.Axis.Y),
                new Rotator(z, Rotator.Axis.Z));
        this.position = affineTransformation.transform(this.position);
    }

    public void Translate(float x, float y, float z) {
        ATransform affineTransformation = new Translator(x, y, z);
        this.position = affineTransformation.transform(this.position);
    }

    public void moveTarget(final Vector3f translation) {
        this.target.add(target);
    }

    Matrix4f getViewMatrix() {
        return GraphicConveyor.lookAt(position, target);
    }

    Matrix4f getProjectionMatrix() {
        return GraphicConveyor.perspective(fov, aspectRatio, nearPlane, farPlane);
    }
}