package Cliente.controlador;

import Cliente.modelo.GestionClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VentanaRegistroController {


    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_registrar;

    @FXML
    private Label lblMensaje;

    @FXML
    private PasswordField psw_contrasena;

    @FXML
    private TextField txt_Id;

    @FXML
    private TextField txt_correo;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_numero_telefonico;

    @FXML
    void actionRegistrar(ActionEvent event) {

        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.guardarDatosCliente(txt_Id.getText(),txt_nombre.getText(),txt_correo.getText(),txt_numero_telefonico.getText(),txt_direccion.getText(),psw_contrasena.getText());

    }

    @FXML
    void actionAtras(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
