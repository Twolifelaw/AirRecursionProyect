package Cliente.controlador;

import Cliente.modelo.objetos.Administrador;
import Cliente.modelo.objetos.Cliente;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import static Cliente.modelo.Serializacion.GestionAdministradores.serializarAdinistrador;
import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;

public class VentanaUtilidades {//Esta clase se encargara de todas las utilidades de  todas las ventnaas que sean necesarias.

    public static void mostrarVentanaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(VentanaUtilidades.class.getResource("/com/vista/Login/ventanaLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que se encarga de quemar el administrador en este caso el profesor.
     */

    public static void crearAdministrador() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador("Profe", "programacion", "12332134", "1234", "profeProgra@gmail.com"));
        serializarAdinistrador("Admins.se", administradores);
    }

    /**
     * Metodo para imprimir mensaje en lblLabel dure 2 segundos y desaparezca.
     *
     * @param lblMensaje
     */
    public static void mostrarErrorTemporalmente(Label lblMensaje) {
        lblMensaje.setVisible(true);
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
     * Metodo que me valida si la identificacion ya esta registrada en los archivos.
     *
     * @param nombreArchivo
     * @param identificacion
     * @return
     */
    public static boolean verificarIdentificacionRegistrada(String nombreArchivo, String identificacion) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCedula().equals(identificacion)) {
                    return true; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return false; // No se encontró el objeto con el nombre deseado
    }

    /**
     * Este método verifica si ese correo elctronico ya esta registrado
     *
     * @param nombreArchivo
     * @param correoElectronico
     * @return
     */
    public static boolean verificarCorreoRegistrado(String nombreArchivo, String correoElectronico) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCorreo().equals(correoElectronico)) {
                    return true; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return false; // No se encontró el objeto con el nombre deseado
    }

    /***
     * Metodo que anima los botones.
     * @param boton
     */
    public static void agregarAnimacionBoton(Button boton) {
        ScaleTransition escalaEntrada = new ScaleTransition(Duration.millis(200), boton);
        escalaEntrada.setToX(1.1);
        escalaEntrada.setToY(1.1);

        ScaleTransition escalaSalida = new ScaleTransition(Duration.millis(200), boton);
        escalaSalida.setToX(1);
        escalaSalida.setToY(1);
        boton.setOnMouseEntered(event -> escalaEntrada.play());
        boton.setOnMouseExited(event -> escalaSalida.play());
    }

    /**
     * Metodo que me muestra mensaje de donde tengo el cursor o el focus.
     *
     * @param campo
     * @param label
     * @param mensaje
     */
    public static void agregarEventoYMostrarStatus(TextField campo, Label label, String mensaje) {
        campo.setOnMouseEntered(event -> mostrarStatus(label, mensaje));
        campo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mostrarStatus(label, mensaje);
            }
        });
    }

    /**
     * Metodo encargado  de mostrar mensaje en el label.
     *
     * @param label
     * @param mensaje
     */
    public static void mostrarStatus(Label label, String mensaje) {
        // Lógica para mostrar el mensaje en el lugar que desees (por ejemplo, lblStatus)
        label.setText(mensaje);
    }

}
