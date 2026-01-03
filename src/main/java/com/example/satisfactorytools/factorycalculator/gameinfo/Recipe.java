package com.example.satisfactorytools.factorycalculator.gameinfo;

import com.example.satisfactorytools.factorycalculator.network.RecipeInput;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;

public enum Recipe {
    IRON_INGOT(
            "Iron Ingot",
            new ResourceRate(ResourceType.IRON_ORE, 30),
            new ResourceRate(ResourceType.IRON_INGOT, 30),
            Machine.SMELTER
    ),
    // Iron Plate Recipes
    IRON_PLATE(
            "Iron Plate",
            new ResourceRate(ResourceType.IRON_INGOT, 30),
            new ResourceRate(ResourceType.IRON_PLATE, 20),
            Machine.CONSTRUCTOR
    ),
    COATED_IRON_PLATE(
            "Coated Iron Plate",
            new RecipeInput(
                    new ResourceRate(ResourceType.IRON_INGOT, 37.5),
                    new ResourceRate(ResourceType.PLASTIC, 7.5)
            ),
            new ResourceRate(ResourceType.IRON_PLATE, 75),
            Machine.ASSEMBLER
    ),
    STEEL_CAST_PLATE(
            "Steel Cast Plate",
            new RecipeInput(
                    new ResourceRate(ResourceType.IRON_INGOT, 15),
                    new ResourceRate(ResourceType.STEEL_INGOT, 15)
            ),
            new ResourceRate(ResourceType.IRON_PLATE, 45),
            Machine.FOUNDRY
    ),

    IRON_ROD(
            "Iron Rod",
            new ResourceRate(ResourceType.IRON_INGOT, 15),
            new ResourceRate(ResourceType.IRON_ROD, 15),
            Machine.CONSTRUCTOR
    ),

    // Screw Recipes
    SCREWS(
            "Screws",
            new ResourceRate(ResourceType.IRON_ROD, 10),
            new ResourceRate(ResourceType.SCREWS, 40),
            Machine.CONSTRUCTOR
    ),
    CAST_SCREWS(
            "Cast Screws",
            new ResourceRate(ResourceType.IRON_INGOT, 12.5),
            new ResourceRate(ResourceType.SCREWS, 50),
            Machine.CONSTRUCTOR
    ),
    STEEL_SCREWS(
            "Steel Screws",
            new ResourceRate(ResourceType.STEEL_BEAM, 5),
            new ResourceRate(ResourceType.SCREWS, 260),
            Machine.CONSTRUCTOR
    ),

    REINFORCED_IRON_PLATE(
            "Reinforced Iron Plate",
            new RecipeInput(
                    new ResourceRate(ResourceType.IRON_PLATE, 30),
                    new ResourceRate(ResourceType.SCREWS, 60)
            ),
            new ResourceRate(ResourceType.REINFORCED_IRON_PLATE, 5),
            Machine.ASSEMBLER
    ),
    COPPER_INGOT(
            "Copper Ingot",
            new ResourceRate(ResourceType.COPPER_ORE, 30),
            new ResourceRate(ResourceType.COPPER_INGOT, 30),
            Machine.SMELTER
    ),
    ;

    private final String name;
    private final RecipeInput input;
    private final ResourceRate output;
    private final Machine machine;

    Recipe(String name, RecipeInput input, ResourceRate output, Machine machine) {
        this.name = name;
        this.input = input;
        this.output = output;
        this.machine = machine;
    }

    Recipe(String name, ResourceRate input, ResourceRate output, Machine machine) {
        this.name = name;
        this.input = new RecipeInput(input);
        this.output = output;
        this.machine = machine;
    }

    public String getName() {
        return name;
    }

    public RecipeInput getInput() {
        return input;
    }

    public ResourceRate getOutput() {
        return output;
    }

    public Machine getMachine() {
        return machine;
    }

    @Override
    public String toString() {
        return input.toString() + " -> " + output.toString() + " (" + machine.toString() + ")";
    }
}
