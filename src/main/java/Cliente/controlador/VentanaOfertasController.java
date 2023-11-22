package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacionDestinos;
import Cliente.modelo.exceptions.CargarImagenException;
import Cliente.modelo.exceptions.VerificarExceptionNull;
import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaOfertasController implements Initializable {
    // Cargar los destinos desde el archivo
    public static ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");//Se trae los destinos deserializados.
    //
    private final double paneSpacing = 5.0; // Espacio entre los AnchorPane
    private final int maxColumns = 3; // Número máximo de columnas
    //Lo necesario para buen funcionamiento de toda la ventana.

    //
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> comboBoxFiltro;
    @FXML
    private TextField txtBuscador;
    @FXML
    private GridPane gridOfertas;
    @FXML
    private AnchorPane anchorPaneOfertas;
    @FXML
    private ScrollPane scrollPanelContenedor;
    //de aqui para arriba son componentes.

    /**
     * Metodo que se encarga de buscarDestino por el filtro seleccionado.
     *
     * @param filtro
     * @param valor
     * @return
     */

    public static ArrayList<Destino> buscarDestinoPorFiltro(String filtro, String valor) {
        ArrayList<Destino> destinosFiltrados = new ArrayList<>();
        for (Destino destino : destinosCargados) {
            String valorAtributo = null;

            switch (filtro.toLowerCase()) {
                case "pais":
                    valorAtributo = destino.getPais();
                    System.out.println(valorAtributo);
                    break;
                case "ciudad":
                    valorAtributo = destino.getCiudad();
                    break;
                case "clima":
                    valorAtributo = destino.getClima();
                    break;
                case "precio":
                    valorAtributo = destino.getPrecio();
                    break;
                case "id":
                    valorAtributo = destino.getId();
                    break;
                case "cupos":
                    valorAtributo = String.valueOf(destino.getNumeroCupos());
                    break;
                // Agrega más casos según tus atributos
                default:
                    // Manejar el caso en que el filtro no coincide con ningún atributo
                    System.out.println("Filtro no válido");
            }

            // Realiza la comparación, ignorando mayúsculas y minúsculas
            if (valorAtributo != null && valorAtributo.equalsIgnoreCase(valor)) {
                destinosFiltrados.add(destino);
            }
        }

        if (destinosFiltrados.isEmpty()) {
            System.out.println("No se encontraron destinos que coincidan con el filtro proporcionado.");
        } else {
            System.out.println(destinosFiltrados);
        }
        return destinosFiltrados;
    }

    /**
     * Metodo encargado de mostrarme toda la ventana de Ofertas.
     *
     * @param destinos
     */

    public void mostrarDestinos(ArrayList<Destino> destinos) {
        gridOfertas.getChildren().clear();
        int anchorPanelCount = 0;
        for (Destino destino : destinos) {
            try {

                //Cargar el AnchorPane desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/DiseñoDestinos.fxml"));
                AnchorPane nuevoAnchorPane = loader.load();
                anchorPanelCount++;


                // Establece el tamaño deseado para las filas y columnas en el GridPane
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(310); // Establece la altura deseada para las filas

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(10); // Establece el ancho deseado para las columnas
                if (gridOfertas != null) {
                    // Aplica las restricciones a todas las filas y columnas
                    gridOfertas.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
                    gridOfertas.getColumnConstraints().addAll(columnConstraints, columnConstraints);


                    //Se setan los valores de los objetos en los labels del anchorPane (Cart)
                    Label lblPais = (Label) nuevoAnchorPane.lookup("#lblPais");
                    lblPais.setText("Pais: " + destino.getPais());

                    Label lblCiudad = (Label) nuevoAnchorPane.lookup("#lblCiudad");
                    lblCiudad.setText("Ciudad: " + destino.getCiudad());

                    Label lblClima = (Label) nuevoAnchorPane.lookup("#lblClima");
                    lblClima.setText("Clima: " + destino.getClima());

                    TextArea lblDescipcion = (TextArea) nuevoAnchorPane.lookup("#lblDescipcion");
                    lblDescipcion.setText(destino.getDescripcion());

                    Label lblPrecio = (Label) nuevoAnchorPane.lookup("#lblPrecio");
                    lblPrecio.setText("Precio: " + destino.getPrecio());


                    ImageView imgView = (ImageView) nuevoAnchorPane.lookup("#imagenVuelo");
                    try {
                        String rutaImagen = destino.getImagenes();

                        if (rutaImagen != null) {
                            Image image = new Image(rutaImagen);
                            imgView.setImage(image);

                            // Establece el Ancho y alto de las imágenes
                            double width = 240; // Ancho deseado
                            double height = 150; // Alto deseado
                            imgView.setFitWidth(width);
                            imgView.setFitHeight(height);
                        } else {
                            // Manejar el caso en que la ruta de la imagen es nula
                            System.out.println("La ruta de la imagen para el destino " + destino.getPais() + " es nula.");
                            // Puedes asignar una imagen predeterminada o mostrar un mensaje al usuario, según tus necesidades
                        }
                    } catch (VerificarExceptionNull e) {
                        // Manejar la excepción específica para el caso de ruta de imagen nula
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        // Manejar otras excepciones
                        throw new CargarImagenException("ERROR al cargar la imagen para el destino: " + destino.getPais(), e);
                    }


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
                    gridOfertas.getChildren().add(nuevoAnchorPane);
                    if (anchorPanelCount > 0) {
                        Insets margins = new Insets(paneSpacing, paneSpacing, paneSpacing, paneSpacing);
                        GridPane.setMargin(nuevoAnchorPane, margins);
                    }

                    scrollPanelContenedor.setVvalue(1.0);
                } else {
                    System.out.println("no se cargo el gridPane");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtBuscador};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnBuscar.fire();
                }
            });
        }
    }

    /**
     * Accion del botonBuscar.
     *
     * @param event
     */

    @FXML
    void onBuscar(ActionEvent event) {
        String filtroSeleccionado = comboBoxFiltro.getValue();
        String valorBusqueda = txtBuscador.getText();
        if (filtroSeleccionado != null && !valorBusqueda.isEmpty()) {
            //buscarDestinoPorFiltro(filtroSeleccionado,valorBusqueda);
            mostrarDestinos(buscarDestinoPorFiltro(filtroSeleccionado, valorBusqueda));
        } else {
            mostrarDestinos(destinosCargados);
            //gridOfertas = null;
            System.out.println("Seleccione un filtro y proporcione un valor de búsqueda.");
        }
    }

    /**
     * Metodo que inicia todo lo de la ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarDestinos(destinosCargados);
        comboBoxFiltro.getItems().addAll("Pais", "Ciudad", "Clima", "Precio", "ID", "Cupo");
        inicializarEnterKey();


    }


    public void filtrado(ActionEvent event) {
    }
}
