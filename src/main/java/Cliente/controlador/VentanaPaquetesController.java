package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionPaquetes;
import Cliente.modelo.objetos.PaqueteTuristico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPaquetesController implements Initializable {
    public static ArrayList<PaqueteTuristico> paquetesTuristicosCargados = GestionPaquetes.deserializarPaquetes("paquetes.dat"); //Se trae los paquetes deserializados.
    private final double paneSpacing = 10.0; // Espacio entre los AnchorPane
    private final int maxColumns = 2; // Número máximo de columnas
    @FXML
    private DatePicker datePickerFechas;
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> comboBoxFiltro;
    @FXML
    private TextField txtBuscador;

    // public static ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");//Se trae los destinos deserializados.
    @FXML
    private GridPane gridPaquetes;
    @FXML
    private AnchorPane AnchorPaquetes;
    @FXML
    private ScrollPane scrollPanePaquetes;
    //Lo necesario para buen funcionamiento de toda la ventana.

    public static ArrayList<PaqueteTuristico> buscarPaquetePorFiltro(String filtro, String valor, LocalDate valorFecha, ArrayList<LocalDate> valoresFechas) {
        ArrayList<PaqueteTuristico> paquetesFiltrados = new ArrayList<>();
        for (PaqueteTuristico paquete : paquetesTuristicosCargados) {
            String valorAtributo = null;
            LocalDate valorFechas = null;

            if (filtro != null) {
                switch (filtro.toLowerCase()) {
                    case "nombre":
                        valorAtributo = paquete.getNombre();
                        break;

                    case "precio":
                        valorAtributo = String.valueOf(paquete.getPrecio());
                        break;

                    case "servicios":
                        valorAtributo = paquete.getServicios();
                        break;

                    case "fechainicio":
                        valorFechas = paquete.getFechaInicio();
                        break;

                    case "fechafin":
                        valorFechas = paquete.getFechaFin();
                        break;

                    case "cupo":
                        valorAtributo = String.valueOf(paquete.getCuposMaximos());
                        break;

                    case "fechadisponible":
                        // Aquí verificamos si la fecha proporcionada está en el rango de fechas disponibles
                        if (valorFecha != null && valorFecha.isAfter(paquete.getFechaInicio()) && valorFecha.isBefore(paquete.getFechaFin())) {
                            paquetesFiltrados.add(paquete);
                            continue;  // Continuar con la siguiente iteración del bucle
                        } else {
                            // Si no cumple con la condición de fecha, continuar con la siguiente iteración
                            continue;
                        }

                    case "fechasdisponibles":
                        // Aquí verificamos si al menos una fecha proporcionada está en el rango de fechas disponibles
                        if (valoresFechas != null && valoresFechas.stream().anyMatch(fecha -> fecha.isAfter(paquete.getFechaInicio()) && fecha.isBefore(paquete.getFechaFin()))) {
                            paquetesFiltrados.add(paquete);
                            continue;  // Continuar con la siguiente iteración del bucle
                        } else {
                            // Si no cumple con la condición de fechas, continuar con la siguiente iteración
                            continue;
                        }

                        // Agrega más casos según tus atributos

                    default:
                        // Manejar el caso en que el filtro no coincide con ningún atributo
                        System.out.println("Filtro no válido");
                }
            } else {
                System.out.println("El filtro es nulo.");
            }
            // Realiza la comparación, ignorando mayúsculas y minúsculas
            if (valorAtributo != null && valorAtributo.equalsIgnoreCase(valor)) {
                paquetesFiltrados.add(paquete);
            }
        }

        if (paquetesFiltrados.isEmpty()) {
            System.out.println("No se encontraron paquetes que coincidan con el filtro proporcionado.");
        } else {
            System.out.println(paquetesFiltrados);
        }
        return paquetesFiltrados;
    }

    public void mostrarDestinos(ArrayList<PaqueteTuristico> paquetes) {
        gridPaquetes.getChildren().clear();
        int anchorPanelCount = 0;
        for (PaqueteTuristico paquete : paquetes) {
            try {

                //Cargar el AnchorPane desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/DiseñoPaquetes.fxml"));
                AnchorPane nuevoAnchorPane = loader.load();
                anchorPanelCount++;


                // Establece el tamaño deseado para las filas y columnas en el GridPane
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(420); // Establece la altura deseada para las filas

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(10); // Establece el ancho deseado para las columnas
                if (gridPaquetes != null) {
                    // Aplica las restricciones a todas las filas y columnas
                    gridPaquetes.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
                    gridPaquetes.getColumnConstraints().addAll(columnConstraints, columnConstraints);


                    //Se setan los valores de los objetos en los labels del anchorPane (Cart)

                    TextArea txtADestinos = (TextArea) nuevoAnchorPane.lookup("#txtAreaDestinos");
                    txtADestinos.setText("Destinos: " + paquete.getDestinos() + "\n");

                    TextArea txtFechasDisponibles = (TextArea) nuevoAnchorPane.lookup("#txtFechas");
                    txtFechasDisponibles.setText("FechaDisponible: "+ paquete.getFechaDisponible());


                    Label lblNombre = (Label) nuevoAnchorPane.lookup("#lblNombre");
                    lblNombre.setText("Nombre: " + paquete.getNombre());

                    Label lblFechaInicio = (Label) nuevoAnchorPane.lookup("#lblFechaInicio");
                    lblFechaInicio.setText("Fecha de inicio: "+paquete.getFechaInicio());

                    Label lblFechaFin = (Label) nuevoAnchorPane.lookup("#lblFechaFin");
                    lblFechaFin.setText("Fecha de fin: "+paquete.getFechaFin());

                    Label lblServicios = (Label) nuevoAnchorPane.lookup("#lblServicios");
                    lblServicios.setText("Servicios: " + paquete.getServicios());


                    Label lblPrecio = (Label) nuevoAnchorPane.lookup("#lblprecio");
                    lblPrecio.setText("Precio: " + paquete.getPrecio());

                    Label lblCupoMax = (Label) nuevoAnchorPane.lookup("#lblcupoMaximo");
                    lblCupoMax.setText("Cupo Max: " + paquete.getCuposMaximos());


                    //Establece el minimo y maximo de tamaño de los anchorPane's
                    nuevoAnchorPane.setMaxHeight(400);
                    nuevoAnchorPane.setMinHeight(400);
                    nuevoAnchorPane.setMaxWidth(400);
                    nuevoAnchorPane.setMinWidth(400);

                    // Configurar las coordenadas de acuerdo a tu diseño
                    int row = (anchorPanelCount - 1) / maxColumns;
                    int col = (anchorPanelCount - 1) % maxColumns;

                    GridPane.setRowIndex(nuevoAnchorPane, row);
                    GridPane.setColumnIndex(nuevoAnchorPane, col);


                    // Establecer restricciones de crecimiento horizontal para evitar que se agrupen
                    GridPane.setHgrow(nuevoAnchorPane, Priority.ALWAYS);

                    //Agregar al cart al GridePane
                    gridPaquetes.getChildren().add(nuevoAnchorPane);
                    if (anchorPanelCount > 0) {
                        Insets margins = new Insets(paneSpacing, paneSpacing, paneSpacing, paneSpacing);
                        GridPane.setMargin(nuevoAnchorPane, margins);
                    }

                    scrollPanePaquetes.setVvalue(1.0);
                } else {
                    System.out.println("no se cargo el gridPane");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarDestinos(paquetesTuristicosCargados);
        comboBoxFiltro.getItems().addAll("Nombre", "Precio", "servicios", "FechaInicio", "FechaFin", "Cupo", "FechaDisponible");

    }


    public void filtrado(ActionEvent event) {
    }

    public void onBuscar(ActionEvent event) {
        String filtroSeleccionado = comboBoxFiltro.getValue();
        String valorBusqueda = txtBuscador.getText();

        if ("fechasdisponibles".equalsIgnoreCase(filtroSeleccionado)) {
            // Obtener la fecha del DatePicker
            LocalDate fechaBusqueda = datePickerFechas.getValue();

            if (fechaBusqueda != null) {
                mostrarDestinos(buscarPaquetePorFiltro(filtroSeleccionado, null, fechaBusqueda, null));
            } else {
                System.out.println("Seleccione una fecha para realizar la búsqueda.");
            }
        } else {
            // Búsqueda normal
            mostrarDestinos(buscarPaquetePorFiltro(filtroSeleccionado, valorBusqueda, null, null));
        }
    }
}
