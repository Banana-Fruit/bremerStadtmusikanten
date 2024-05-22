module com.example.ea_guiprog {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.ea_guiprog to javafx.fxml;
    exports bremen;
}