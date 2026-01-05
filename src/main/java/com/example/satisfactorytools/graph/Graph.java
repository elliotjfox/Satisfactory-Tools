package com.example.satisfactorytools.graph;

import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.GraphEdge;
import com.example.satisfactorytools.graph.gesture.NodeGestures;
import com.example.satisfactorytools.graph.gesture.ViewportGestures;
import com.example.satisfactorytools.graph.layout.Layout;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Model model;
    private final Map<GraphNode, Region> graphics;
    private final PannableCanvas pannableCanvas;
    private final BooleanProperty useNodeGestures;
    private final NodeGestures nodeGestures;
    private final BooleanProperty useViewportGestures;
    private final ViewportGestures viewportGestures;

    public Graph() {
        this(new Model());
    }

    public Graph(Model model) {
        this.model = model;

        pannableCanvas = new PannableCanvas();

        nodeGestures = new NodeGestures(this);
        useNodeGestures = new SimpleBooleanProperty(true);
        useNodeGestures.addListener((_, _, newVal) -> {
            if (newVal) {
                model.getAllCells().forEach(cell -> nodeGestures.makeDraggable(getGraphic(cell)));
            } else {
                model.getAllCells().forEach(cell -> nodeGestures.makeUndraggable(getGraphic(cell)));
            }
        });

        viewportGestures = new ViewportGestures(this);
        useViewportGestures = new SimpleBooleanProperty(true);
        useViewportGestures.addListener((obs, oldVal, newVal) -> {
            final Parent parent = pannableCanvas.parentProperty().get();
            if (parent == null) {
                return;
            }
            if (newVal) {
                parent.addEventHandler(MouseEvent.MOUSE_PRESSED, viewportGestures.getOnMousePressedEventHandler());
                parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, viewportGestures.getOnMouseDraggedEventHandler());
                parent.addEventHandler(ScrollEvent.ANY, viewportGestures.getOnScrollEventHandler());
            } else {
                parent.removeEventHandler(MouseEvent.MOUSE_PRESSED, viewportGestures.getOnMousePressedEventHandler());
                parent.removeEventHandler(MouseEvent.MOUSE_DRAGGED, viewportGestures.getOnMouseDraggedEventHandler());
                parent.removeEventHandler(ScrollEvent.ANY, viewportGestures.getOnScrollEventHandler());
            }
        });
        pannableCanvas.parentProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != null) {
                oldVal.removeEventHandler(MouseEvent.MOUSE_PRESSED, viewportGestures.getOnMousePressedEventHandler());
                oldVal.removeEventHandler(MouseEvent.MOUSE_DRAGGED, viewportGestures.getOnMouseDraggedEventHandler());
                oldVal.removeEventHandler(ScrollEvent.ANY, viewportGestures.getOnScrollEventHandler());
            }
            if (newVal != null) {
                newVal.addEventHandler(MouseEvent.MOUSE_PRESSED, viewportGestures.getOnMousePressedEventHandler());
                newVal.addEventHandler(MouseEvent.MOUSE_DRAGGED, viewportGestures.getOnMouseDraggedEventHandler());
                newVal.addEventHandler(ScrollEvent.ANY, viewportGestures.getOnScrollEventHandler());
            }
        });

        graphics = new HashMap<>();

        addCells(getModel().getAllCells());
        addEdges(getModel().getAllEdges());
    }

    public PannableCanvas getCanvas() {
        return pannableCanvas;
    }

    public Model getModel() {
        return model;
    }

    public void updateGraph() {
        getCanvas().getChildren().clear();

        addEdges(model.getAddedEdges());
        addCells(model.getAddedCells());

        removeEdges(model.getRemovedEdges());
        removeCells(model.getRemovedCells());

        getModel().endUpdate();
    }

    public void beginUpdate() {
        getCanvas().getChildren().clear();
    }

    public void endUpdate() {
        // add components to graph pane
        addEdges(model.getAddedEdges());
        addCells(model.getAddedCells());

        // remove components to graph pane
        removeEdges(model.getRemovedEdges());
        removeCells(model.getRemovedCells());

        // clean up the model
        getModel().endUpdate();
    }

    private void addEdges(List<GraphEdge> edges) {
        edges.forEach(edge -> {
            try {
                Region edgeGraphic = getGraphic(edge);
                getCanvas().getChildren().add(edgeGraphic);
                edge.onAddedToGraph(this, edgeGraphic);
            } catch (final Exception e) {
                throw new RuntimeException("failed to add " + edge, e);
            }
        });
    }

    private void removeEdges(List<GraphEdge> edges) {
        edges.forEach(edge -> {
            try {
                Region edgeGraphic = getGraphic(edge);
                getCanvas().getChildren().remove(edgeGraphic);
                edge.onRemovedFromGraph(this, edgeGraphic);
            } catch (final Exception e) {
                throw new RuntimeException("failed to remove " + edge, e);
            }
        });
    }

    private void addCells(List<GraphCell> cells) {
        cells.forEach(cell -> {
            try {
                Region cellGraphic = getGraphic(cell);
                getCanvas().getChildren().add(cellGraphic);
                if (useNodeGestures.get()) {
                    nodeGestures.makeDraggable(cellGraphic);
                }
                cell.onAddedToGraph(this, cellGraphic);
            } catch (final Exception e) {
                throw new RuntimeException("failed to add " + cell, e);
            }
        });
    }

    private void removeCells(List<GraphCell> cells) {
        cells.forEach(cell -> {
            try {
                Region cellGraphic = getGraphic(cell);
                getCanvas().getChildren().remove(cellGraphic);
                cell.onRemovedFromGraph(this, cellGraphic);
            } catch (final Exception e) {
                throw new RuntimeException("failed to remove " + cell, e);
            }
        });
    }

    public ViewportGestures getViewportGestures() {
        return viewportGestures;
    }

    public NodeGestures getNodeGestures() {
        return nodeGestures;
    }

    public double getScale() {
        return getCanvas().getScale();
    }

    public void layout(Layout layout) {
        layout.execute(this);
    }

    public Region getGraphic(GraphNode node) {
        if (!graphics.containsKey(node)) {
            graphics.put(node, createGraphic(node));
        }
        return graphics.get(node);
    }

    public Region createGraphic(GraphNode node) {
        return node.createGraphic(this);
    }
}
