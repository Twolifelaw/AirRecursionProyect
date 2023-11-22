package Cliente.controlador;

import Cliente.modelo.Serializacion.SesionCliente;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;
import static Cliente.modelo.Serializacion.GestionSerializacioClientes.serializarObjetos;

public class VentanaContrasenaController implements Initializable {

    @FXML
    private AnchorPane anchorPaneRecuperar;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnRecuperar;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtContrasena2;

    private Cliente clienteAutenticado = SesionCliente.getClienteAutenticado();

    @FXML
    void onRecuperar(ActionEvent event) {
        String contrasena1 = txtContrasena.getText();
        String contrasena2 = txtContrasena2.getText();

        if ( contrasena1.isEmpty() && contrasena2.isEmpty()) {
            lblStatus.setText("campos vacios. Llenar porfavor");
        }else if (  contrasena1.isEmpty() || contrasena2.isEmpty()){
            lblStatus.setText("Uno de los campos Vacios. LLenar porfavor");
        }else {
            if (contrasena1.equals(contrasena2)) {
                ArrayList<Cliente> listaClientes = deserializarClientesDesdeArchivo("clientes.se");

                if (listaClientes != null) {
                    for (Cliente cliente : listaClientes) {
                        // Aquí puedes verificar si el cliente coincide con el cliente autenticado
                        if (cliente.getCedula().equals(clienteAutenticado.getCedula())) {
                            // Actualizar la contraseña del cliente
                            cliente.setContrasena(contrasena1);
                            lblStatus.setText("Se actualizó la contraseña correctamente.");
                            break;
                        }
                    }

                    // Guardar la lista actualizada
                    serializarObjetos("clientes.se", listaClientes);

                    // Puedes agregar cualquier lógica adicional aquí, si es necesario.
                }
            } else {
                lblStatus.setText("Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
            }

        }

    }

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtContrasena,txtContrasena2};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnRecuperar.fire();
                }
            });
        }
    }

    @FXML
    void onRegresar(ActionEvent event) throws IOException {
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
        VentanaUtilidades.agregarEventoYMostrarStatus(txtContrasena,lblStatus, "contraseñaNueva");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtContrasena2, lblStatus, "RecontraseñaNueva");
        VentanaUtilidades.agregarAnimacionBoton(btnRecuperar);
        VentanaUtilidades.agregarAnimacionBoton(btnAtras);
        inicializarEnterKey();

    }
}
