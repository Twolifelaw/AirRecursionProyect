package Cliente.controlador;

import Cliente.modelo.exceptions.VerificarException;
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

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarObjetos;
import static Cliente.modelo.Serializacion.GestionSerializacioClientes.serializarObjetos;

public class VentanaRegistroController implements Initializable {

    public ArrayList<Cliente> clientes = deserializarObjetos("clientes.se");

    /**
     * Variables responsables de realizar todas las validaciones de la ventana.
     */
    String nombres;
    String identificacion;
    String correoElectronico;
    String numeroTelefonico;
    String direccionResidencia;
    //
    String contrasena;

    /**
     * COmponenete que hace uso la ventana
     */

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblStatus;

    @FXML
    private PasswordField pswContrasena;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumeroTelefonico;
    //de aqui parar arriba van componentes.


    /**
     * Boton que se encarga de toda la parte de registro del cliente.
     *
     * @param event
     */

    @FXML
    void onRegistrar(ActionEvent event) {
        try {
            nombres = txtNombre.getText();
            identificacion = txtId.getText();
            correoElectronico = txtCorreo.getText();
            numeroTelefonico = txtNumeroTelefonico.getText();
            direccionResidencia = txtDireccion.getText();
            contrasena = pswContrasena.getText();

            if (nombres.isEmpty() && identificacion.isEmpty() && correoElectronico.isEmpty() && numeroTelefonico.isEmpty() && direccionResidencia.isEmpty() && contrasena.isEmpty()) {
                throw new VerificarException("Llene los campos");
            } else if (nombres.isEmpty() || identificacion.isEmpty() || correoElectronico.isEmpty() || numeroTelefonico.isEmpty() || direccionResidencia.isEmpty() || contrasena.isEmpty()) {
                throw new VerificarException("Campo vacio llenar porfavor");
            } else {
                if (VentanaUtilidades.verificarIdentificacionRegistrada("clientes.se", identificacion)) {
                    throw new VerificarException("identificacion ya registrada.");
                } else if (VentanaUtilidades.verificarCorreoRegistrado("clientes.se", correoElectronico)) {
                    throw new VerificarException("correo ya registrada.");
                } else {
                    if(clientes==null){
                        clientes=new ArrayList<>();
                    }
                    clientes.add(new Cliente(nombres, "a", identificacion, contrasena, correoElectronico, numeroTelefonico, direccionResidencia));
                    serializarObjetos("clientes.se", clientes);
                    throw new VerificarException("Se registró correctamente");
                }
            }
        } catch (VerificarException e) {

            lblMensaje.setText(e.getMessage());
            VentanaUtilidades.mostrarErrorTemporalmente(lblMensaje);
        }


    }

    /**
     * Boton que se encarga de regresar a a la ventana login.
     *
     * @param event
     * @throws IOException
     */

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

    /**
     * Metodo que al oprimir Enter me verifique los campos y en caso de que sean correcto registra.
     */

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtNombre, txtId, txtCorreo, txtNumeroTelefonico, txtDireccion, pswContrasena};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnRegistrar.fire();
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
        VentanaUtilidades.agregarAnimacionBoton(btnRegistrar);
        VentanaUtilidades.agregarAnimacionBoton(btnAtras);
        inicializarEnterKey();
        VentanaUtilidades.agregarEventoYMostrarStatus(txtNombre, lblStatus, "Nombre");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtId, lblStatus, "Identificacion");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtCorreo, lblStatus, "Correo");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtNumeroTelefonico, lblStatus, "Telefono");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtDireccion, lblStatus, "Dirección");
        VentanaUtilidades.agregarEventoYMostrarStatus(pswContrasena, lblStatus, "Contraseña");
    }
}
