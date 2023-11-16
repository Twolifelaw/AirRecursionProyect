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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;
import static Cliente.modelo.Serializacion.GestionSerializacioClientes.serializarObjetos;

public class VentanaMicuentaController implements Initializable {

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btn_atras;

    @FXML
    private Button btn_guardar;

    @FXML
    private PasswordField psw_contrasena;

    @FXML
    private TextField txt_correo;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_numero_telefonico;


    private Cliente clienteAutenticado = SesionCliente.getClienteAutenticado();

    @FXML
    void actionAtras(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaInicio.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void cambiarDatos(ActionEvent event) {
        ArrayList<Cliente> listaClientes = deserializarClientesDesdeArchivo("clientes.se");

        if (listaClientes != null) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(clienteAutenticado.getCedula())) {
                    cliente.setNombre(txt_nombre.getText());
                    cliente.setCorreo(txt_correo.getText());
                    cliente.setTelefono(txt_numero_telefonico.getText());
                    cliente.setDireccionResidencia(txt_direccion.getText());
                    cliente.setContrasena(psw_contrasena.getText());
                    lblMensaje.setText("se actualizo sus datos correctamente!");
                    clienteAutenticado = cliente;
                    break;
                }
            }
            serializarObjetos("clientes.se", listaClientes);
            actualizarCamposTexto();
        }

    }

    /**
     * Metodo que pense que servia para actualizar los textField pero no.
     */
    public void actualizarCamposTexto() {
        // Actualiza los campos de texto solo si el clienteAutenticado no es null
        if (clienteAutenticado != null) {
            txt_nombre.setText(clienteAutenticado.getNombre());
            txt_correo.setText(clienteAutenticado.getCorreo());
            txt_numero_telefonico.setText(clienteAutenticado.getTelefono());
            txt_direccion.setText(clienteAutenticado.getDireccionResidencia());
            psw_contrasena.setText(clienteAutenticado.getContrasena());
        }
    }

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txt_nombre, txt_correo, txt_numero_telefonico, txt_direccion, psw_contrasena};
        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btn_guardar.fire();
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btn_guardar);
        VentanaUtilidades.agregarAnimacionBoton(btn_atras);
        actualizarCamposTexto();
        inicializarEnterKey();
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_nombre, lblStatus, "Nombre");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_correo, lblStatus, "Correo");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_numero_telefonico, lblStatus, "Telefono");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_direccion, lblStatus, "Direccion");
        VentanaUtilidades.agregarEventoYMostrarStatus(psw_contrasena, lblStatus, "Contraseña");
    }
}
