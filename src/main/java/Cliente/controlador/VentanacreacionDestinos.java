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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.*;


public class VentanacreacionDestinos implements Initializable {


    private String imagePath;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView imvImagenDestino;

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
        destinosNuevos.add(new Destino(txtPais.getText(),txtCiudad.getText(),txtDescripcion.getText(),imagePath,txtClima.getText()));
        serializarDestino("destinos.dat",destinosNuevos);
    }

    @FXML
    void actionbtnEditar(ActionEvent event) {

    }

    @FXML
    void actionbtnEliminar(ActionEvent event) {

    }

    @FXML
    void actionbtnAgregarImagen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // Obtener la ruta relativa del archivo con respecto al directorio de trabajo actual
                imagePath =  "/"+selectedFile.getName();

                // Imprimir la ruta relativa (puedes guardarla en una variable, base de datos, etc.)
                System.out.println("Ruta relativa del archivo: " + imagePath);

                // Copiar la imagen seleccionada a una ubicación dentro del proyecto
                Path destinationPath = Path.of("src/main/resources", selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Cargar la imagen desde la ubicación dentro del proyecto
                Image image = new Image(getClass().getResource(imagePath).toExternalForm());

                imvImagenDestino.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
