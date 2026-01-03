package com.example.satisfactorytools.graph.cell;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.GraphNode;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public abstract class GraphCell implements GraphNode {

    private final List<GraphCell> children = new ArrayList<>();
    private final List<GraphCell> parents = new ArrayList<>();

    public void addCellChild(GraphCell cell) {
        children.add(cell);
    }

    public void removeCellChild(GraphCell cell) {
        children.remove(cell);
    }

    public List<GraphCell> getCellChildren() {
        return children;
    }

    public void addCellParent(GraphCell cell) {
        parents.add(cell);
    }

    public List<GraphCell> getCellParents() {
        return parents;
    }

    public DoubleBinding getXAnchor(Graph graph) {
        Region graphic = graph.getGraphic(this);
        return graphic.layoutXProperty().add(graphic.widthProperty().divide(2));
    }

    public DoubleBinding getYAnchor(Graph graph) {
        Region graphic = graph.getGraphic(this);
        return graphic.layoutYProperty().add(graphic.heightProperty().divide(2));
    }

    public ReadOnlyDoubleProperty getWidth(Graph graph) {
        Region graphic = graph.getGraphic(this);
        return graphic.widthProperty();
    }

    public ReadOnlyDoubleProperty getHeight(Graph graph) {
        Region graphic = graph.getGraphic(this);
        return graphic.heightProperty();
    }
}
