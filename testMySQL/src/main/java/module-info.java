module model.testmysql {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    requires javafx.web;

    opens model.testmysql to javafx.fxml;
    exports model.testmysql;

    opens Controller to javafx.fxml;
    exports Controller;
}
