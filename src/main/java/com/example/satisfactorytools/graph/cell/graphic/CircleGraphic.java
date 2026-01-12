package com.example.satisfactorytools.graph.cell.graphic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleGraphic extends GraphCellGraphic {

    private final double radius;
    private final Color fill;
    private final Circle circle;

    public CircleGraphic() {
        this(50, Color.WHITE);
    }

    public CircleGraphic(Color fill) {
        this(50, fill);
    }

    public CircleGraphic(double radius, Color fill) {
        super();

        this.radius = radius;
        this.fill = fill;

        this.circle = new Circle(radius, fill);
        circle.relocate(0, 0);

        group.getChildren().add(circle);
    }
}
