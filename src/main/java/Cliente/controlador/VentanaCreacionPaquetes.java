package Cliente.controlador;

import Cliente.modelo.exceptions.VerificarException;
import Cliente.modelo.objetos.PaqueteTuristico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionPaquetes.*;

public class VentanaCreacionPaquetes implements Initializable {


    @FXML
    private AnchorPane anchorPaneCrear;

    @FXML
    private Button btnAgregarDisponible;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<PaqueteTuristico, Integer> columnaCupos;

    @FXML
    private TableColumn<PaqueteTuristico, ArrayList<LocalDate>> columnaFechaDisponible;

    @FXML
    private TableColumn<PaqueteTuristico, LocalDate> columnaFechaFIn;

    @FXML
    private TableColumn<PaqueteTuristico, LocalDate> columnaFechaInicio;

    @FXML
    private TableColumn<PaqueteTuristico, String> columnaNombre;

    @FXML
    private TableColumn<PaqueteTuristico, Double> columnaPrecio;

    @FXML
    private TableColumn<PaqueteTuristico, String> columnaServicio;

    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<PaqueteTuristico> tablaPaquetes;

    @FXML
    private TextField txtCupos;

    @FXML
    private DatePicker datePickerFechaDisponible;

    @FXML
    private DatePicker datePickerFechaFin;

    @FXML
    private DatePicker datePickerFechaInicio;

    @FXML
    private ListView<String> listViewFechasDisponibles;


    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtServicios;


    @FXML
    void onAgregar(ActionEvent event) throws VerificarException {
        try {
            String nombre = txtNombre.getText();
            String precio = txtPrecio.getText();
            String servicios = txtServicios.getText();
            String cuposMaximos = txtCupos.getText();
            LocalDate fechaInicio = datePickerFechaInicio.getValue();
            LocalDate fechaFin = datePickerFechaFin.getValue();
            LocalDate fechaDisponible = datePickerFechaDisponible.getValue();

            if (nombre.isEmpty() && precio.isEmpty() && servicios.isEmpty() && cuposMaximos.isEmpty() && fechaInicio == null && fechaFin == null && fechaDisponible == null) {
                throw new VerificarException("Llene los campos");
            } else if (nombre.isEmpty() || precio.isEmpty() || servicios.isEmpty() || cuposMaximos.isEmpty() || fechaInicio == null || fechaFin == null && fechaDisponible == null) {
                throw new VerificarException("Campo vacío, llenar por favor");
            } else {
                // Deserializar la lista actual de paquetes
                ArrayList<PaqueteTuristico> paquetesNuevos = deserializarPaquetes("paquetes.dat");

                // Obtener valores desde los campos de la interfaz gráfica
                double precioPaquete = Double.parseDouble(precio);
                int cuposPaquete = Integer.parseInt(cuposMaximos);

                // Crear un nuevo objeto PaqueteTuristico


                PaqueteTuristico nuevoPaquete = new PaqueteTuristico(new ArrayList<>(), nombre, fechaInicio, fechaFin, new ArrayList<>(List.of(fechaDisponible)), servicios, precioPaquete, cuposPaquete);
                // Agregar el nuevo paquete a la lista
                paquetesNuevos.add(nuevoPaquete);


                // Serializar la lista actualizada de paquetes
                serializarPaquetes("paquetes.dat", paquetesNuevos);

                // Limpiar los campos después de agregar

                // Mostrar mensaje de éxito
                lblStatus.setText("Paquete agregado con éxito");
            }
        } catch (VerificarException e) {
            lblStatus.setText(e.getMessage());
        } catch (NumberFormatException e) {
            lblStatus.setText("Ingrese valores numéricos válidos para precio y cupos");
        }

    }

    @FXML
    void onAgregarDisponible(ActionEvent event) throws VerificarException {
        try {
            LocalDate fechaDisponible = datePickerFechaDisponible.getValue();

            if (fechaDisponible == null) {
                throw new VerificarException("Llene los campos");
            } else {
                ArrayList<PaqueteTuristico> paquetes = deserializarPaquetes("paquetes.dat");

                // Verificar si hay un paquete seleccionado
                PaqueteTuristico paqueteSeleccionado = this.tablaPaquetes.getSelectionModel().getSelectedItem();

                if (paqueteSeleccionado != null) {
                    // Agregar la nueva fecha al paquete existente
                    paqueteSeleccionado.getFechaDisponible().add(fechaDisponible);

                    // Actualizar la lista en la interfaz gráfica
                    ObservableList<String> fechasDisponiblesStrings = FXCollections.observableArrayList();
                    for (LocalDate fecha : paqueteSeleccionado.getFechaDisponible()) {
                        fechasDisponiblesStrings.add(fecha.toString());
                    }
                    this.listViewFechasDisponibles.setItems(fechasDisponiblesStrings);

                    // Mostrar mensaje de éxito
                    lblStatus.setText("Fecha disponible agregada con éxito");
                } else {
                    lblStatus.setText("Seleccione un paquete antes de agregar fecha disponible");
                }

                // Serializar la lista actualizada de paquetes
                serializarPaquetes("paquetes.dat", paquetes);
            }
        } catch (VerificarException e) {
            lblStatus.setText(e.getMessage());
        } catch (Exception e) {
            lblStatus.setText("Error al agregar fecha disponible");
            e.printStackTrace();  // Imprime el seguimiento de la pila para la depuración
        }
    }

    public void editarPaquete(String nombreArchivo, String nombre) {

    }

    @FXML
    void onEditar(ActionEvent event) {


    }

    @FXML
    void onEliminar(ActionEvent event) {
        PaqueteTuristico p = this.tablaPaquetes.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        } else {
            eliminarPaquete("paquetes.dat", p.getNombre());
        }

    }

    @FXML
    void onSeleccionar(MouseEvent event) {
        PaqueteTuristico p = this.tablaPaquetes.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.txtNombre.setText(p.getNombre());
            this.txtPrecio.setText(String.valueOf(p.getPrecio()));
            this.txtServicios.setText(p.getServicios());
            this.datePickerFechaInicio.setValue(p.getFechaInicio());
            this.datePickerFechaFin.setValue(p.getFechaFin());
            this.txtCupos.setText(String.valueOf(p.getCuposMaximos()));
            ObservableList<String> fechasDisponiblesStrings = FXCollections.observableArrayList();
            if (!p.getFechaDisponible().isEmpty()) {
                for (LocalDate fecha : p.getFechaDisponible()) {
                    fechasDisponiblesStrings.add(fecha.toString());
                }
            }
            // Asignar el ObservableList al ListView
            this.listViewFechasDisponibles.setItems(fechasDisponiblesStrings);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaServicio.setCellValueFactory(new PropertyValueFactory<>("servicios"));
        columnaFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        columnaFechaFIn.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        columnaCupos.setCellValueFactory(new PropertyValueFactory<>("cuposMaximos"));
        columnaFechaDisponible.setCellValueFactory(new PropertyValueFactory<>("fechaDisponible"));
        ObservableList<PaqueteTuristico> paqueteTuristicos = FXCollections.observableArrayList(deserializarPaquetes("paquetes.dat"));
        tablaPaquetes.setItems(paqueteTuristicos);

    }
}
