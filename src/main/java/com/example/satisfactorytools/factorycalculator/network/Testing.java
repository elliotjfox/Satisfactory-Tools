package com.example.satisfactorytools.factorycalculator.network;

import com.example.satisfactorytools.factorycalculator.gameinfo.ResourceType;

public class Testing {

    public static void main(String... args) {
        Network network = NetworkBuilder.buildNetwork(new ResourceRate(ResourceType.REINFORCED_IRON_PLATE, 10));
        System.out.println(network.toAdjacencyListString());
    }
}
