package com.example.satisfactorytools.graph.layout;

import com.example.satisfactorytools.graph.Graph;
import com.example.satisfactorytools.graph.cell.GraphCell;

import java.util.*;

public class NetworkLayout implements Layout {

    private final List<GraphCell> sourceCells;

    public NetworkLayout(List<GraphCell> sourceCells) {
        this.sourceCells = sourceCells;
    }

    @Override
    public void execute(Graph graph) {
        Map<GraphCell, Integer> column = new HashMap<>();

        Queue<GraphCell> cellQueue = new LinkedList<>(sourceCells);

        for (GraphCell cell : graph.getModel().getAllCells()) {
            column.put(cell, 0);
        }

        while (!cellQueue.isEmpty()) {
            GraphCell cell = cellQueue.poll();
            if (cell.getCellParents().isEmpty() || !column.containsKey(cell.getCellParents().getFirst())) {
                for (GraphCell child : cell.getCellChildren()) {
                    cellQueue.offer(child);
                }
                continue;
            }

            for (GraphCell parent : cell.getCellParents()) {
                if (column.get(parent) + 1 > column.get(cell)) {
                    column.put(cell, column.get(parent) + 1);
                }
            }

            for (GraphCell child : cell.getCellChildren()) {
                cellQueue.offer(child);
            }
        }

        int maxSize = 0;
        int columnIdx = 0;
        List<List<GraphCell>> cellColumns = new ArrayList<>();
        while (true) {
            List<GraphCell> currentColumn = new ArrayList<>();
            for (Map.Entry<GraphCell, Integer> entry : column.entrySet()) {
                if (entry.getValue() == columnIdx) {
                    currentColumn.add(entry.getKey());
                }
            }
            if (currentColumn.isEmpty()) {
                break;
            }
            cellColumns.add(currentColumn);
            maxSize = Math.max(maxSize, currentColumn.size());
            columnIdx++;
            for (GraphCell cell : currentColumn) {
                column.remove(cell);
            }
        }

        for (int i = 0; i < cellColumns.size(); i++) {
            for (int j = 0; j < cellColumns.get(i).size(); j++) {
                graph.getGraphic(cellColumns.get(i).get(j)).relocate(i * 150, (j + (maxSize - cellColumns.get(i).size()) / 2.0) * 125);

            }
        }
    }
}
