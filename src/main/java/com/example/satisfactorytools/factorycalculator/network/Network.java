package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.nodes.NetworkNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {

    private final Map<NetworkNode, List<NetworkNode>> adjacencyList;

    public Network() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(NetworkNode node) {
        adjacencyList.put(node, new ArrayList<>(adjacencyList.size()));
    }

    public void removeNode(NetworkNode node) {
        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : adjacencyList.entrySet()) {
            if (entry.getKey() == node) {
                continue;
            }
            entry.getValue().remove(node);
        }
        adjacencyList.remove(node);
    }

    public void addEdge(NetworkNode from, NetworkNode to) {
        if (isConnected(from, to)) {
            return;
        }
        adjacencyList.get(from).add(to);
    }

    public void removeEdge(NetworkNode from, NetworkNode to) {
        if (!isConnected(from, to)) {
            return;
        }
        adjacencyList.get(from).remove(to);
    }

    public boolean isConnected(NetworkNode from, NetworkNode to) {
        return adjacencyList.get(from).contains(to);
    }

    public String toAdjacencyListString() {
        StringBuilder s = new StringBuilder();

        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : adjacencyList.entrySet()) {
            s.append(entry.getKey()).append(": ");
            if (entry.getValue().isEmpty()) {
                s.append("\n");
                continue;
            }
            s.append(entry.getValue().getFirst());
            for (int i = 1; i < entry.getValue().size(); i++) {
                s.append(", ").append(entry.getValue().get(i));
            }
            s.append("\n");
        }
        // Remove last \n
        s.deleteCharAt(s.length() - 1);

        return s.toString();
    }

    public Map<NetworkNode, List<NetworkNode>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<NetworkNode> getParents(NetworkNode node) {
        List<NetworkNode> parents = new ArrayList<>();
        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().contains(node) && entry.getKey() != node) {
                parents.add(entry.getKey());
            }
        }
        return parents;
    }
}
