package com.example.satisfactorytools.graph.edge.graphic;

import javafx.beans.binding.DoubleBinding;

public abstract class DirectedEdgeGraphic extends GraphEdgeGraphic {

    protected final Arrow arrow;

    public DirectedEdgeGraphic() {
        super();

        arrow = new Arrow();
    }

    protected void setupArrow(DoubleBinding sourceX, DoubleBinding sourceY, DoubleBinding targetX, DoubleBinding targetY) {
        // Set position bindings
        arrow.startXProperty().bind(sourceX);
        arrow.startYProperty().bind(sourceY);
        arrow.endXProperty().bind(targetX);
        arrow.endYProperty().bind(targetY);

        // CSS?

        group.getChildren().add(arrow);
    }

    public Arrow getArrow() {
        return arrow;
    }
}
