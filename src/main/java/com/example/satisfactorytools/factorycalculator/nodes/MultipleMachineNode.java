package com.example.satisfactorytools.factorycalculator.nodes;

import com.example.satisfactorytools.factorycalculator.gameinfo.Machine;
import com.example.satisfactorytools.factorycalculator.gameinfo.Recipe;
import com.example.satisfactorytools.factorycalculator.network.ResourceRate;
import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.graphic.LabelCircleGraphic;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class MultipleMachineNode extends MachineNode implements MultipleNode {

    private double multiplier;

    public MultipleMachineNode(Machine machine, Recipe recipe, double multiplier) {
        super(machine, recipe);
        this.multiplier = multiplier;
    }

    @Override
    public List<ResourceRate> getOutput() {
        return getRecipe().getInput().getRates().stream().map(resourceRate -> new ResourceRate(resourceRate.resource(), resourceRate.rate() * multiplier)).toList();
    }

    @Override
    public List<ResourceRate> getInput() {
        return List.of(new ResourceRate(getRecipe().getOutput().resource(), getRecipe().getOutput().rate() * multiplier));
    }

    @Override
    public Region createGraphic(Graph graph) {
        return new MultipleMachineGraphic(Color.FORESTGREEN, toString(), multiplier);
    }

    @Override
    public NetworkNode getNetworkNode() {
        return this;
    }

    @Override
    public void updateMultiplier(double newMultiplier) {
        this.multiplier = newMultiplier;
    }

    @Override
    public void addMultiplier(double addition) {
        this.multiplier += addition;
    }

    private static class MultipleMachineGraphic extends LabelCircleGraphic {

        private final Label multiplierLabel;

        public MultipleMachineGraphic(Color fill, String text, double multiplier) {
            this(50, fill, text, multiplier);
        }

        public MultipleMachineGraphic(double radius, Color fill, String text, double multiplier) {
            super(radius, fill, text);

            multiplierLabel = new Label();
            multiplierLabel.setText("x" + multiplier);
            multiplierLabel.setTextAlignment(TextAlignment.CENTER);
            multiplierLabel.setAlignment(Pos.CENTER);
            multiplierLabel.setWrapText(false);
            multiplierLabel.setPrefSize(25, 25);
            multiplierLabel.relocate(-12.5, 12.5);

            group.getChildren().add(multiplierLabel);
        }
    }
}
