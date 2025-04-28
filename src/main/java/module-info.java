module com.example.javafxcit244 {
    requires javafx.controls;
    requires javafx.fxml;
    requires JWiki;
    requires javafx.media;


    opens com.example.javafxcit244 to javafx.fxml;
    exports com.example.javafxcit244;
}