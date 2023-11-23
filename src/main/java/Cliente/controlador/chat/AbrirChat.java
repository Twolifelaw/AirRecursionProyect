package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AbrirChat implements Initializable {

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnServer;

    @FXML
    private Button btnDuo;


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
        VentanaUtilidades.abrirChatCliente();
    }

    @FXML
    void onChatServer(ActionEvent event) {
        VentanaUtilidades.mostrarChatServer();
    }

    @FXML
    void onChatServerDuo(ActionEvent event) {
        VentanaUtilidades.abrirChatServer();
        VentanaUtilidades.abrirChatCliente();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btnCliente);
        VentanaUtilidades.agregarAnimacionBoton(btnSalir);
        VentanaUtilidades.agregarAnimacionBoton(btnServer);
        VentanaUtilidades.agregarAnimacionBoton(btnDuo);

    }
}
