package com.example.satisfactorytools.graph.edge.graphic;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point2D;

import java.util.Optional;

public class DirectedEdgeToCircleGraphic extends DirectedGraphEdgeGraphic {
    private final double radius;

    public DirectedEdgeToCircleGraphic(double radius) {
        this.radius = radius;
    }

    @Override
    public void setupGraphic(DoubleBinding sourceX, DoubleBinding sourceY, DoubleBinding targetX, DoubleBinding targetY) {
        DoubleBinding targetXFitted = Bindings.createDoubleBinding(() -> getIntercept(
                new Point2D(sourceX.get(), sourceY.get()),
                new Point2D(targetX.get(), targetY.get()),
                radius).orElse(new Point2D(targetX.get(), targetY.get())).getX(),
            targetX, targetY, sourceX, sourceY);
        DoubleBinding targetYFitted = Bindings.createDoubleBinding(() -> getIntercept(
                new Point2D(sourceX.get(), sourceY.get()),
                new Point2D(targetX.get(), targetY.get()),
                radius).orElse(new Point2D(targetX.get(), targetY.get())).getY(),
            targetX, targetY, sourceX, sourceY);

        setupArrow(sourceX, sourceY, targetXFitted, targetYFitted);
    }

    private Optional<Point2D> getIntercept(Point2D source, Point2D target, double radius) {
        double distance = source.distance(target);
        if (distance <= radius || distance == 0) {
            return Optional.empty();
        }

        double fractionX = (target.getX() - source.getX()) / distance;
        double fractionY = (target.getY() - source.getY()) / distance;

        return Optional.of(
                new Point2D(
                        target.getX() - fractionX * radius,
                        target.getY() - fractionY * radius
                )
        );
    }

}
