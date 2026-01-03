package com.example.satisfactorytools.graph;

import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.GraphEdge;

public interface GraphModel {

    void addCell(GraphCell cell);
    void removeCell(GraphCell cell);
    void addEdge(GraphCell sourceCell, GraphCell targetCell);
    void addEdge(GraphEdge edge);
    void removeEdge(GraphEdge edge);
}
