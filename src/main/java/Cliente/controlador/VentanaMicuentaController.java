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
    //
    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnGuardar;

    @FXML
    private PasswordField pswContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumeroTelefonico;
    //de aqui para arriba son componentes.

    private Cliente clienteAutenticado = SesionCliente.getClienteAutenticado();//se crea una instancia que va almacenar la informacion del cliente que ingreso.

    /**
     * Accion del botoRegresar(atras).
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onRegresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/VentanaInicio.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Accion de botonGuardarCambios.
     *
     * @param event
     */

    @FXML
    public void onGuardarCambios(ActionEvent event) {
        ArrayList<Cliente> listaClientes = deserializarClientesDesdeArchivo("clientes.se");

        if (listaClientes != null) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(clienteAutenticado.getCedula())) {
                    // Eliminar el cliente existente
                    listaClientes.remove(cliente);

                    // Actualizar los atributos del cliente existente
                    cliente.setNombre(txtNombre.getText());
                    cliente.setCorreo(txtCorreo.getText());
                    cliente.setTelefono(txtNumeroTelefonico.getText());
                    cliente.setDireccionResidencia(txtDireccion.getText());
                    cliente.setContrasena(pswContrasena.getText());
                    lblMensaje.setText("Se actualizaron los datos correctamente!");

                    // Agregar el cliente editado
                    listaClientes.add(cliente);

                    // Romper el bucle ya que ya hemos encontrado y editado el cliente
                    break;
                }
            }

            // Serializar la lista actualizada
            serializarObjetos("clientes.se", listaClientes);
            actualizarCamposTexto();
        }
    }


    /**
     *
     */

    public void actualizarCamposTexto() {
        // Actualiza los campos de texto solo si el clienteAutenticado no es null
        if (clienteAutenticado != null) {
            txtNombre.setText(clienteAutenticado.getNombre());
            txtCorreo.setText(clienteAutenticado.getCorreo());
            txtNumeroTelefonico.setText(clienteAutenticado.getTelefono());
            txtDireccion.setText(clienteAutenticado.getDireccionResidencia());
            pswContrasena.setText(clienteAutenticado.getContrasena());
        }
    }

    /**
     * Metodo que se encarga de validar si todos los campos txt estan llenos para poder dar enter en el botonUsado.
     */

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtNombre, txtCorreo, txtNumeroTelefonico, txtDireccion, pswContrasena};
        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnGuardar.fire();
                }
            });
        }
    }

    /**
     * Metodo que inicia todo lo de la ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btnGuardar);
        VentanaUtilidades.agregarAnimacionBoton(btnAtras);
        actualizarCamposTexto();
        inicializarEnterKey();
        VentanaUtilidades.agregarEventoYMostrarStatus(txtNombre, lblStatus, "Nombre");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtCorreo, lblStatus, "Correo");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtNumeroTelefonico, lblStatus, "Telefono");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtDireccion, lblStatus, "Direccion");
        VentanaUtilidades.agregarEventoYMostrarStatus(pswContrasena, lblStatus, "Contraseña");
    }
}
