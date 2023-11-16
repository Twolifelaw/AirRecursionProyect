package Cliente.controlador;

import Cliente.modelo.exceptions.verificarException;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.serializarObjetos;

public class VentanaRegistroController implements Initializable {

    public ArrayList<Cliente> clientes = new ArrayList<>();
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
    private Button btn_atras;
    @FXML
    private Button btn_registrar;
    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblStatus;

    @FXML
    private PasswordField psw_contrasena;
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_correo;
    @FXML
    private TextField txt_direccion;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_numero_telefonico;
    @FXML
    private VBox vText;


    /**
     * Boton que se encarga de toda la parte de registro del cliente.
     *
     * @param event
     */

    @FXML
    void actionRegistrar(ActionEvent event) {
        try {
            nombres = txt_nombre.getText();
            identificacion = txt_id.getText();
            correoElectronico = txt_correo.getText();
            numeroTelefonico = txt_numero_telefonico.getText();
            direccionResidencia = txt_direccion.getText();
            contrasena = psw_contrasena.getText();
            if (nombres.isEmpty() && identificacion.isEmpty() && correoElectronico.isEmpty() && numeroTelefonico.isEmpty() && direccionResidencia.isEmpty() && contrasena.isEmpty()) {
                throw new verificarException("Llene los campos");
            } else if (nombres.isEmpty() || identificacion.isEmpty() || correoElectronico.isEmpty() || numeroTelefonico.isEmpty() || direccionResidencia.isEmpty() || contrasena.isEmpty()) {
                throw new verificarException("Campo vacio llenar porfavor");
            } else {
                if (VentanaUtilidades.verificarIdentificacionRegistrada("clientes.se", identificacion)) {
                    throw new verificarException("identificacion ya registrada.");
                } else if (VentanaUtilidades.verificarCorreoRegistrado("clientes.se", correoElectronico)) {
                    throw new verificarException("correo ya registrada.");
                } else {
                    clientes.add(new Cliente(nombres, "a", identificacion, contrasena, correoElectronico, numeroTelefonico, direccionResidencia));
                    serializarObjetos("clientes.se", clientes);
                    throw new verificarException("Se registró correctamente");
                }
            }
        } catch (verificarException e) {

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
    void actionAtras(ActionEvent event) throws IOException {

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
        TextField[] camposTexto = {txt_nombre, txt_id, txt_correo, txt_numero_telefonico, txt_direccion, psw_contrasena};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btn_registrar.fire();
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btn_registrar);
        VentanaUtilidades.agregarAnimacionBoton(btn_atras);
        inicializarEnterKey();
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_nombre, lblStatus, "Nombre");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_id, lblStatus, "Identificacion");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_correo, lblStatus, "Correo");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_numero_telefonico, lblStatus, "Telefono");
        VentanaUtilidades.agregarEventoYMostrarStatus(txt_direccion, lblStatus, "Dirección");
        VentanaUtilidades.agregarEventoYMostrarStatus(psw_contrasena, lblStatus, "Contraseña");
    }
}
