package Cliente.modelo;

import Cliente.controlador.VentanaLoginController;
import Cliente.controlador.VentanaManager;
import Cliente.modelo.objetos.Empleado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaManager.mostrarVentanaLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}