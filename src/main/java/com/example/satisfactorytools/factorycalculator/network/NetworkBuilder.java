package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.gameinfo.Recipe;
import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.nodes.*;

import java.util.*;

public class NetworkBuilder {


    public static Network buildNetwork(ResourceRate... goal) {
        Network network = new Network();
        Queue<NetworkNode> unfinishedNodes = new LinkedList<>();
        Map<NetworkNode, ResourceRate> availableOutputs = new HashMap<>();

        for (ResourceRate rate : goal) {
            GoalNode goalNode = new GoalNode(rate);
            network.addNode(goalNode);
            unfinishedNodes.add(goalNode);
        }

        // TODO: Need to implement numbers!
        while (!unfinishedNodes.isEmpty()) {
            System.out.println(unfinishedNodes.size() + " left");
            NetworkNode currentNode = unfinishedNodes.remove();
            for (ResourceRate resourceRate : currentNode.getInput()) {
                Recipe defaultRecipe = resourceRate.resource().getDefaultRecipe();
                if (defaultRecipe == null) {
                    NetworkNode sourceNode = new SourceNode(resourceRate);
                    network.addNode(sourceNode);
                    network.addEdge(sourceNode, currentNode);
                    continue;
                }
                double recipeRate = defaultRecipe.getOutput().rate();
                double requiredRate = resourceRate.rate();
                double multiplier = requiredRate / recipeRate;
                int count = (int) Math.ceil(multiplier);

                // We need a half input
                if (multiplier + 0.5 == count) {
                    count--;
                    boolean found = false;
                    for (Map.Entry<NetworkNode, ResourceRate> available : availableOutputs.entrySet()) {
                        if (available.getValue().resource() == resourceRate.resource()) {
                            network.addEdge(available.getKey(), currentNode);
                            found = true;
                            availableOutputs.remove(available.getKey());
                            break;
                        }
                    }
                    if (!found) {
                        NetworkNode splitter = new SplitterNode(defaultRecipe.getOutput(), 2);
                        network.addNode(splitter);
                        network.addEdge(splitter, currentNode);
                        availableOutputs.put(splitter, splitter.getOutput().getFirst());
                        unfinishedNodes.offer(splitter);
                    }
                }

                for (int i = 0; i < count; i++) {
                    NetworkNode machineNode = new MachineNode(defaultRecipe.getMachine(), defaultRecipe);
                    network.addNode(machineNode);
                    network.addEdge(machineNode, currentNode);
                    unfinishedNodes.offer(machineNode);
                }
                // TODO: Select from available recipes
            }
        }

        reduceNetwork(network);
        return network;
    }

    private static void reduceNetwork(Network network) {
        List<NetworkNode> nodesToRemove = new ArrayList<>();
        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : network.getAdjacencyList().entrySet()) {
            // If a splitter only splits to one node, remove it
            if (entry.getKey() instanceof SplitterNode splitterNode && entry.getValue().size() == 1) {
                for (NetworkNode parent : network.getParents(splitterNode)) {
                    network.removeEdge(parent, splitterNode);
                    network.addEdge(parent, entry.getValue().getFirst());
                }
                network.removeEdge(splitterNode, entry.getValue().getFirst());
                nodesToRemove.add(splitterNode);
            }
        }
        for (NetworkNode node : nodesToRemove) {
            network.removeNode(node);
        }
    }

//    public static void verifyNetwork(Network network) {
//        for (Map.Entry<NetworkNode, List<NetworkNode>> entry : network.getAdjacencyList().entrySet()) {
//            System.out.print("Checking " + entry.getKey());
//            for (NetworkNode node : entry.getValue()) {
//
//            }
//        }
//    }
//
//    private static void verifyNode(NetworkNode node, List<NetworkNode> children) {
//        System.out.print("Checking " + node);
//        Map<ResourceType, Double> outputs = new HashMap<>();
//        boolean result = true;
//        for (ResourceRate output : node.getOutput()) {
//            if (outputs.containsKey(output.resource())) {
//                outputs.put(output.resource(), outputs.get(output.resource()) + output.rate());
//            } else {
//                outputs.put(output.resource(), output.rate());
//            }
//        }
//
//        for
//    }

    private static List<Recipe> getAllRecipes(ResourceType resource) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : Recipe.values()) {
            if (recipe.getOutput().resource() == resource) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
