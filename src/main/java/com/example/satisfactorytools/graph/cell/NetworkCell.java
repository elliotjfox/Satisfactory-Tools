package com.example.satisfactorytools.graph.cell;

import com.example.satisfactorytools.factorycalculator.network.Network;
import com.example.satisfactorytools.graph.Graph;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class NetworkCell extends GraphCell {

    private final StringProperty textProperty;
    private String cellName = null;

    public NetworkCell() {
        this("");
    }

    public NetworkCell(String text) {
        textProperty = new SimpleStringProperty();
        textProperty.set(text);
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    @Override
    public String toString() {
        return cellName == null ? super.toString() : cellName;
    }

    @Override
    public Region createGraphic(Graph graph) {
        Circle circle = new Circle(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.DODGERBLUE);

        Label label = new Label();
        label.textProperty().bind(textProperty);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setPrefSize(100, 100);

        Pane pane = new Pane();
        pane.setPrefSize(100, 100);
        pane.getChildren().add(circle);
        pane.getChildren().add(label);
        circle.relocate(0, 0);


        return pane;
    }
}
