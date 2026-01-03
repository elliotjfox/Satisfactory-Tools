package com.example.satisfactorytools.factorycalculator.gameinfo;

import java.util.ArrayList;
import java.util.List;

public enum ResourceType {
    IRON_ORE("Iron Ore"),
    IRON_INGOT("Iron Ingot"),
    COPPER_INGOT("Copper Ingot"),
    IRON_PLATE("Iron Plate"),
    IRON_ROD("Iron Rod"),
    SCREWS("Screws"),
    REINFORCED_IRON_PLATE("Reinforced Iron Plate"),
    STEEL_BEAM("Steel Bean"),
    COPPER_ORE("Copper Ore"),
    PLASTIC("Plastic"),
    STEEL_INGOT("Steel Ingot"),
//    COPPER_WIRE("Copper Wire", null),
//    CABLE("Cable", null),
//    COPPER_SHEET("Copper Sheet", null),
    ;

    private final String resourceName;
    private Recipe defaultRecipe;
    private List<Recipe> alternateRecipes;

    ResourceType(String resourceName) {
        this.resourceName = resourceName;
        this.defaultRecipe = null;
        this.alternateRecipes = new ArrayList<>();
    }

    public String getResourceName() {
        return resourceName;
    }

    public Recipe getDefaultRecipe() {
        return defaultRecipe;
    }

    public List<Recipe> getAlternateRecipes() {
        return alternateRecipes;
    }

    private void setRecipes(Recipe defaultRecipe, Recipe... alternateRecipes) {
        this.defaultRecipe = defaultRecipe;
        this.alternateRecipes = new ArrayList<>(List.of(alternateRecipes));
    }

    // Initialize recipes
    static {
        IRON_INGOT.setRecipes(Recipe.IRON_INGOT);

        IRON_PLATE.setRecipes(Recipe.IRON_PLATE, Recipe.COATED_IRON_PLATE, Recipe.STEEL_CAST_PLATE);
        
        IRON_ROD.setRecipes(Recipe.IRON_ROD);
        SCREWS.setRecipes(Recipe.SCREWS, Recipe.CAST_SCREWS, Recipe.STEEL_SCREWS);
        REINFORCED_IRON_PLATE.setRecipes(Recipe.REINFORCED_IRON_PLATE);
    }
}
