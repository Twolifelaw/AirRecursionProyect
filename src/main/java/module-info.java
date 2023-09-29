module com.example.airrecursionproyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.codigo.modelo to javafx.fxml;
    opens com.codigo.controlador to javafx.fxml; // Abre el paquete del controlador

    exports com.codigo.modelo;
}
