package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.cell.GraphCell;

import java.util.ArrayList;
import java.util.List;

public abstract class NetworkNode extends GraphCell {
    public abstract List<ResourceRate> getOutput();
    public abstract List<ResourceRate> getInput();

    protected static List<ResourceRate> singleList(ResourceRate resourceRate) {
        List<ResourceRate> list = new ArrayList<>();
        list.add(resourceRate);
        return list;
    }
}
