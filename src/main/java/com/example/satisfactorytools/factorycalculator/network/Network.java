package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.edge.NetworkEdge;
import com.example.satisfactorytools.factorycalculator.node.NetworkNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

    private final List<NetworkNode> allNodes;
    private final List<NetworkEdge> allEdges;

    public Network() {
        allNodes = new ArrayList<>();
        allEdges = new ArrayList<>();
    }

    public void addNode(NetworkNode node) {
        allNodes.add(node);
    }

    public void removeNode(NetworkNode node) {
        List<NetworkEdge> incidentEdges = new ArrayList<>();
        incidentEdges.addAll(node.getInEdges());
        incidentEdges.addAll(node.getOutEdges());
        for (NetworkEdge edge : incidentEdges) {
            removeEdge(edge);
        }
        allNodes.remove(node);
    }

    public void addEdge(NetworkEdge edge) {
        if (allEdges.contains(edge)) {
            return;
        }
        edge.getSource().addOutEdge(edge);
        edge.getTarget().addInEdge(edge);
        allEdges.add(edge);
    }

    public void addEdge(NetworkNode from, NetworkNode to) {
        if (from.isAdjacent(to)) {
            return;
        }
        addEdge(new NetworkEdge(from, to));
    }

    public void removeEdge(NetworkEdge edge) {
        edge.getSource().removeOutEdge(edge);
        edge.getTarget().removeInEdge(edge);
        allEdges.remove(edge);
    }

    public void removeEdge(NetworkNode from, NetworkNode to) {
        for (NetworkEdge edge : from.getOutEdges()) {
            if (edge.getTarget() == to) {
                removeEdge(edge);
                return;
            }
        }
    }

    public String toAdjacencyListString() {
        StringBuilder s = new StringBuilder();

        for (NetworkNode node : allNodes) {
            s.append(node).append(": ");
            if (node.getChildren().isEmpty()) {
                s.append("\n");
                continue;
            }
            s.append(node.getChildren().getFirst());
            for (int i = 1; i < node.getChildren().size(); i++) {
                s.append(", ").append(node.getChildren().get(i));
            }
            s.append("\n");
        }
        // Remove last \n
        s.deleteCharAt(s.length() - 1);

        return s.toString();
    }

    public List<NetworkNode> getAllNodes() {
        return allNodes;
    }

    public List<NetworkEdge> getAllEdges() {
        return allEdges;
    }
}
