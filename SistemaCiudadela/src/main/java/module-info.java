module com.mycompany.sistemaciudadela {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.sistemaciudadela to javafx.fxml;
    exports com.mycompany.sistemaciudadela;
}
