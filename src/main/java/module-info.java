module com.example.airrecursionproyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens Cliente.modelo to javafx.fxml;
    opens Cliente.controlador to javafx.fxml; // Abre el paquete del controlador

    exports Cliente.modelo;
}
