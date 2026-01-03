package com.example.satisfactorytools.graph.gesture;

import com.example.satisfactorytools.graph.Graph;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class NodeGestures {

    private final DragContext dragContext = new DragContext();
    private final Graph graph;
    private MouseButton dragButton = MouseButton.PRIMARY;

    private final EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            Node node = (Node) event.getSource();

            double scale = graph.getScale();

            dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
            dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getScreenY();

            event.consume();
        }
    };

    private final EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            Node node = (Node) event.getSource();

            if (event.getButton() == getDragButton()) {
                double offsetX = event.getScreenX() + dragContext.x;
                double offsetY = event.getScreenY() + dragContext.y;

                // adjust the offset in case we are zoomed
                double scale = graph.getScale();

                offsetX /= scale;
                offsetY /= scale;

                node.relocate(offsetX, offsetY);

                // only consume if target button.
                // allows for "pass-through" of events to parent when not the target button.
                event.consume();
            }
        }
    };

    public NodeGestures(Graph graph) {
        this.graph = graph;
    }

    public void setDragButton(MouseButton dragButton) {
        this.dragButton = dragButton;
    }

    public MouseButton getDragButton() {
        return dragButton;
    }

    public void makeDraggable(Node node) {
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
    }

    public void makeUndraggable(Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
    }

    private static class DragContext {
        double x;
        double y;
    }
}
