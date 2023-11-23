package Cliente.controlador;

import Cliente.modelo.Serializacion.SesionCliente;
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

public class VentanaRecuperacionController implements Initializable {
    //
    @FXML
    private AnchorPane anchorPaneRecuperar;

    @FXML
    private Button btnRecuperar;

    @FXML
    private Button btnAtras;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCorreo;
    //de aqui para arriba van los componentes.


    public static Cliente buscarObjeto(String nombreArchivo, String correo) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCorreo().equalsIgnoreCase(correo)) {
                    return objeto; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
    }

    /**
     * Accion del botonRecuperar.
     *
     * @param event
     */

    @FXML
    void onRecuperar(ActionEvent event) throws VerificarException, IOException {
        try {


            String correo = txtCorreo.getText();

            if (correo.isEmpty()) {
                throw new VerificarException("llene el campo");
            } else {
                boolean usuarioEncontrado = false;
                Cliente clienteBuscar = buscarObjeto("clientes.se", correo);
                System.out.println("cliente en el archivo");
                System.out.println(deserializarClientesDesdeArchivo("clientes.se"));
                if (clienteBuscar != null) {
                    Cliente clienteaut = clienteBuscar;
                    SesionCliente.setClienteAutenticado(clienteaut);
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/vista/ventanas/VentanaContrasena.fxml"));
                    Scene escena = new Scene(root);
                    stage.setScene(escena);
                    stage.show();
                    // en esta linea , esconde el stage del login y carga el nuevo stage
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    usuarioEncontrado = true;
                } else {
                    if (!usuarioEncontrado) {
                        throw new VerificarException("No se encontro usuario");
                    }
                }
            }
        } catch (VerificarException e) {
            lblMensaje.setText(e.getMessage());
            VentanaUtilidades.mostrarErrorTemporalmente(lblMensaje);
        }

    }

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtCorreo};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnRecuperar.fire();
                }
            });
        }
    }


    /**
     * Accion del botonRegresar.
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
     * Metodo que inicia lo de la ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarEnterKey();
        VentanaUtilidades.agregarAnimacionBoton(btnRecuperar);
        VentanaUtilidades.agregarAnimacionBoton(btnAtras);

    }
}
