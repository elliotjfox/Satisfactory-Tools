package com.example.satisfactorytools.graph.layout;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;

import java.util.List;
import java.util.Random;

public class RandomLayout implements Layout {

    private final Random r = new Random();

    @Override
    public void execute(Graph graph) {
        List<GraphCell> cells = graph.getModel().getAllCells();

        for (GraphCell cell : cells) {
            double x = r.nextDouble() * 500;
            double y = r.nextDouble() * 500;

            graph.getGraphic(cell).relocate(x, y);
        }
    }
}
