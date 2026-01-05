package com.example.satisfactorytools.graph.cell;

import com.example.satisfactorytools.graph.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends GraphCell {
    @Override
    public Region createGraphic(Graph graph) {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setStroke(Color.DODGERBLUE);
        rectangle.setFill(Color.DODGERBLUE);

        Pane pane = new Pane(rectangle);
        pane.setPrefSize(50, 50);
        rectangle.widthProperty().bind(pane.widthProperty());
        rectangle.heightProperty().bind(pane.heightProperty());

        return pane;
    }
}
