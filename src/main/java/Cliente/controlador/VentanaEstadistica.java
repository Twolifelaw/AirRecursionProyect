package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import Cliente.modelo.objetos.PaqueteTuristico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionPaquetes.deserializarPaquetes;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.deserializarDestino;

public class VentanaEstadistica implements Initializable {

    @FXML
    public static ArrayList<Destino> destinos = deserializarDestino("destinos.dat");
    @FXML
    public static ArrayList<PaqueteTuristico> paqueteTuristicoArrayList = deserializarPaquetes("paquetes.dat");
    @FXML
    private CategoryAxis CategoriAxis;

    @FXML
    private CategoryAxis CategoriPrecioPaquetes;

    @FXML
    private CategoryAxis CategoryCupos;

    @FXML
    private CategoryAxis CategoryCuposPaquetes;

    @FXML
    private CategoryAxis CategoryDisLike;

    @FXML
    private CategoryAxis CategoryDisLikeP;

    @FXML
    private NumberAxis NumberAxis;

    @FXML
    private NumberAxis NumberCupos;

    @FXML
    private NumberAxis NumberCuposPaquetes;

    @FXML
    private NumberAxis NumberLike;

    @FXML
    private NumberAxis NumberLikeP;

    @FXML
    private NumberAxis NumberPrecioPaquetes;

    @FXML
    private AnchorPane anchorPaneStats;

    @FXML
    private BarChart<String, Number> barCharsPrecioPaquetes;

    @FXML
    private BarChart<String, Number> barChartPrecioDestinos;

    @FXML
    private BarChart<String, Number> bartCharLikeDestinos;

    @FXML
    private BarChart<String, Number> bartChartCuposDestinos;

    @FXML
    private BarChart<String, Number> bartChartCuposPaquete;

    @FXML
    private BarChart<String, Number> bartChartDislikeDestinos;

    @FXML
    private BarChart<String, Number> bartChartDislikePaquetes;

    @FXML
    private BarChart<String, Number> bartChartLikePaquetes;

    @FXML
    private CategoryAxis categoryLike;

    @FXML
    private CategoryAxis categoryLikeP;

    @FXML
    private NumberAxis numberDisLike;

    @FXML
    private NumberAxis numberDisLikeP;

    private void iniciarDestinos(){
        XYChart.Series<String, Number> precioDestinos = new XYChart.Series<>();
        XYChart.Series<String, Number> cupoDestinos = new XYChart.Series<>();
        XYChart.Series<String,Number> likeDestinos = new XYChart.Series<>();
        XYChart.Series<String,Number> dislikeDestinos = new XYChart.Series<>();



        //Destinos
        for (Destino destino : destinos) {

            precioDestinos.getData().add(new XYChart.Data<>(destino.getCiudad(),Integer.parseInt(destino.getPrecio())));
            cupoDestinos.getData().add(new XYChart.Data<>(destino.getCiudad(), destino.getNumeroCupos()));
            likeDestinos.getData().add(new XYChart.Data<>(destino.getCiudad(),destino.getNumLike()));
            dislikeDestinos.getData().add(new XYChart.Data<>(destino.getCiudad(),destino.getNumDisLike()));
        }

        bartCharLikeDestinos.getData().addAll(likeDestinos);
        bartChartDislikeDestinos.getData().addAll(dislikeDestinos);
        barChartPrecioDestinos.getData().addAll(precioDestinos);
        bartChartCuposDestinos.getData().addAll(cupoDestinos);


    }

    private void iniciarPaquetes(){
        //Paquetes Touristicos

        XYChart.Series<String, Number> precioPaquetes = new XYChart.Series<>();
        XYChart.Series<String, Number> cuposPaquetes = new XYChart.Series<>();
        XYChart.Series<String,Number> paquetesLike = new XYChart.Series<>();
        XYChart.Series<String,Number> paquetesDislike = new XYChart.Series<>();


        for (PaqueteTuristico paqueteTuristico : paqueteTuristicoArrayList) {
            precioPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(), paqueteTuristico.getPrecio()));
            cuposPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(), paqueteTuristico.getCuposMaximos()));
            paquetesLike.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(),paqueteTuristico.getNumlikes()));
            paquetesDislike.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(),paqueteTuristico.getNumDislikes()));

        }
        barCharsPrecioPaquetes.getData().add(precioPaquetes);
        bartChartCuposPaquete.getData().add(cuposPaquetes);
        bartChartLikePaquetes.getData().add(paquetesLike);
        bartChartDislikePaquetes.getData().add(paquetesDislike);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarDestinos();
        iniciarPaquetes();

    }
}
