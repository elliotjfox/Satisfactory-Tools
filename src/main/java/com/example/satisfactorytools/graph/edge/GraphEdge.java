package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.GraphNode;
import com.example.satisfactorytools.graph.cell.GraphCell;

public abstract class GraphEdge implements GraphNode {

    private final GraphCell source;
    private final GraphCell target;
    private final boolean isDirected;

    public GraphEdge(GraphCell source, GraphCell target, boolean isDirected) {
        this.source = source;
        this.target = target;
        this.isDirected = isDirected;

        linkCells();
    }

    protected void linkCells() {
//        source.addCellParent(target);
//        target.addCellChild(source);
        source.addCellChild(target);
        target.addCellParent(source);
    }

    public GraphCell getSource() {
        return source;
    }

    public GraphCell getTarget() {
        return target;
    }

    public boolean isDirected() {
        return isDirected;
    }
}
