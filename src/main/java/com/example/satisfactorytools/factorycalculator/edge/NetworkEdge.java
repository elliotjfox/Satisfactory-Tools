package com.example.satisfactorytools.factorycalculator.edge;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.factorycalculator.node.NetworkNode;

public class NetworkEdge {

    private final NetworkNode source;
    private final NetworkNode target;

    private ResourceRate resourceRate;

    public NetworkEdge(NetworkNode source, NetworkNode target) {
        this.source = source;
        this.target = target;
    }

    public void setResourceRate(ResourceRate resourceRate) {
        this.resourceRate = resourceRate;
    }

    public void setResourceRate(ResourceType resourceType, double rate) {
        this.resourceRate = new ResourceRate(resourceType, rate);
    }

    public NetworkNode getSource() {
        return source;
    }

    public NetworkNode getTarget() {
        return target;
    }

    public ResourceRate getResourceRate() {
        return resourceRate;
    }
}
