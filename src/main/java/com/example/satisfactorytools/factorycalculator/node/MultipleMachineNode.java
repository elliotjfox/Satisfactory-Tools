package com.example.satisfactorytools.factorycalculator.node;

import com.example.satisfactorytools.factorycalculator.edge.NetworkEdge;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleMachineNode extends MachineNode implements MultipleNode {

    private double multiplier;

    private MultipleMachineGraphic graphic;

    private Map<ResourceRate, List<NetworkEdge>> inputs;

    public MultipleMachineNode(Machine machine, Recipe recipe, double multiplier) {
        super(machine, recipe);
        this.multiplier = multiplier;

        inputs = new HashMap<>();
    }

    @Override
    public void addInEdge(NetworkEdge edge) {
        super.addInEdge(edge);
    }

    @Override
    public void removeInEdge(NetworkEdge edge) {
        super.removeInEdge(edge);
    }

    public boolean allRequirementsFilled() {
        return false;
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
        graphic = new MultipleMachineGraphic(Color.FORESTGREEN, toString(), multiplier);
        return graphic;
    }

    @Override
    public NetworkNode getNetworkNode() {
        return this;
    }

    @Override
    public void updateMultiplier(double newMultiplier) {
        this.multiplier = newMultiplier;
        if (graphic != null) {
            graphic.setMultiplier(this.multiplier);
        }
    }

    @Override
    public void addMultiplier(double addition) {
        this.multiplier += addition;
        if (graphic != null) {
            graphic.setMultiplier(this.multiplier);
        }
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

        private void setMultiplier(double multiplier) {
            multiplierLabel.setText("x" + multiplier);
        }
    }
}
