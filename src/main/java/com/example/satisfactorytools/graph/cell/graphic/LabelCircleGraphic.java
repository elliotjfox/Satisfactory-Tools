package com.example.satisfactorytools.graph.cell.graphic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class LabelCircleGraphic extends CircleGraphic {

    private final StringProperty textProperty;
    private final Label label;

    public LabelCircleGraphic(String text) {
        this(50, Color.WHITE, text);
    }

    public LabelCircleGraphic(Color fill, String text) {
        this(50, fill, text);
    }

    public LabelCircleGraphic(double radius, Color fill, String text) {
        super(radius, fill);

        this.textProperty = new SimpleStringProperty();
        this.label = new Label();

        label.textProperty().bind(textProperty);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setPrefSize(100, 100);
        textProperty.set(text);

        group.getChildren().add(label);
    }

    public String getTextProperty() {
        return textProperty.get();
    }

    public StringProperty textProperty() {
        return textProperty;
    }
}
