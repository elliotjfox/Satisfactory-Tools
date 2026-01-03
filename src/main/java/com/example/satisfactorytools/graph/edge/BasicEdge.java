package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.BasicEdgeGraphic;
import com.example.satisfactorytools.graph.edge.graphic.DirectedEdgeGraphic;
import com.example.satisfactorytools.graph.edge.graphic.GraphEdgeGraphic;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.Region;

public class BasicEdge extends GraphEdge {

    public BasicEdge(GraphCell source, GraphCell target) {
        super(source, target, false);
    }

    @Override
    public Region getGraphic(Graph graph) {
        GraphEdgeGraphic graphic = new BasicEdgeGraphic();

        DoubleBinding sourceX = this.getSource().getXAnchor(graph);
        DoubleBinding sourceY = this.getSource().getYAnchor(graph);
        DoubleBinding targetX = this.getTarget().getXAnchor(graph);
        DoubleBinding targetY = this.getTarget().getYAnchor(graph);

        graphic.setupGraphic(sourceX, sourceY, targetX, targetY);

        return graphic;
    }
}
