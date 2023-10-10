package Cliente.controlador;

import Cliente.exceptions.verificarException;
import Cliente.modelo.GestionClientes;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class VentanaRegistroController {

    /**
     * Variables responsables de realizar todas las validaciones de la ventana.
     */
    String nombres;
    String identificacion;

    String correoElectronico;

    String numeroTelefonico;

    String direccionResidencia;

    String ccontrasena;
    //

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
    //

    /**
     * Boton que se encarga de toda la parte de registro del cliente.
     *
     * @param event
     */

    @FXML
    void actionRegistrar(ActionEvent event) {

        try {
            nombres = txt_nombre.getText();
            identificacion = txt_Id.getText();
            correoElectronico = txt_correo.getText();
            numeroTelefonico = txt_numero_telefonico.getText();
            direccionResidencia = txt_direccion.getText();
            ccontrasena = psw_contrasena.getText();
            if (nombres.isEmpty() && identificacion.isEmpty() && correoElectronico.isEmpty() && numeroTelefonico.isEmpty() && direccionResidencia.isEmpty() && ccontrasena.isEmpty()) {
                throw new verificarException("Llene los campos");
            } else if (nombres.isEmpty() || identificacion.isEmpty() || correoElectronico.isEmpty() || numeroTelefonico.isEmpty() || direccionResidencia.isEmpty() || ccontrasena.isEmpty()) {
                throw new verificarException("Campo vacio llenar porfavor");
            } else {
                if (verificarIdentificacionRegistrada(identificacion)) {
                    throw new verificarException("La identificacion ya esta registrada");
                } else if (verificarCorreoRegistrado(correoElectronico)) {
                    throw new verificarException("El correo ya esta registrado");
                } else {
                    GestionClientes gestionClientes = new GestionClientes();
                    gestionClientes.guardarDatosCliente(identificacion, nombres, correoElectronico, numeroTelefonico, direccionResidencia, ccontrasena);
                    throw new verificarException("Se registró correctamente");
                }

            }
        } catch (verificarException e) {

            lblMensaje.setText(e.getMessage());
            mostrarLoginErrorTemporalmente();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Funcion  que se encarga de que el mensaje que salga en pantalla no se quede costante si no que tenga un timpo seleccionado.
     */
    private void mostrarLoginErrorTemporalmente() {

        lblMensaje.setVisible(true);


        // Configurar un Timeline para ocultar el mensaje después de 2 segundos (por ejemplo).
        Duration delay = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                lblMensaje.setVisible(false);


            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    /**
     * Funcion que se encarga de que la identificacion que va ser registrada no exista en los archivos.
     *
     * @param identificacion
     * @return
     * @throws IOException
     */

    private boolean verificarIdentificacionRegistrada(String identificacion) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Identificación Usuario: " + identificacion)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Funcion que se encarga de que el correo que va ser registrado no exista en los archivos.
     *
     * @param correo
     * @return
     * @throws IOException
     */
    private boolean verificarCorreoRegistrado(String correo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Correo Usuario: " + correo)) {
                    return true;
                }
            }
        }
        return false;
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


}
