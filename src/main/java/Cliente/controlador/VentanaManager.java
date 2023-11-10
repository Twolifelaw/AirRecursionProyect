package Cliente.controlador;

import Cliente.modelo.objetos.Administrador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static Cliente.modelo.Serializacion.GestionAdministradores.serializarAdinistrador;

public class VentanaManager {//Esta clase se encargara de cargar todas las ventnaas que sean necesarias.

    public static void mostrarVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(VentanaManager.class.getResource("/com/vista/Login/ventanaLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se encarga de quemar el administrador en este caso el profesor.
     */

    public static void crearAdministrador(){
        ArrayList<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador("Profe","programacion","12332134","1234","profeProgra@gmail.com"));
        serializarAdinistrador("Admins.se",administradores);

    }

}
