package com.example.satisfactorytools.graph.cell;

import com.example.satisfactorytools.graph.Graph;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleCell extends GraphCell {
    @Override
    public Region getGraphic(Graph graph) {
        Circle circle = new Circle(25);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.DODGERBLUE);

        Pane pane = new Pane();
        pane.setPrefSize(50, 50);
        pane.getChildren().add(circle);
        circle.relocate(0, 0);

        return pane;

    }
}
