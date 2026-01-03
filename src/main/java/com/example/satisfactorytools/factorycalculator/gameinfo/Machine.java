package com.example.satisfactorytools.factorycalculator.gameinfo;

public enum Machine {
    MINER("Miner"),
    CONSTRUCTOR("Constructor"),
    ASSEMBLER("Assembler"),
    FOUNDRY("Foundry"),
    SMELTER("Smelter"),
    ;

    private final String machineName;

    Machine(String machineName) {
        this.machineName = machineName;
    }

    @Override
    public String toString() {
        return machineName;
    }
}
