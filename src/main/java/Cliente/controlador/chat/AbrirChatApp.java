package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
import javafx.application.Application;
import javafx.stage.Stage;

public class AbrirChatApp extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        VentanaUtilidades.mostrarChat();
    }
}


