package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacionDestinos;
import Cliente.modelo.objetos.Destino;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaCartsController implements Initializable {
    public static ArrayList<Destino> destinos = new ArrayList<>();
    @FXML
    private GridPane gridOfertas;
    @FXML
    private AnchorPane paneOfertas;
    @FXML
    private ScrollPane scroll_pane;
    @FXML
    private CartsController cartsController;
    private final double paneSpacing = 10.0; // Espacio entre los AnchorPane
    private int anchorPanelCount = 0;
    private final int maxColumns = 2; // Número máximo de columnas

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Cargar los destinos desde el archivo
        ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");

        for (Destino destino : destinos) {
            try {
                //Cargar el AnchorPane desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/Carts.fxml"));
                AnchorPane nuevoAnchorPane = loader.load();
                anchorPanelCount++;

                //Se setan los valores de los objetos en los labels del anchorPane (Cart)
                Label lblPais = (Label) nuevoAnchorPane.lookup("#lblPais");
                lblPais.setText("Pais: " + destino.getPais());

                Label lblCiudad = (Label) nuevoAnchorPane.lookup("#lblCiudad");
                lblCiudad.setText("Ciudad: " + destino.getCiudad());

                Label lblClima = (Label) nuevoAnchorPane.lookup("#lblClima");
                lblClima.setText("Clima: " + destino.getClima());


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
                    Insets margins = new Insets(0.0, paneSpacing, paneSpacing, 0.0);
                    GridPane.setMargin(nuevoAnchorPane, margins);
                }


                scroll_pane.setVvalue(1.0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
