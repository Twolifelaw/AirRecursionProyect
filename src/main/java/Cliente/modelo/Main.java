package Cliente.modelo;

import Cliente.controlador.VentanaManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaManager.mostrarVentanaLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}