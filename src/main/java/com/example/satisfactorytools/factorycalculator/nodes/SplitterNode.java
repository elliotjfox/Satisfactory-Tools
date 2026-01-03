package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;

import java.util.List;

public class SplitterNode extends NetworkNode {

    private final ResourceRate input;
    private final int outputs;

    public SplitterNode(ResourceRate input, int outputs) {
        if (outputs <= 0 || outputs > 3) {
            throw new IllegalArgumentException("The number of outputs must be between 1 and 3! Was: " + outputs);
        }

        this.input = input;
        this.outputs = outputs;
    }

    @Override
    public List<ResourceRate> getOutput() {
        return singleList(new ResourceRate(input.resource(), input.rate() / outputs));
    }

    @Override
    public List<ResourceRate> getInput() {
        return singleList(input);
    }

    @Override
    public String toString() {
        return "Splitter{" + outputs + "}";
    }
}
