package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AbrirChat {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnServer;

    @FXML
    private AnchorPane anchorPaneChats;


    @FXML
    void OnCerrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/VentanaInicio.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();


    }

    @FXML
    void onChatCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(VentanaUtilidades.class.getResource("/com/vista/ventanas/ChatNuevo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onChatServer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(VentanaUtilidades.class.getResource("/com/vista/ventanas/ChatServer.fxml"));
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
