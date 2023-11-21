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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPaquetesController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> comboBoxFiltro;
    @FXML
    private TextField txtBuscador;
    @FXML
    private GridPane gridPaquetes;
    @FXML
    private AnchorPane AnchorPaquetes;
    @FXML
    private ScrollPane scrollPanePaquetes;

   // public static ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");//Se trae los destinos deserializados.

    public static  ArrayList<PaqueteTuristico> paquetesTuristicosCargados = GestionPaquetes.deserializarPaquetes("paquetes.dat"); //Se trae los paquetes deserializados.
    private final double paneSpacing = 5.0; // Espacio entre los AnchorPane
    private final int maxColumns = 3; // Número máximo de columnas
    //Lo necesario para buen funcionamiento de toda la ventana.


    public static ArrayList<PaqueteTuristico> buscarDestinoPorFiltro(String filtro, String valor) {
        ArrayList<PaqueteTuristico> destinosFiltrados = new ArrayList<>();
        for (PaqueteTuristico paquete : paquetesTuristicosCargados) {
            String valorAtributo = null;

            switch (filtro.toLowerCase()) {
                case "nombre":
                    valorAtributo = paquete.getNombre();
                    System.out.println(valorAtributo);
                    break;
                case "duracion":
                    valorAtributo = String.valueOf(paquete.getDuracion());
                    break;
                case "servicios":
                    valorAtributo = paquete.getServicios();
                    break;
                case "precio":
                    valorAtributo = String.valueOf(paquete.getPrecio());
                    break;
                case "cupo":
                    valorAtributo = String.valueOf(paquete.getCupoMaximo());
                    break;

                // Agrega más casos según tus atributos
                default:
                    // Manejar el caso en que el filtro no coincide con ningún atributo
                    System.out.println("Filtro no válido");
            }

            // Realiza la comparación, ignorando mayúsculas y minúsculas
            if (valorAtributo != null && valorAtributo.equalsIgnoreCase(valor)) {
                destinosFiltrados.add(paquete);
            }
        }

        if (destinosFiltrados.isEmpty()) {
            System.out.println("No se encontraron destinos que coincidan con el filtro proporcionado.");
        } else {
            System.out.println(destinosFiltrados);
        }
        return destinosFiltrados;
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
                rowConstraints.setPrefHeight(310); // Establece la altura deseada para las filas

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(10); // Establece el ancho deseado para las columnas
                if (gridPaquetes != null) {
                    // Aplica las restricciones a todas las filas y columnas
                    gridPaquetes.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
                    gridPaquetes.getColumnConstraints().addAll(columnConstraints, columnConstraints);


                    //Se setan los valores de los objetos en los labels del anchorPane (Cart)

                    TextArea txtADestinos = (TextArea) nuevoAnchorPane.lookup("#txtAreaDestinos");
                    txtADestinos.setText("\nDestinos: "+paquete.getDestinos()+"\n");

                    Label lblNombre = (Label) nuevoAnchorPane.lookup("#lblNombre");
                    lblNombre.setText("Nombre: " + paquete.getNombre());

                    Label lblDuracion = (Label) nuevoAnchorPane.lookup("#lblDuracion");
                    lblDuracion.setText("Duration: " + paquete.getDuracion());

                    Label lblServicios = (Label) nuevoAnchorPane.lookup("#lblServicios");
                    lblServicios.setText("Servicios: "+paquete.getServicios());


                    Label lblPrecio = (Label) nuevoAnchorPane.lookup("#lblprecio");
                    lblPrecio.setText("Precio: " + paquete.getPrecio());

                    Label lblCupoMax = (Label) nuevoAnchorPane.lookup("#lblcupoMaximo");
                    lblCupoMax.setText("Cupo Max: "+paquete.getCupoMaximo());


                    //Establece el minimo y maximo de tamaño de los anchorPane's
                    nuevoAnchorPane.setMaxHeight(291);
                    nuevoAnchorPane.setMinHeight(291);
                    nuevoAnchorPane.setMaxWidth(300);
                    nuevoAnchorPane.setMinWidth(300);

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
        comboBoxFiltro.getItems().addAll("Nombre", "Duracion", "Servicios", "Precio", "Cupo");

    }


    public void filtrado(ActionEvent event) {
    }

    public void onBuscar(ActionEvent event) {
        String filtroSeleccionado = comboBoxFiltro.getValue();
        String valorBusqueda = txtBuscador.getText();
        if (filtroSeleccionado != null && !valorBusqueda.isEmpty()) {
            //buscarDestinoPorFiltro(filtroSeleccionado,valorBusqueda);
            mostrarDestinos(buscarDestinoPorFiltro(filtroSeleccionado, valorBusqueda));
        } else {
            mostrarDestinos(paquetesTuristicosCargados);
            //gridOfertas = null;
            System.out.println("Seleccione un filtro y proporcione un valor de búsqueda.");
        }
    }
}
