package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.GraphEdgeGraphic;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;

public class BasicEdge extends GraphEdge {

    public BasicEdge(GraphCell source, GraphCell target) {
        super(source, target, false);
    }

    @Override
    public Region createGraphic(Graph graph) {
        Pane graphicPane = new Pane();

        Line line = new Line();
        graphicPane.getChildren().add(line);

        line.startXProperty().bind(getSource().getXAnchor(graph));
        line.startYProperty().bind(getSource().getYAnchor(graph));
        line.endXProperty().bind(getTarget().getXAnchor(graph));
        line.endYProperty().bind(getTarget().getYAnchor(graph));

        return graphicPane;
    }

    private static class BasicEdgeGraphic extends GraphEdgeGraphic {

        private final Line line;

        public BasicEdgeGraphic() {
            super();

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
}
