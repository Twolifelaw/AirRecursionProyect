package Cliente.controlador;

import Cliente.modelo.exceptions.verificarException;
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


public class VentanaCreacionDestinos implements Initializable {
    //
    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private Label lblStatus;

    @FXML
    private AnchorPane anchorPaneCrear;

    private String imagePath;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView contenedorImagenesDestinos;

    @FXML
    private TableView<Destino> tablaDestinos;

    @FXML
    private TableColumn<Destino, String> columnPais;

    @FXML
    private TableColumn<Destino, String> columnaCiudad;

    @FXML
    private TableColumn<Destino, String> columnaPrecio;

    @FXML
    private TableColumn<Destino, String> columnaClima;

    @FXML
    private TableColumn<Destino, String> columnaImagen;

    @FXML
    private TableColumn<Destino, String> columnaID;

    @FXML
    private TableColumn<Destino, Integer> columnaCupos;

    @FXML
    private Button btnAgregarImg;

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
    //de aqui para arriba son componentes.



    /**
     * Metodo para seleccionar destino dentro de la tablaDestinos.
     *
     * @param event
     */

    @FXML
    void onSeleccionar(MouseEvent event) {
        Destino d = this.tablaDestinos.getSelectionModel().getSelectedItem();

        if (d != null) {
            this.txtPais.setText(d.getPais());
            this.txtCiudad.setText(d.getCiudad());
            this.txtClima.setText(d.getClima());
            this.txtDescripcion.setText(d.getDescripcion());
            this.txtPrecio.setText(d.getPrecio());
            this.txtID.setText(d.getId());
            this.txtCupos.setText(String.valueOf(d.getNumeroCupos()));
            try {
                Image img = new Image(d.getImagenes());
                this.contenedorImagenesDestinos.setImage(img);
            } catch (NullPointerException e) {
                lblStatus.setText("Error en la carga de la imagen ruta no encontrada: " + e.getMessage());
            }

        }

    }

    /**
     * Accion del botonAgregar.
     *
     * @param event
     */

    @FXML
    void onAgregar(ActionEvent event) {

        try {
            String pais = txtPais.getText();
            String precio = txtPrecio.getText();
            String ciudad = txtCiudad.getText();
            String id = txtID.getId();
            String clima = txtClima.getText();
            String cupos = txtCupos.getText();
            String descripcion = txtDescripcion.getText().trim();
            if (pais.isEmpty() && precio.isEmpty() && ciudad.isEmpty() && id.isEmpty() && clima.isEmpty() && cupos.isEmpty() && descripcion.isEmpty()) {
                throw new verificarException("Llene los campos");
            } else if (pais.isEmpty() || precio.isEmpty() || ciudad.isEmpty() || id.isEmpty() || clima.isEmpty() || cupos.isEmpty() || descripcion.isEmpty()) {
                throw new verificarException("Campo vacío, llenar por favor");
            } else {
                ArrayList<Destino> destinosNuevos = deserializarDestino("destinos.dat");
                destinosNuevos.add(new Destino(txtPais.getText(), txtCiudad.getText(), descripcion,
                        imagePath, txtClima.getText(), txtPrecio.getText(), txtID.getText(), Integer.parseInt(txtCupos.getText())));
                serializarDestino("destinos.dat", destinosNuevos);
            }

        } catch (verificarException e) {
            lblStatus.setText(e.getMessage());
        }

    }

    /**
     * Accion del botonEditar.
     *
     * @param event
     */

    @FXML
    void onEditar(ActionEvent event) {

        Destino D = this.tablaDestinos.getSelectionModel().getSelectedItem();

        if (D == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        } else {
            editarDestino("destinos.dat", D.getId());

            D.setPais(txtPais.getText());
            D.setCiudad(txtCiudad.getText());
            D.setClima(txtClima.getText());
            D.setDescripcion(txtDescripcion.getText());
            D.setImagenes(imagePath);
            D.setPrecio(txtPrecio.getText());
            D.setId(txtID.getText());
            D.setNumeroCupos(Integer.parseInt(txtCupos.getText()));

            this.tablaDestinos.refresh();

        }

    }

    /**
     * Metodo que se encarga de editar el destino.
     *
     * @param nombreArchivo
     * @param id
     */

    public void editarDestino(String nombreArchivo, String id) {
        ArrayList<Destino> listaObjetos = deserializarObjetos(nombreArchivo);

        if (listaObjetos != null) {
            for (Destino objeto : listaObjetos) {
                if (objeto.getId().equals(id)) {
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
            serializarDestino("destinos.dat", listaObjetos);
        }

    }

    /**
     * Accion del botonEliminar.
     *
     * @param event
     */

    @FXML
    void onEliminar(ActionEvent event) {
        Destino D = this.tablaDestinos.getSelectionModel().getSelectedItem();
        if (D == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        } else {
            eliminarDestino("destinos.dat", D.getId());

        }

    }

    /**
     * Accion del botonAgregarImagen.
     *
     * @param event
     */

    @FXML
    void onAgregarImagen(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                // Obtener la ruta relativa del archivo con respecto al directorio de trabajo actual
                imagePath = "file:" + selectedFile.getAbsolutePath();

                // Imprimir la ruta relativa (puedes guardarla en una variable, base de datos, etc.)
                System.out.println("Ruta relativa del archivo: " + imagePath);


                // Copiar la imagen seleccionada a una ubicación dentro del proyecto
                Path destinationPath = Path.of("src/main/resources", selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);


                // Cargar la imagen desde la ubicación dentro del proyecto
                Image image = new Image("file:" + selectedFile.getAbsolutePath());

                contenedorImagenesDestinos.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que se encarga de iniciar todo en la ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Animacion de los botones
        VentanaUtilidades.agregarAnimacionBoton(btnAgregar);
        VentanaUtilidades.agregarAnimacionBoton(btnEditar);
        VentanaUtilidades.agregarAnimacionBoton(btnEliminar);
        VentanaUtilidades.agregarAnimacionBoton(btnAgregarImg);
        //Seguimiento del label
        VentanaUtilidades.agregarEventoYMostrarStatus(txtPais, lblStatus, "Pais");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtPrecio, lblStatus, "Precio");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtCiudad, lblStatus, "Ciudad");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtID, lblStatus, "ID");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtClima, lblStatus, "Clima");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtCupos, lblStatus, "Cupos");
        VentanaUtilidades.agregarEventoYMostrarStatus(txtDescripcion, lblStatus, "Descripcion");
        //Animacion de imagen.
        VentanaUtilidades.girarImagen(imagenAvion1);
        VentanaUtilidades.girarImagen(imagenAvion2);

        columnPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        columnaClima.setCellValueFactory(new PropertyValueFactory<>("clima"));
        columnaImagen.setCellValueFactory(new PropertyValueFactory<>("imagenes"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaCupos.setCellValueFactory(new PropertyValueFactory<>("numeroCupos"));


        ObservableList<Destino> datosDestinos = FXCollections.observableArrayList(deserializarDestino("destinos.dat"));

        //Asignar los tados a la tabla
        tablaDestinos.setItems(datosDestinos);

    }
}
