package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.graphic.LabelCircleGraphic;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SourceNode extends NetworkNode implements MultipleNode {

    private ResourceRate resourceRate;

    public SourceNode(ResourceRate resourceRate) {
        this.resourceRate = resourceRate;
    }

    public SourceNode(ResourceType resource, double rate) {
        this(new ResourceRate(resource, rate));
    }

    @Override
    public NetworkNode getNetworkNode() {
        return this;
    }

    @Override
    public void updateMultiplier(double newRate) {
        resourceRate = new ResourceRate(resourceRate.resource(), newRate);
    }

    @Override
    public void addMultiplier(double addition) {
        resourceRate = new ResourceRate(resourceRate.resource(), resourceRate.rate() + addition);
    }

    public ResourceType getResourceType() {
        return resourceRate.resource();
    }

    public double getRate() {
        return resourceRate.rate();
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

    @Override
    public Region createGraphic(Graph graph) {
        return new LabelCircleGraphic(Color.LIGHTGREEN, toString());
    }
}
