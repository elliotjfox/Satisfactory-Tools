package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.graphic.LabelCircleGraphic;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.List;

public class MergerNode extends NetworkNode {

    private final ResourceRate input1;
    private final ResourceRate input2;
    private final ResourceRate input3;
    private final int numInputs;

    public MergerNode(ResourceRate input1, ResourceRate input2) {
        if (input1.resource() != input2.resource()) {
            throw new IllegalArgumentException("Resources must be of the same type! " + input1 + ", " + input2);
        }

        this.input1 = input1;
        this.input2 = input2;
        this.input3 = null;
        numInputs = 2;
    }

    public MergerNode(ResourceRate input1, ResourceRate input2, ResourceRate input3) {
        if (input1.resource() != input2.resource() || input1.resource() != input3.resource()) {
            throw new IllegalArgumentException("Resources must be of the same type! " + input1 + ", " + input2 + ", " + input3);
        }

        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        numInputs = 3;
    }

    @Override
    public List<ResourceRate> getOutput() {
        if (numInputs == 2) {
            return List.of(input1, input2);
        } else {
            return List.of(input1, input2, input3);
        }
    }

    @Override
    public List<ResourceRate> getInput() {
        if (numInputs == 2) {
            return List.of(new ResourceRate(input1.resource(), input1.rate() + input2.rate()));
        } else {
            return List.of(new ResourceRate(input1.resource(), input1.rate() + input2.rate() + input3.rate()));
        }
    }

    @Override
    public String toString() {
        return "Merger{" + numInputs + "}";
    }

    @Override
    public Region createGraphic(Graph graph) {
        return new LabelCircleGraphic(Color.LIGHTSTEELBLUE, toString());
    }
}
