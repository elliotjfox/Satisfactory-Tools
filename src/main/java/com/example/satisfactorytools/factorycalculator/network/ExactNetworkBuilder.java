package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.edge.NetworkEdge;
import com.example.satisfactorytools.factorycalculator.gameinfo.Recipe;
import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.node.*;

import java.util.*;

public class ExactNetworkBuilder {

    private final NetworkSettings settings;
    private final List<ResourceRate> goals;

    private Network network;
    private Queue<NetworkNode> unfinishedNodes;
    private HashMap<ResourceType, MultipleNode> resourceSources;

    public ExactNetworkBuilder(NetworkSettings settings, ResourceRate... goals) {
        this.settings = settings;
        this.goals = new ArrayList<>(List.of(goals));
    }

    private void reset() {
        network = new Network();
        unfinishedNodes = new LinkedList<>();
        resourceSources = new HashMap<>();
    }

    public Network generateNetwork() {
        reset();

        for (ResourceRate resourceRate : goals) {
            GoalNode tmp = new GoalNode(resourceRate);
            network.addNode(tmp);
            unfinishedNodes.add(tmp);
        }

        while (!unfinishedNodes.isEmpty()) {
            NetworkNode currentNode = unfinishedNodes.remove();
            fulfillRequirements(currentNode);

        }

        return network;
    }

    private void fulfillRequirements(NetworkNode node) {
        for (ResourceRate resourceRate : node.getInput()) {
            Recipe recipe = selectRecipe(resourceRate.resource());
            if (recipe == null) {
                NetworkEdge edge = new NetworkEdge(createSource(resourceRate), node);
                edge.setResourceRate(resourceRate);
                network.addEdge(edge);
                continue;
            }

            fulfillRequirement(node, resourceRate);
//            double multiplier = resourceRate.rate() / recipe.getOutput().rate();
        }
    }

    private void fulfillRequirement(NetworkNode node, ResourceRate resourceRate) {
        System.out.println("Fulfilling " + node + ": " + resourceRate);
        if (resourceSources.containsKey(resourceRate.resource())) {
            System.out.println("Source exists");
            // A source for this resource exists
            NetworkNode source = resourceSources.get(resourceRate.resource()).getNetworkNode();
            if (resourceSources.get(resourceRate.resource()).getNetworkNode().isAdjacent(node)) {
                // We are already connected to it, so we need to increase the amount of it
                resourceSources.get(resourceRate.resource()).addMultiplier(resourceRate.rate());
                // We need to update this node's requirements
                unfinishedNodes.add(source);
            } else {
                // Not connected, but the source exists
                NetworkEdge edge = new NetworkEdge(source, node);
                edge.setResourceRate(resourceRate);
                network.addEdge(edge);
            }
        } else {
            System.out.println("Source does not exist");
            // A source for this resource does not exist
            Recipe recipe = selectRecipe(resourceRate.resource());
            System.out.println(recipe);
            if (recipe == null) {
                NetworkNode sourceNode = createSource(resourceRate);
                NetworkEdge edge = new NetworkEdge(sourceNode, node);
                edge.setResourceRate(resourceRate);
                network.addEdge(edge);
            } else {
                MultipleMachineNode machineNode = new MultipleMachineNode(recipe.getMachine(), recipe, resourceRate.rate() / recipe.getOutput().rate());
                network.addNode(machineNode);
                NetworkEdge edge = new NetworkEdge(machineNode, node);
                edge.setResourceRate(resourceRate);
                network.addEdge(edge);
                unfinishedNodes.add(machineNode);
                resourceSources.put(resourceRate.resource(), machineNode);
                System.out.println(machineNode.getOutEdges());
            }
        }
    }

    private Recipe selectRecipe(ResourceType resourceType) {
        return resourceType.getDefaultRecipe();
    }

    private NetworkNode createSource(ResourceRate resourceRate) {
        if (!resourceSources.containsKey(resourceRate.resource())) {
            SourceNode tmp = new SourceNode(resourceRate.resource(), 0);
            network.addNode(tmp);
            resourceSources.put(resourceRate.resource(), tmp);
        }

        resourceSources.get(resourceRate.resource()).addMultiplier(resourceRate.rate());
        return resourceSources.get(resourceRate.resource()).getNetworkNode();
    }
}
