package com.example.satisfactorytools.graph.cell.graphic;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class GraphCellGraphic extends Pane {

    protected final Group group;

    public GraphCellGraphic() {
        group = new Group();

        getChildren().add(group);
    }
}
