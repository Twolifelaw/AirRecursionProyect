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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.serializarObjetos;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;

public class VentanaMicuentaController implements Initializable {

    @FXML
    private Label lblMensaje;




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
    ;

    // Otros métodos...


    /*public static Cliente buscarMiCuenta(String nombreArchivo, String id) {
        ArrayList<Cliente> listaObjetos = deserializarClientesDesdeArchivo(nombreArchivo);

        if (listaObjetos != null) {
            for (Cliente objeto : listaObjetos) {
                if (objeto.getCedula().equalsIgnoreCase(id)) {
                    return objeto; // Se encontró el objeto con el id deseado.
                }
            }
        }
        return null; // No se encontró el objeto con el nombre deseado
    }

     */

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

    //@FXML
    /*void buscarCliente(ActionEvent event) {
        String id = txt_id.getText();
        boolean usuarioEncontrado = false;
        Cliente clienteBuscar = buscarMiCuenta("clientes.se",id);
        System.out.println("Cliente encontrado");

        if (clienteBuscar!=null){
            String nombre = clienteBuscar.getNombre();
            String correo = clienteBuscar.getCorreo();
            String telefono = clienteBuscar.getTelefono();
            String direccion = clienteBuscar.getDireccionResidencia();
            String contrasena = clienteBuscar.getContrasena();
            txt_nombre.setText(nombre);
            txt_correo.setText(correo);
            txt_numero_telefonico.setText(telefono);
            txt_direccion.setText(direccion);
            psw_contrasena.setText(contrasena);
            usuarioEncontrado =true;
        }

    }

     */


    @FXML
    void cambiarDatos(ActionEvent event) {
        ArrayList<Cliente> listaClientes = deserializarClientesDesdeArchivo("clientes.se");

        if(listaClientes != null) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(clienteAutenticado.getCedula())) {
                    cliente.setNombre(txt_nombre.getText());
                    cliente.setCorreo(txt_correo.getText());
                    cliente.setTelefono(txt_numero_telefonico.getText());
                    cliente.setDireccionResidencia(txt_direccion.getText());
                    cliente.setContrasena(psw_contrasena.getText());
                    break;
                }

            }
            serializarObjetos("clientes.se", listaClientes);

        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cliente clienteAutenticado = SesionCliente.getClienteAutenticado();

        // Llenar los campos con la información del cliente
        if ( clienteAutenticado!= null) {
            txt_nombre.setText(clienteAutenticado.getNombre());
            txt_correo.setText(clienteAutenticado.getCorreo());
            txt_numero_telefonico.setText(clienteAutenticado.getTelefono());
            txt_direccion.setText(clienteAutenticado.getDireccionResidencia());
            psw_contrasena.setText(clienteAutenticado.getContrasena());
        }
    }
}
