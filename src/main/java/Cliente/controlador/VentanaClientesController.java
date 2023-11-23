package Cliente.controlador;

import Cliente.modelo.objetos.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;

public class VentanaClientesController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCrear;

    @FXML
    private TableColumn<Cliente, String> columnaApellido;

    @FXML
    private TableColumn<Cliente, String> columnaCedula;

    @FXML
    private TableColumn<Cliente, String> columnaCorreo;

    @FXML
    private TableColumn<Cliente, String> columnaDireccion;

    @FXML
    private TableColumn<Cliente, String> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaTelefono;

    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private TableView<Cliente> tablaClientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionResidencia"));
        ObservableList<Cliente> datosClientes = FXCollections.observableArrayList(deserializarClientesDesdeArchivo("clientes.se"));
        tablaClientes.setItems(datosClientes);
    }
}
