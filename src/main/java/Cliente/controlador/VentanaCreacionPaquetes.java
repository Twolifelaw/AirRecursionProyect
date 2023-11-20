package Cliente.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static Cliente.modelo.Serializacion.GestionPaquetes.*;

public class VentanaCreacionPaquetes
{

    @FXML
    private AnchorPane anchorPaneCrear;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<?, ?> columnDuracion;

    @FXML
    private TableColumn<?, ?> columnFechaDisponible;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnServicio;

    @FXML
    private TableColumn<?, ?> columnaCupos;

    @FXML
    private TableColumn<?, ?> columnaPrecio;

    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<?> tablaPaquetes;

    @FXML
    private TextField txtCupos;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtFechasDisponibles;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtServicios;

    @FXML
    void onAgregar(ActionEvent event) {

    }

    @FXML
    void onEditar(ActionEvent event) {

    }

    @FXML
    void onEliminar(ActionEvent event) {

    }

    @FXML
    void onSeleccionar(MouseEvent event) {

    }

}
