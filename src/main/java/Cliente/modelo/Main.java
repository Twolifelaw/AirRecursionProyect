package Cliente.modelo;

import Cliente.controlador.VentanaUtilidades;
import Cliente.modelo.Serializacion.GestionPaquetes;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.guardarDestinoTuristico;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaUtilidades.mostrarVentanaLogin();
    }

    public static void main(String[] args) {

        //VentanaUtilidades.crearAdministrador();
        launch(args);
       //guardarDestinoTuristico("destinos.dat");
        //GestionPaquetes.crearPaquetePrueba();


    }
}