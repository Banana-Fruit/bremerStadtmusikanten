module org.example.bremerstadtmusikanten {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bremerstadtmusikanten to javafx.fxml;
    exports org.example.bremerstadtmusikanten;
}