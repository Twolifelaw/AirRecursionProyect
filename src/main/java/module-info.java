module com.example.airrecursionproyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens Cliente.modelo to javafx.fxml;
    opens Cliente.controlador to javafx.fxml;
    opens Cliente.modelo.Serializacion to javafx.fxml;
    opens Cliente to javafx.fxml;
    opens Cliente.modelo.objetos to javafx.fxml;

    exports Cliente.modelo;
    exports Cliente.modelo.Serializacion;
    exports Cliente.modelo.objetos;
    exports Cliente.controlador; // Exporta el paquete del controlador
    exports Cliente.controlador.chat;
    opens Cliente.controlador.chat to javafx.fxml;

}