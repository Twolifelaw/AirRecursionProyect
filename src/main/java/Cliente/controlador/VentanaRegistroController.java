package Cliente.controlador;

import Cliente.modelo.exceptions.verificarException;
import Cliente.modelo.objetos.Cliente;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.*;

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
    String ccontrasena;
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
                    clientes.add(new Cliente(nombres, "a", identificacion, ccontrasena, correoElectronico, numeroTelefonico, direccionResidencia));
                    serializarObjetos("clientes.se", clientes);

                    throw new verificarException("Se registró correctamente");


            }
        } catch (verificarException e) {

            lblMensaje.setText(e.getMessage());
            mostrarLoginErrorTemporalmente();
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

    /**Este método verifica si la identificación ya se encuntra registrada
     *
     * @param nombreArchivo
     * @param identificacion
     * @return
     */
    public static Cliente verificarIdentificacionRegistrada (String nombreArchivo, String identificacion) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCedula().equals(identificacion)) {
                    return objeto; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
    }

    /**Este método verifica si ese correo elctronico ya esta registrado
     *
     * @param nombreArchivo
     * @param correoElectronico
     * @return
     */
    public static Cliente verificarCorreoRegistrado (String nombreArchivo, String correoElectronico) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCorreo().equals(correoElectronico)) {
                    return objeto; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
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
        TextField[] camposTexto = {txt_nombre, txt_Id, txt_correo, txt_numero_telefonico, txt_direccion, psw_contrasena};

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
        inicializarEnterKey();//llama el metodo

    }
}
