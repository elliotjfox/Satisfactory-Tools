package com.example.satisfactorytools.factorycalculator.node;

import com.example.satisfactorytools.factorycalculator.edge.NetworkEdge;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.cell.GraphCell;

import java.util.ArrayList;
import java.util.List;

public abstract class NetworkNode extends GraphCell {

    protected final List<NetworkEdge> outEdges = new ArrayList<>();
    protected final List<NetworkEdge> inEdges = new ArrayList<>();

    public abstract List<ResourceRate> getOutput();
    public abstract List<ResourceRate> getInput();

    public void addOutEdge(NetworkEdge edge) {
        outEdges.add(edge);
    }

    public void removeOutEdge(NetworkEdge edge) {
        outEdges.remove(edge);
    }

    public List<NetworkEdge> getOutEdges() {
        return outEdges;
    }

    public void addInEdge(NetworkEdge edge) {
        inEdges.add(edge);
    }

    public void removeInEdge(NetworkEdge edge) {
        inEdges.remove(edge);
    }

    public List<NetworkEdge> getInEdges() {
        return inEdges;
    }

    public List<NetworkNode> getChildren() {
        return outEdges.stream().map(NetworkEdge::getSource).toList();
    }

    public List<NetworkNode> getParents() {
        return inEdges.stream().map(NetworkEdge::getTarget).toList();
    }

    public boolean isAdjacent(NetworkNode node) {
        return getChildren().contains(node);
    }

    protected static List<ResourceRate> singleList(ResourceRate resourceRate) {
        List<ResourceRate> list = new ArrayList<>();
        list.add(resourceRate);
        return list;
    }
}
