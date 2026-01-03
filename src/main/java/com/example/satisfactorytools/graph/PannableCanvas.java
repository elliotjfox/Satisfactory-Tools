package com.example.satisfactorytools.graph;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PannableCanvas extends Pane {

    private final DoubleProperty scaleProperty;

    public PannableCanvas() {
        this(new SimpleDoubleProperty(1.0));
    }

    public PannableCanvas(DoubleProperty scaleProperty) {
        this.scaleProperty = scaleProperty;
        scaleXProperty().bind(scaleProperty);
        scaleYProperty().bind(scaleProperty);
    }

    public double getScale() {
        return scaleProperty.get();
    }

    public void setScale(double scale) {
        scaleProperty.set(scale);
    }

    public void setPivot(double x, double y) {
        setTranslateX(getTranslateX() - x);
        setTranslateY(getTranslateY() - y);
    }
}
