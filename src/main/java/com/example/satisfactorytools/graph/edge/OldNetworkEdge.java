package com.example.satisfactorytools.graph.edge;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;
import com.example.satisfactorytools.graph.edge.graphic.DirectedEdgeToCircleGraphic;
import com.example.satisfactorytools.graph.edge.graphic.GraphEdgeGraphic;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;

public class OldNetworkEdge extends GraphEdge {

    private final String labelText;

    public OldNetworkEdge(GraphCell source, GraphCell target, String labelText) {
        super(source, target, true);
        this.labelText = labelText;
    }

    @Override
    public Region createGraphic(Graph graph) {
        GraphEdgeGraphic graphic = new NetworkEdgeGraphic(50, labelText);

        DoubleBinding sourceX = this.getSource().getXAnchor(graph);
        DoubleBinding sourceY = this.getSource().getYAnchor(graph);
        DoubleBinding targetX = this.getTarget().getXAnchor(graph);
        DoubleBinding targetY = this.getTarget().getYAnchor(graph);

        graphic.setupGraphic(sourceX, sourceY, targetX, targetY);

        return graphic;
    }

    private static class NetworkEdgeGraphic extends DirectedEdgeToCircleGraphic {

        private final Label label;

        public NetworkEdgeGraphic(double radius) {
            this(radius, "");
        }

        public NetworkEdgeGraphic(double radius, String labelText) {
            super(radius);

            this.label = new Label(labelText);
        }

        @Override
        protected void setupArrow(DoubleBinding sourceX, DoubleBinding sourceY, DoubleBinding targetX, DoubleBinding targetY) {
            super.setupArrow(sourceX, sourceY, targetX, targetY);

            Pane labelPane = new Pane(label);

            labelPane.layoutXProperty().bind(Bindings.createDoubleBinding(
                    () -> getTextPosition(
                            new Point2D(sourceX.get(), sourceY.get()),
                            new Point2D(targetX.get(), targetY.get()),
                            new Point2D(label.widthProperty().get(), labelPane.heightProperty().get())
                    ).getX(),
                    sourceX, sourceY, targetX, targetY, label.widthProperty(), label.heightProperty())
            );
            labelPane.layoutYProperty().bind(Bindings.createDoubleBinding(
                    () -> getTextPosition(
                            new Point2D(sourceX.get(), sourceY.get()),
                            new Point2D(targetX.get(), targetY.get()),
                            new Point2D(label.widthProperty().get(), labelPane.heightProperty().get())
                    ).getY(),
                    sourceX, sourceY, targetX, targetY, label.widthProperty(), label.heightProperty())
            );

            Rotate rotation = new Rotate();
            rotation.angleProperty().bind(Bindings.createDoubleBinding(
                    () -> Math.toDegrees(getAngle(
                            new Point2D(sourceX.get(), sourceY.get()),
                            new Point2D(targetX.get(), targetY.get())
                    )), sourceX, sourceY, targetX, targetY
            ));

            labelPane.getTransforms().add(rotation);
            label.relocate(0, 0);

            group.getChildren().add(labelPane);
        }

        private double getAngle(Point2D source, Point2D target) {
            Point2D vector = target.subtract(source);
            return Math.atan2(vector.getY(), vector.getX());
        }

        private Point2D getTextPosition(Point2D source, Point2D target, Point2D textDimensions) {
            double textHeight = textDimensions.getY();
            double textWidth = textDimensions.getX();
            Point2D vector = target.subtract(source);
            Point2D center = source.add(target).multiply(0.5);
            Point2D normalizedVector = vector.normalize();
            Point2D orthogonal = new Point2D(normalizedVector.getY(), -normalizedVector.getX());

            Point2D top = center.add(orthogonal.multiply(textHeight));

            return top.subtract(normalizedVector.multiply(textWidth / 2));
        }
    }
}
