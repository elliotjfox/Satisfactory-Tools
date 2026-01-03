package com.example.satisfactorytools.factorycalculator.network;

import java.util.ArrayList;
import java.util.List;

public class RecipeInput {

    private final List<ResourceRate> rates;

    public RecipeInput(ResourceRate... rates) {
        this.rates = new ArrayList<>(List.of(rates));
    }

    public List<ResourceRate> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (rates.isEmpty()) {
            return s.toString();
        }

        s.append(rates.getFirst().toString());

        for (int i = 1; i < rates.size(); i++) {
            s.append(", ").append(rates.get(i).toString());
        }

        return s.toString();
    }
}
