package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacionDestinos;
import Cliente.modelo.objetos.Destino;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaCartsController implements Initializable {
    public static ArrayList<Destino> destinos = new ArrayList<>();
    private final double paneSpacing = 5.0; // Espacio entre los AnchorPane
    private final int maxColumns = 3; // Número máximo de columnas
    @FXML
    private GridPane gridOfertas;
    @FXML
    private AnchorPane paneOfertas;
    @FXML
    private ScrollPane scroll_pane;
    @FXML
    private CartsController cartsController;
    private int anchorPanelCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Cargar los destinos desde el archivo
        ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");

        for (Destino destino : destinosCargados) {
            try {
                //Cargar el AnchorPane desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/Carts.fxml"));
                AnchorPane nuevoAnchorPane = loader.load();
                anchorPanelCount++;

                // Establece el tamaño deseado para las filas y columnas en el GridPane
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(310); // Establece la altura deseada para las filas

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(10); // Establece el ancho deseado para las columnas

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

                ImageView imgView = (ImageView) nuevoAnchorPane.lookup("#imagenVuelo");
                Image image = new Image(destino.getImagenes());
                imgView.setImage(image);


                double width = 258; // Ancho deseado
                double height = 165; // Alto deseado
                imgView.setFitWidth(width);
                imgView.setFitHeight(height);


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
                GridPane.setHgrow(nuevoAnchorPane, javafx.scene.layout.Priority.ALWAYS);

                //Agregar al cart al GridePane
                gridOfertas.getChildren().add(nuevoAnchorPane);
                if (anchorPanelCount > 0) {
                    Insets margins = new Insets(paneSpacing, paneSpacing, paneSpacing, paneSpacing);
                    GridPane.setMargin(nuevoAnchorPane, margins);
                }


                scroll_pane.setVvalue(1.0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
