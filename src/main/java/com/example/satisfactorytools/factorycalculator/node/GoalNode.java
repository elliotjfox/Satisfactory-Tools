package com.example.satisfactorytools.factorycalculator.node;

import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.graphic.LabelCircleGraphic;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GoalNode extends NetworkNode {
    private final ResourceRate goal;

    public GoalNode(ResourceRate goal) {
        this.goal = goal;
    }

    @Override
    public List<ResourceRate> getOutput() {
        return new ArrayList<>();
    }

    @Override
    public List<ResourceRate> getInput() {
        return singleList(goal);
    }

    @Override
    public String toString() {
        return "GoalNode{" + goal + "}";
    }

    @Override
    public Region createGraphic(Graph graph) {
        return new LabelCircleGraphic(Color.DODGERBLUE, toString());
    }
}
