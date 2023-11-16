package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacioClientes;
import Cliente.modelo.objetos.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaRecuperacionController implements Initializable {

    @FXML
    private AnchorPane anc_recuperacion;

    @FXML
    private Button btnRecuperar;

    @FXML
    private Button btn_atras;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCorreo;


    @FXML
    void recuperar(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        boolean correoRegistrado = VentanaUtilidades.verificarCorreoRegistrado("clientes.se", correo);

        if (correoRegistrado) {
            ArrayList<Cliente> clientes = GestionSerializacioClientes.deserializarClientesDesdeArchivo("clientes.se");

            Cliente clienteEncontrado = null;
            for (Cliente cliente : clientes
            ) {
                if (cliente.getCorreo().equals(correo)) {
                    clienteEncontrado = cliente;
                    break;
                }

            }

            if (clienteEncontrado != null) {
                GestionSerializacioClientes.serializarObjetos("clientes.se", clientes);
                lblMensaje.setText("Se ha enviado un correo de recuperacion a: " + correo);
                System.out.println("Éxito" + "Correo enviado" + "Se ha enviado un correo de recuperación a " + correo);
            }
        } else {
            lblMensaje.setText("El correo ingresado no está registrado en nuestra base de datos");
            System.out.println("Error" + "Correo no registrado" + "El correo ingresado no está registrado en nuestra base de datos");
        }

    }

    @FXML
    void regresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btnRecuperar);
        VentanaUtilidades.agregarAnimacionBoton(btn_atras);

    }
}
