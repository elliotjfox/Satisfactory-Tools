package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.DirectedEdgeToCircleGraphic;
import com.example.satisfactorytools.graph.edge.graphic.GraphEdgeGraphic;
import com.example.satisfactorytools.graph.edge.graphic.NetworkEdgeGraphic;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Region;

public class NetworkEdge extends GraphEdge {

    private final String labelText;

    public NetworkEdge(GraphCell source, GraphCell target, String labelText) {
        super(source, target, true);
        this.labelText = labelText;
    }

    @Override
    public Region getGraphic(Graph graph) {
        GraphEdgeGraphic graphic = new NetworkEdgeGraphic(50, labelText);

        DoubleBinding sourceX = this.getSource().getXAnchor(graph);
        DoubleBinding sourceY = this.getSource().getYAnchor(graph);
        DoubleBinding targetX = this.getTarget().getXAnchor(graph);
        DoubleBinding targetY = this.getTarget().getYAnchor(graph);

        graphic.setupGraphic(sourceX, sourceY, targetX, targetY);

        return graphic;
    }
}
