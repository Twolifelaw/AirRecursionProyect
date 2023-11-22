package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class AbrirChatApp extends Application{
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        VentanaUtilidades.mostrarChat();
    }
}


