package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;

public record ResourceRate(ResourceType resource, double rate) {
    @Override
    public String toString() {
        return resource.getResourceName() + " (" + rate + "/min)";
    }
}
