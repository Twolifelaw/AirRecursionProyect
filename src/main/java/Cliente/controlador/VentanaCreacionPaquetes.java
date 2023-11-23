package Cliente.controlador;

import Cliente.modelo.exceptions.VerificarExceptionNull;
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


    public Button btnEliminarFecha;
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
    void onAgregar(ActionEvent event) throws VerificarExceptionNull {
        try {
            String nombre = txtNombre.getText();
            String precio = txtPrecio.getText();
            String servicios = txtServicios.getText();
            String cuposMaximos = txtCupos.getText();
            LocalDate fechaInicio = datePickerFechaInicio.getValue();
            LocalDate fechaFin = datePickerFechaFin.getValue();
            LocalDate fechaDisponible = datePickerFechaDisponible.getValue();

            if (nombre.isEmpty() && precio.isEmpty() && servicios.isEmpty() && cuposMaximos.isEmpty() && fechaInicio == null && fechaFin == null && fechaDisponible == null) {
                throw new VerificarExceptionNull("Llene los campos");
            } else if (nombre.isEmpty() || precio.isEmpty() || servicios.isEmpty() || cuposMaximos.isEmpty() || fechaInicio == null || fechaFin == null && fechaDisponible == null) {
                throw new VerificarExceptionNull("Campo vacío, llenar por favor");
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
        } catch (VerificarExceptionNull e) {
            lblStatus.setText(e.getMessage());
        } catch (NumberFormatException e) {
            lblStatus.setText("Ingrese valores numéricos válidos para precio y cupos");
        }

    }

    @FXML
    void onAgregarDisponible(ActionEvent event) throws VerificarExceptionNull {
        try {
            LocalDate fechaDisponible = datePickerFechaDisponible.getValue();

            if (fechaDisponible == null) {
                throw new VerificarExceptionNull("Llene los campos");
            } else {
                ArrayList<PaqueteTuristico> paquetes = deserializarPaquetes("paquetes.dat");

                // Verificar si hay un paquete seleccionado
                PaqueteTuristico paqueteSeleccionado = this.tablaPaquetes.getSelectionModel().getSelectedItem();

                if (paqueteSeleccionado != null) {
                    // Verificar si la fecha ya existe en la lista
                    if (!paqueteSeleccionado.getFechaDisponible().contains(fechaDisponible)) {
                        // Agregar la nueva fecha al paquete existente
                        paqueteSeleccionado.getFechaDisponible().add(fechaDisponible);

                        // Actualizar la lista en la interfaz gráfica
                        ObservableList<String> fechasDisponiblesStrings = FXCollections.observableArrayList();
                        for (LocalDate fecha : paqueteSeleccionado.getFechaDisponible()) {
                            fechasDisponiblesStrings.add(fecha.toString());
                        }
                        this.listViewFechasDisponibles.setItems(fechasDisponiblesStrings);

                        // Serializar la lista actualizada de paquetes
                        serializarPaquetes("paquetes.dat", paquetes);

                        // Mostrar mensaje de éxito
                        lblStatus.setText("Fecha disponible agregada con éxito");
                    } else {
                        lblStatus.setText("La fecha ya está disponible en la lista");
                    }
                } else {
                    lblStatus.setText("Seleccione un paquete antes de agregar fecha disponible");
                }
            }
        } catch (VerificarExceptionNull e) {
            lblStatus.setText(e.getMessage());
        } catch (Exception e) {
            lblStatus.setText("Error al agregar fecha disponible");
            e.printStackTrace();
        }
    }

    public void editarPaquete(String nombreArchivo, String nombre) {

        try {
            // Deserializar la lista actual de paquetes
            ArrayList<PaqueteTuristico> listPaquetes = deserializarPaquetes(nombreArchivo);

            LocalDate fechaDisponible = datePickerFechaDisponible.getValue();
            //ArrayList<LocalDate> fechasDisponibles = listViewFechasDisponibles;
            ArrayList<LocalDate> fechasDisponibles = new ArrayList<>();
            ObservableList<String> fechasDisponiblesStrings = listViewFechasDisponibles.getItems();

            for (String fechaString : fechasDisponiblesStrings) {
                LocalDate fecha = LocalDate.parse(fechaString);
                fechasDisponibles.add(fecha);
            }

            if (fechaDisponible != null) {
                fechasDisponibles.add(fechaDisponible);
            }

            if (listPaquetes != null) {
                for (PaqueteTuristico paqueteTuristico : listPaquetes) {
                    if (paqueteTuristico.getNombre().equals(nombre)) {
                        // Actualizar solo los campos que se han ingresado
                        paqueteTuristico.setNombre(txtNombre.getText());
                        paqueteTuristico.setFechaInicio(datePickerFechaInicio.getValue());
                        paqueteTuristico.setFechaFin(datePickerFechaFin.getValue());
                        paqueteTuristico.setServicios(txtServicios.getText());
                        paqueteTuristico.setFechaDisponible(fechasDisponibles);
                        paqueteTuristico.setPrecio(Double.parseDouble(txtPrecio.getText()));
                        paqueteTuristico.setCuposMaximos(Integer.parseInt(txtCupos.getText()));
                        break;
                    }
                }
                // Serializar la lista actualizada de paquetes
                serializarPaquetes(nombreArchivo, listPaquetes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onEditar(ActionEvent event) {
        PaqueteTuristico p = this.tablaPaquetes.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Destino");
            alert.showAndWait();
        } else {
            try {
                // Obtener la fecha disponible del DatePicker
                LocalDate fechaDisponible = datePickerFechaDisponible.getValue();

                // Verificar si la fechaDisponible no es nula antes de agregarla
                if (fechaDisponible != null) {
                    // Agregar la nueva fecha al paquete existente
                    p.getFechaDisponible().add(fechaDisponible);

                    // Actualizar la lista en la interfaz gráfica
                    ObservableList<String> fechasDisponiblesStrings = FXCollections.observableArrayList();
                    for (LocalDate fecha : p.getFechaDisponible()) {
                        fechasDisponiblesStrings.add(fecha.toString());
                    }
                    this.listViewFechasDisponibles.setItems(fechasDisponiblesStrings);
                }

                // Realizar la edición del paquete
                editarPaquete("paquetes.dat", p.getNombre());

                // Refrescar la tabla después de la edición
                this.tablaPaquetes.refresh();

                // Mostrar mensaje de éxito
                lblStatus.setText("Paquete editado con éxito");
            } catch (Exception e) {
                lblStatus.setText("Error al editar el paquete");
                e.printStackTrace();  // Imprime el seguimiento de la pila para la depuración
            }
        }
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

    public void eliminarFechaDisponible(LocalDate fecha) {
        // Obtener el paquete seleccionado
        PaqueteTuristico paqueteSeleccionado = this.tablaPaquetes.getSelectionModel().getSelectedItem();

        if (paqueteSeleccionado != null) {
            // Obtener la lista de fechas disponibles del paquete
            ArrayList<LocalDate> fechasDisponibles = paqueteSeleccionado.getFechaDisponible();

            // Verificar si la fecha a eliminar está presente en la lista
            if (fechasDisponibles.contains(fecha)) {
                // Eliminar la fecha
                fechasDisponibles.remove(fecha);

                // Actualizar la lista en la interfaz gráfica
                ObservableList<String> fechasDisponiblesStrings = FXCollections.observableArrayList();
                for (LocalDate fechaDisponible : fechasDisponibles) {
                    fechasDisponiblesStrings.add(fechaDisponible.toString());
                }
                this.listViewFechasDisponibles.setItems(fechasDisponiblesStrings);

                // Mostrar mensaje de éxito
                lblStatus.setText("Fecha disponible eliminada con éxito");
            } else {
                lblStatus.setText("La fecha seleccionada no está en la lista de fechas disponibles");
            }
        } else {
            lblStatus.setText("Seleccione un paquete antes de eliminar una fecha disponible");
        }
        this.tablaPaquetes.refresh();
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

    public void onEliminarFecha(ActionEvent event) {

        // Obtener la fecha seleccionada en el ListView
        String fechaString = listViewFechasDisponibles.getSelectionModel().getSelectedItem();

        if (fechaString != null) {
            // Convertir la fecha de String a LocalDate
            LocalDate fechaSeleccionada = LocalDate.parse(fechaString);

            // Llamar al método para eliminar la fecha
            eliminarFechaDisponible(fechaSeleccionada);
        } else {
            lblStatus.setText("Seleccione una fecha antes de eliminar");
        }
    }
}
