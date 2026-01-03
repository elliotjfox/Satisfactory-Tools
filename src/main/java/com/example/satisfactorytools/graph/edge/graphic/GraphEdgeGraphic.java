package com.example.satisfactorytools.graph.edge.graphic;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class GraphEdgeGraphic extends Pane {

    protected final Group group;

    public GraphEdgeGraphic() {
        this.group = new Group();

        getChildren().add(group);
    }

    public abstract void setupGraphic(DoubleBinding sourceX, DoubleBinding sourceY, DoubleBinding targetX, DoubleBinding targetY);

    public Group getGroup() {
        return group;
    }
}
