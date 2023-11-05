package Cliente.controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
}
