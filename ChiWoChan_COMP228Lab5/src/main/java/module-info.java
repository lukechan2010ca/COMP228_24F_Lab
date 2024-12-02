module com.example.chiwochan_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.dotenv;


    opens com.example.chiwochan_comp228lab5 to javafx.fxml;
    exports com.example.chiwochan_comp228lab5;
}