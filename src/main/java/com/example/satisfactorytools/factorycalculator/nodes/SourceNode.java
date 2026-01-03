package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.network.ResourceRate;

import java.util.ArrayList;
import java.util.List;

public class SourceNode extends NetworkNode {

    private final ResourceRate resourceRate;

    public SourceNode(ResourceRate resourceRate) {
        this.resourceRate = resourceRate;
    }

    @Override
    public List<ResourceRate> getOutput() {
        return singleList(resourceRate);
    }

    @Override
    public List<ResourceRate> getInput() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SourceNode{" + resourceRate + "}";
    }
}
