package com.example.satisfactorytools.graph;

import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.GraphEdge;
import com.example.satisfactorytools.graph.edge.BasicEdge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

import java.util.List;

public class Model {
    
    private final GraphCell root;

    private ObservableList<GraphCell> allCells;
    private ObservableList<GraphCell> addedCells;
    private ObservableList<GraphCell> removedCells;

    private ObservableList<GraphEdge> allEdges;
    private ObservableList<GraphEdge> addedEdges;
    private ObservableList<GraphEdge> removedEdges;
    
    public Model() {
        root = new GraphCell() {
            @Override
            public Region createGraphic(Graph graph) {
                return null;
            }
        };

        clear();
    }
    
    public void clear() {
        allCells = FXCollections.observableArrayList();
        addedCells = FXCollections.observableArrayList();
        removedCells = FXCollections.observableArrayList();

        allEdges = FXCollections.observableArrayList();
        addedEdges = FXCollections.observableArrayList();
        removedEdges = FXCollections.observableArrayList();
    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public void endUpdate() {
        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        attachOrphansToGraphParent(getAddedCells());

        // remove reference to graphParent
        disconnectFromGraphParent(getRemovedCells());

        // merge added & removed cells with all cells
        merge();
    }

    public ObservableList<GraphCell> getAddedCells() {
        return addedCells;
    }

    public ObservableList<GraphCell> getRemovedCells() {
        return removedCells;
    }

    public ObservableList<GraphCell> getAllCells() {
        return allCells;
    }

    public ObservableList<GraphEdge> getAddedEdges() {
        return addedEdges;
    }

    public ObservableList<GraphEdge> getRemovedEdges() {
        return removedEdges;
    }

    public ObservableList<GraphEdge> getAllEdges() {
        return allEdges;
    }

    public void addCell(GraphCell cell) {
        if(cell == null) {
            throw new NullPointerException("Cannot add a null cell");
        }
        addedCells.add(cell);
    }

    public void removeCell(GraphCell cell) {
        if(cell == null) {
            throw new NullPointerException("Cannot remove a null cell");
        }
        removedCells.add(cell);
    }

    public void addEdge(GraphCell sourceCell, GraphCell targetCell) {
        final GraphEdge edge = new BasicEdge(sourceCell, targetCell);
        addEdge(edge);
    }

    public void addEdge(GraphEdge edge) {
        if(edge == null) {
            throw new NullPointerException("Cannot add a null edge");
        }
        addedEdges.add(edge);
    }

    public void removeEdge(GraphEdge edge) {
        if(edge == null) {
            throw new NullPointerException("Cannot remove a null edge");
        }
        removedEdges.add(edge);
    }

    /**
     * Attach all cells which don't have a parent to graphParent
     *
     * @param cellList
     */
    public void attachOrphansToGraphParent(List<GraphCell> cellList) {
        System.out.println("Orphans");
        for (final GraphCell cell : cellList) {
            if (cell.getCellParents().isEmpty()) {
                System.out.println("Yep: " + cell);
                root.addCellChild(cell);
            }
        }
    }

    /**
     * Remove the graphParent reference if it is set
     *
     * @param cellList
     */
    public void disconnectFromGraphParent(List<GraphCell> cellList) {
        for (GraphCell cell : cellList) {
            root.removeCellChild(cell);
        }
    }

    public GraphCell getRoot() {
        return root;
    }

    public void merge() {
        // cells
        allCells.addAll(addedCells);
        allCells.removeAll(removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll(addedEdges);
        allEdges.removeAll(removedEdges);

        addedEdges.clear();
        removedEdges.clear();
    }

}
