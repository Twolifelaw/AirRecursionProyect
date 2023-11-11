package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.*;


public class VentanacreacionDestinos implements Initializable {



    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Destino, String> ColumnCiudad;
    @FXML
    private TableColumn<Destino, String> columnPrecio;

    @FXML
    private TableColumn<Destino, String> columnClima;

    @FXML
    private TableColumn<Destino,String> columnImagen;

    @FXML
    private TableColumn<Destino,String > columnPais;

    @FXML
    private TableView<Destino> tblDestinos;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtClima;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPais;


    @FXML
    void actionbtnAgregar(ActionEvent event) {
        ArrayList<Destino> destinosNuevos = new ArrayList<>();
        destinosNuevos.add(new Destino(txtPais.getText(),txtCiudad.getText(),txtDescripcion.getText(),"/NEwYork.jpg",txtClima.getText()));
        serializarDestino("destinos.dat",destinosNuevos);
    }

    @FXML
    void actionbtnEditar(ActionEvent event) {

    }

    @FXML
    void actionbtnEliminar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        ColumnCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        columnClima.setCellValueFactory(new PropertyValueFactory<>("clima"));



        ObservableList<Destino> datosDestinos = FXCollections.observableArrayList(deserializarDestino("destinos.dat"));

        //Asignar los tados a la tabla
        tblDestinos.setItems(datosDestinos);


    }
}
