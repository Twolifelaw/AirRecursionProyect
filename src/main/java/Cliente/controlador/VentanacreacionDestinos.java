package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class VentanacreacionDestinos {



    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Destino, String> ColumnCiudad;

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
    private AnchorPane txtPais;

    @FXML
    void actionbtnAgregar(ActionEvent event) {




    }

    @FXML
    void actionbtnEditar(ActionEvent event) {

    }

    @FXML
    void actionbtnEliminar(ActionEvent event) {

    }

}
