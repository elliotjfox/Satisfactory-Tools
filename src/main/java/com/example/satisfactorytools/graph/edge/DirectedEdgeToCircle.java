package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.DirectedEdgeToCircleGraphic;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Region;

public class DirectedEdgeToCircle extends GraphEdge {

    private final double radius;

    public DirectedEdgeToCircle(GraphCell source, GraphCell target) {
        this(source, target, 50);
    }

    public DirectedEdgeToCircle(GraphCell source, GraphCell target, double radius) {
        super(source, target, true);
        this.radius = radius;
    }

    @Override
    public Region createGraphic(Graph graph) {
        DirectedEdgeToCircleGraphic graphic = new DirectedEdgeToCircleGraphic(radius);

        DoubleBinding sourceX = this.getSource().getXAnchor(graph);
        DoubleBinding sourceY = this.getSource().getYAnchor(graph);
        DoubleBinding targetX = this.getTarget().getXAnchor(graph);
        DoubleBinding targetY = this.getTarget().getYAnchor(graph);

        graphic.setupGraphic(sourceX, sourceY, targetX, targetY);

        return graphic;
    }
}
