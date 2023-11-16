package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacioClientes;
import Cliente.modelo.Serializacion.SesionCliente;
import Cliente.modelo.exceptions.verificarException;
import Cliente.modelo.objetos.Administrador;
import Cliente.modelo.objetos.Cliente;
import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionAdministradores.deserializarAdministradorDesdeArchivo;
import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;

public class VentanaLoginController implements Initializable {

    /**
     * Variables que se encargan de la validacion de la ventana.
     */
    String nombre;
    String contrasena;
    //

    /**
     * Componenetes  que interactuan con la ventana .
     */

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRecuperar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblMensaje;

    @FXML
    private PasswordField pswContrasena;

    @FXML
    private TextField txtUsuario;
    //

    /**
     * Este metodo busca en una lista de clientes deserializados si uno de ellos tiene los mismos parametros
     * (nombre y contraseña), proporsionado en el login.
     *
     * @param nombreArchivo
     * @param nombreBuscado
     * @param contrasena
     * @return Cliente
     */

    public static Cliente buscarObjeto(String nombreArchivo, String nombreBuscado, String contrasena) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getNombre().equalsIgnoreCase(nombreBuscado) && objeto.getContrasena().equals(contrasena)) {
                    return objeto; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
    }

    public static Administrador buscarAdmin(String nombreArchivo, String nombreBuscado, String contrasena) {
        ArrayList<Administrador> listaObjetos = deserializarAdministradorDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Administrador objeto : listaObjetos) {
                if (objeto.getNombre().equalsIgnoreCase(nombreBuscado) && objeto.getContrasena().equals(contrasena)) {
                    return objeto; // Se encontró el objeto con el nombre deseado
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
    }

    /**
     * Boton que se encarga del ingreso de los diferentes usuarios.
     *
     * @param event
     */

    @FXML
    void ingresar(ActionEvent event) {
        try {
            nombre = txtUsuario.getText();
            contrasena = pswContrasena.getText();
            if (nombre.isEmpty() && contrasena.isEmpty()) {
                throw new verificarException("Llene los campos");
            } else if (nombre.isEmpty() || contrasena.isEmpty()) {
                throw new verificarException("Campo vacio llenar porfavor");
            } else {
                boolean usuarioEncontrado = false;
                Cliente clienteBuscar = buscarObjeto("clientes.se", nombre, contrasena);
                Administrador adminBuscar = buscarAdmin("Admins.se", nombre, contrasena);

                System.out.println("clientes en el archivo");
                //System.out.println(deserializarClientesDesdeArchivo("clientes.se"));

                if (clienteBuscar != null) {
                    Cliente clienteaut = clienteBuscar;
                    SesionCliente.setClienteAutenticado(clienteaut);
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/com/vista/ventanas/ventanaInicio.fxml"));
                    Scene escena = new Scene(root);
                    stage.setScene(escena);
                    stage.show();
                    // en esta linea , esconde el stage del login y carga el nuevo stage
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    usuarioEncontrado = true;

                } else if (adminBuscar != null) {
                    //Mensaje provisional xd
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaAdministrador.fxml")));
                    Scene escena = new Scene(root);
                    stage.setScene(escena);
                    stage.show();
                    // en esta linea , esconde el stage del login y carga el nuevo stage
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } else {
                    if (!usuarioEncontrado) {
                        throw new verificarException("No se encontro usuario");
                    }
                }
            }

        } catch (verificarException e) {

            lblMensaje.setText(e.getMessage());
            VentanaUtilidades.mostrarErrorTemporalmente(lblMensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Boton que se encarga de abrir la ventana registrar.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void action_registrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaRegistro.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    /**
     * Boton que se encargara de recuperar la contraseña del cliente.
     *
     * @param event
     */
    @FXML
    void onRecuperar(ActionEvent event) throws IOException { Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaRecuperacion.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }


    /**
     * Metodo para cuando se oprima la letra Enter hace acción en el botonIngresar.
     */
    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtUsuario, pswContrasena};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnIngresar.fire();
                }
            });
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Se llama al metodo de btnIngresar.
        inicializarEnterKey();
        VentanaUtilidades.agregarAnimacionBoton(btnIngresar);
        VentanaUtilidades.agregarAnimacionBoton(btnRecuperar);
        VentanaUtilidades.agregarAnimacionBoton(btnRegistrar);
    }
}
