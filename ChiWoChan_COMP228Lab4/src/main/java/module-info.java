module com.example.chiwochan_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.chiwochan_comp228lab4 to javafx.fxml;
    exports com.example.chiwochan_comp228lab4;
}