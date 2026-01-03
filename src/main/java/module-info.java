module com.example.satisfactorytools {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.satisfactorytools to javafx.fxml;
    exports com.example.satisfactorytools;
    exports com.example.satisfactorytools.factorycalculator.nodes;
    opens com.example.satisfactorytools.factorycalculator.nodes to javafx.fxml;
    exports com.example.satisfactorytools.factorycalculator.network;
    opens com.example.satisfactorytools.factorycalculator.network to javafx.fxml;
    exports com.example.satisfactorytools.factorycalculator.gameinfo;
    opens com.example.satisfactorytools.factorycalculator.gameinfo to javafx.fxml;
    exports com.example.satisfactorytools.graph;
    opens com.example.satisfactorytools.graph to javafx.fxml;
    exports com.example.satisfactorytools.graph.cell;
    opens com.example.satisfactorytools.graph.cell to javafx.fxml;
    exports com.example.satisfactorytools.graph.edge;
    opens com.example.satisfactorytools.graph.edge to javafx.fxml;
    exports com.example.satisfactorytools.graph.layout;
    opens com.example.satisfactorytools.graph.layout to javafx.fxml;
    exports com.example.satisfactorytools.graph.gesture;
    opens com.example.satisfactorytools.graph.gesture to javafx.fxml;
    exports com.example.satisfactorytools.graph.edge.graphic;
    opens com.example.satisfactorytools.graph.edge.graphic to javafx.fxml;
}