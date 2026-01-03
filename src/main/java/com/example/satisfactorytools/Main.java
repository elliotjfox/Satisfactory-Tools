package com.example.satisfactorytools;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.network.Network;
import com.example.satisfactorytools.factorycalculator.network.NetworkBuilder;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.factorycalculator.nodes.MachineNode;
import com.example.satisfactorytools.factorycalculator.nodes.NetworkNode;
import com.example.satisfactorytools.factorycalculator.nodes.SourceNode;
import com.example.satisfactorytools.factorycalculator.nodes.SplitterNode;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.Model;
import com.example.satisfactorytools.graph.cell.CircleCell;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.cell.NetworkCell;
import com.example.satisfactorytools.graph.edge.*;
import com.example.satisfactorytools.graph.layout.NetworkLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Graph graph = new Graph();

        Network network = NetworkBuilder.buildNetwork(new ResourceRate(ResourceType.REINFORCED_IRON_PLATE, 10));

//        populateGraph(graph);
        populateGraphWithNetwork(graph, network);

        graph.getNodeGestures().setDragButton(MouseButton.PRIMARY);
        graph.getViewportGestures().setPanButton(MouseButton.SECONDARY);

        Scene scene = new Scene(new BorderPane(graph.getCanvas()), 400, 300);
        stage.setTitle("Main Stage");
        stage.setScene(scene);
        stage.show();
    }

    private void populateGraphWithNetwork(Graph graph, Network network) {
        Model model = graph.getModel();

        Map<NetworkNode, GraphCell> map = new HashMap<>();
        List<GraphCell> sourceNodes = new ArrayList<>();

        for (NetworkNode node : network.getAdjacencyList().keySet()) {
            NetworkCell cell = new NetworkCell(node.toString());
            cell.setCellName("N{" + node + "}");
            model.addCell(cell);
            map.put(node, cell);
            if (node instanceof SourceNode) {
                sourceNodes.add(cell);
            }
        }

        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : network.getAdjacencyList().entrySet()) {
            for (NetworkNode node : entry.getValue()) {
                GraphEdge edge;
                if (entry.getKey() instanceof MachineNode || entry.getKey() instanceof SourceNode) {
                    ResourceRate commonRate = getCommonRate(entry.getKey(), node);
                    if (commonRate != null) {
                        edge = new NetworkEdge(map.get(entry.getKey()), map.get(node), commonRate.toString());
                    } else {
                        edge = new DirectedEdgeToCircle(map.get(entry.getKey()), map.get(node));
                    }
                } else if (entry.getKey() instanceof SplitterNode splitterNode) {
                    edge = new NetworkEdge(map.get(entry.getKey()), map.get(node), splitterNode.getOutput().getFirst().toString());
                } else {
                    edge = new DirectedEdgeToCircle(map.get(entry.getKey()), map.get(node));
                }
                model.addEdge(edge);
            }
        }

        graph.updateGraph();

        NetworkLayout layout = new NetworkLayout(sourceNodes);
        graph.layout(layout);
    }

    private ResourceRate getCommonRate(NetworkNode source, NetworkNode target) {
        for (ResourceRate outputRate : source.getOutput()) {
            for (ResourceRate inputRate : target.getInput()) {
                if (outputRate.resource() == inputRate.resource()) {
                    return new ResourceRate(outputRate.resource(), Math.min(outputRate.rate(), inputRate.rate()));
                }
            }
        }
        return null;
    }

    private void populateGraph(Graph graph) {
        Model model = graph.getModel();

        GraphCell cellA = new CircleCell();
        GraphCell cellB = new NetworkCell("B");
        GraphCell cellC = new CircleCell();
        GraphCell cellD = new CircleCell();

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);

        GraphEdge edgeAB = new BasicDirectedEdge(cellA, cellB);
        GraphEdge edgeBC = new BasicEdge(cellB, cellC);
        GraphEdge edgeBD = new BasicEdge(cellB, cellD);
        GraphEdge edgeCD = new BasicEdge(cellC, cellD);

        model.addEdge(edgeAB);
        model.addEdge(edgeBC);
        model.addEdge(edgeBD);
        model.addEdge(edgeCD);

        graph.updateGraph();
    }

    public static void main(String[] args) {
        launch();
    }
}