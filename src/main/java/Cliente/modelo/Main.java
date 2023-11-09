package Cliente.modelo;

import Cliente.controlador.VentanaManager;
import Cliente.modelo.objetos.Administrador;
import javafx.application.Application;
import javafx.stage.Stage;
import static Cliente.modelo.Serializacion.GestionAdministradores.*;

import java.io.IOException;
import java.util.ArrayList;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaManager.mostrarVentanaLogin();
    }

    public static void main(String[] args) {

        //VentanaManager.crearAdministrador();
        launch(args);
       // guardarDestinoTuristico("destinos.dat");


    }
}