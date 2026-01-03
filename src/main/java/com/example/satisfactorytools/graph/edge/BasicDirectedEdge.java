package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.DirectedEdgeGraphic;
import com.example.satisfactorytools.graph.edge.graphic.GraphEdgeGraphic;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Region;

public class BasicDirectedEdge extends GraphEdge {

    public BasicDirectedEdge(GraphCell source, GraphCell target) {
        super(source, target, true);
    }

    @Override
    public Region getGraphic(Graph graph) {
        GraphEdgeGraphic graphic = new DirectedEdgeGraphic(graph.getGraphic(getTarget()));

        DoubleBinding sourceX = this.getSource().getXAnchor(graph);
        DoubleBinding sourceY = this.getSource().getYAnchor(graph);
        DoubleBinding targetX = this.getTarget().getXAnchor(graph);
        DoubleBinding targetY = this.getTarget().getYAnchor(graph);

        graphic.setupGraphic(sourceX, sourceY, targetX, targetY);

        return graphic;
    }
}
