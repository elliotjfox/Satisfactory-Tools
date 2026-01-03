package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.network.ResourceRate;

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
}
