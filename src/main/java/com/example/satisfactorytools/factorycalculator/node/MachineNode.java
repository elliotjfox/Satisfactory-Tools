package com.example.satisfactorytools.factorycalculator.node;

import com.example.satisfactorytools.factorycalculator.gameinfo.Machine;
import com.example.satisfactorytools.factorycalculator.gameinfo.Recipe;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.graphic.LabelCircleGraphic;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MachineNode extends NetworkNode {

    private final Machine machine;
    private final Recipe recipe;

    public MachineNode(Machine machine, Recipe recipe) {
        this.machine = machine;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public List<ResourceRate> getOutput() {
        return singleList(recipe.getOutput());
    }

    @Override
    public List<ResourceRate> getInput() {
        return new ArrayList<>(recipe.getInput().getRates());
    }

    @Override
    public String toString() {
        return machine + "{" + recipe.getName() + "}";
    }

    @Override
    public Region createGraphic(Graph graph) {
        return new LabelCircleGraphic(Color.FORESTGREEN, toString());
    }
}
