package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.gameinfo.Recipe;
import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.nodes.GoalNode;
import com.example.satisfactorytools.factorycalculator.nodes.MultipleNode;
import com.example.satisfactorytools.factorycalculator.nodes.NetworkNode;
import com.example.satisfactorytools.factorycalculator.nodes.SourceNode;

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
                network.addEdge(createSource(resourceRate), node);
                continue;
            }

            double multiplier = resourceRate.rate() / recipe.getOutput().rate();

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

//    private NetworkNode addToMachine()

}
