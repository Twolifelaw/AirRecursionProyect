package Cliente.modelo;

import Cliente.controlador.VentanaManager;
import Cliente.modelo.objetos.Administrador;
import javafx.application.Application;
import javafx.stage.Stage;
import static Cliente.modelo.Serializacion.GestionAdministradores.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaManager.mostrarVentanaLogin();
    }

    public static void main(String[] args) {
        ArrayList<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador("Profe","programacion","12332134","1234","profeProgra@gmail.com"));
        serializarAdinistrador("Admins.se",administradores);
        launch(args);

    }
}