package Cliente.controlador;

import Cliente.modelo.objetos.Cliente;
import Cliente.modelo.objetos.Destino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacioClientes.deserializarClientesDesdeArchivo;
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
    private TableColumn<Destino, String> columnImagen;
    @FXML
    private TableColumn<Destino, String > columnID;


    @FXML
    private TableColumn<Destino, String> columnPais;

    @FXML
    private TableView<Destino> tblDestinos;

    @FXML
    private TableColumn<Destino, Integer> columnCupos;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtClima;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCupos;



    @FXML
    void seleccionar(MouseEvent event) {
        Destino d = (Destino) this.tblDestinos.getSelectionModel().getSelectedItem();

        if(d!=null){
            this.txtPais.setText(d.getPais());
            this.txtCiudad.setText(d.getCiudad());
            this.txtClima.setText(d.getClima());
            this.txtDescripcion.setText(d.getDescripcion());
            this.txtPrecio.setText(d.getPrecio());
            this.txtID.setText(d.getId());
            this.txtCupos.setText(String.valueOf(d.getNumeroCupos()));

            Image img = new Image(d.getImagenes());
            this.imvImagenDestino.setImage(img);
        }


    }
    @FXML
    void actionbtnAgregar(ActionEvent event) {
        ArrayList<Destino> destinosNuevos = deserializarDestino("destinos.dat");
        destinosNuevos.add(new Destino(txtPais.getText(), txtCiudad.getText(), txtDescripcion.getText(),
                imagePath, txtClima.getText(),txtPrecio.getText(),txtID.getText(),Integer.parseInt(txtCupos.getText())));

        serializarDestino("destinos.dat", destinosNuevos);
    }

    @FXML
    void actionbtnEditar(ActionEvent event) {

        Destino D = (Destino) this.tblDestinos.getSelectionModel().getSelectedItem();


        if(D==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        }else {
            editarDestino("destinos.dat",D.getId());

            D.setPais(txtPais.getText());
            D.setCiudad(txtCiudad.getText());
            D.setClima(txtClima.getText());
            D.setDescripcion(txtDescripcion.getText());
            D.setImagenes(imagePath);
            D.setPrecio(txtPrecio.getText());
            D.setId(txtID.getText());
            D.setNumeroCupos(Integer.parseInt(txtCupos.getText()));

            this.tblDestinos.refresh();

        }

    }

    public  void editarDestino(String nombreArchivo , String id) {
        ArrayList<Destino> listaObjetos = deserializarObjetos(nombreArchivo);

        if (listaObjetos != null) {
            for (Destino objeto : listaObjetos) {
                if (objeto.getId().equals(id)){
                    objeto.setPais(txtPais.getText());
                    objeto.setCiudad(txtCiudad.getText());
                    objeto.setClima(txtClima.getText());
                    objeto.setDescripcion(txtDescripcion.getText());
                    objeto.setImagenes(imagePath);
                    objeto.setPrecio(txtPrecio.getText());
                    objeto.setId(txtID.getText());
                    objeto.setNumeroCupos(Integer.parseInt(txtCupos.getText()));
                    break;


                }
            }
            serializarDestino("destinos.dat",listaObjetos);
        }

    }

    @FXML
    void actionbtnEliminar(ActionEvent event) {
        Destino D = (Destino) this.tblDestinos.getSelectionModel().getSelectedItem();
        if(D == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        }else {
            eliminarDestino("destinos.dat",D.getId());

        }

    }

    @FXML
    void actionbtnAgregarImagen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                // Obtener la ruta relativa del archivo con respecto al directorio de trabajo actual
                imagePath = "file:"+selectedFile.getAbsolutePath();

                // Imprimir la ruta relativa (puedes guardarla en una variable, base de datos, etc.)
                System.out.println("Ruta relativa del archivo: " + imagePath);


                // Copiar la imagen seleccionada a una ubicación dentro del proyecto
                Path destinationPath = Path.of("src/main/resources", selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);




                // Cargar la imagen desde la ubicación dentro del proyecto
                Image image = new Image("file:" +selectedFile.getAbsolutePath());

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
        columnImagen.setCellValueFactory(new PropertyValueFactory<>("imagenes"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCupos.setCellValueFactory(new PropertyValueFactory<>("numeroCupos"));


        ObservableList<Destino> datosDestinos = FXCollections.observableArrayList(deserializarDestino("destinos.dat"));

        //Asignar los tados a la tabla
        tblDestinos.setItems(datosDestinos);


    }
}
