package com.example.satisfactorytools.graph;

import javafx.scene.layout.Region;

public interface GraphNode {
    Region getGraphic(Graph graph);
    default void onAddedToGraph(Graph graph, Region region) {}
    default void onRemovedFromGraph(Graph graph, Region region) {}
}
