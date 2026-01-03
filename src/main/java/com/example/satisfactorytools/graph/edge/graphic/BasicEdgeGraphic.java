package com.example.satisfactorytools.graph.edge.graphic;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Line;

public class BasicEdgeGraphic extends GraphEdgeGraphic {

    private final Line line;

    public BasicEdgeGraphic() {
        line = new Line();

        group.getChildren().add(line);
    }

    @Override
    public void setupGraphic(DoubleBinding sourceX, DoubleBinding sourceY, DoubleBinding targetX, DoubleBinding targetY) {
        line.startXProperty().bind(sourceX);
        line.startYProperty().bind(sourceY);
        line.endXProperty().bind(targetX);
        line.endYProperty().bind(targetY);
    }
}
