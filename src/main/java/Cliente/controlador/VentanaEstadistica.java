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
    public BarChart<String, Number> bartChartCuposPaquete;
    @FXML
    public CategoryAxis CategoryCuposPaquetes;
    @FXML
    public NumberAxis NumberCuposPaquetes;
    @FXML
    public BarChart<String, Number> barCharsPrecioPaquetes;
    @FXML
    public CategoryAxis CategoriPrecioPaquetes;
    @FXML
    public NumberAxis NumberPrecioPaquetes;
    @FXML
    public BarChart<String,Number> bartChartDisLike;
    @FXML
    public CategoryAxis CategoryDisLike;
    @FXML
    public NumberAxis numberDisLike;
    @FXML
    public BarChart<String,Number>bartChartLike;
    @FXML
    public CategoryAxis categoryLike;
    @FXML
    public NumberAxis NumberLike;
    @FXML
    private AnchorPane anchorPaneEstadisticas;
    @FXML
    private CategoryAxis CategoriAxis;
    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private BarChart<String, Number> barChars;
    @FXML
    private CategoryAxis CategoryCupos;
    @FXML
    private NumberAxis NumberCupos;
    @FXML
    private BarChart<String, Number> bartChartCupos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesCupos = new XYChart.Series<>();
        XYChart.Series<String,Number> serisLike = new XYChart.Series<>();
        XYChart.Series<String,Number> serisDisLike = new XYChart.Series<>();
        //Destinos
        for (Destino destino : destinos) {
            series1.getData().add(new XYChart.Data<>(destino.getCiudad(), Integer.parseInt(destino.getPrecio())));
            seriesCupos.getData().add(new XYChart.Data<>(destino.getCiudad(), destino.getNumeroCupos()));
            serisLike.getData().add(new XYChart.Data<>(destino.getCiudad(),destino.getNumLike()));
            serisDisLike.getData().add(new XYChart.Data<>(destino.getCiudad(),destino.getNumDisLike()));

        }


        //Paquetes Touristicos

        XYChart.Series<String, Number> seriePrecioPaquetes = new XYChart.Series<>();
        XYChart.Series<String, Number> serieCuposPaquetes = new XYChart.Series<>();
        for (PaqueteTuristico paqueteTuristico : paqueteTuristicoArrayList) {
            seriePrecioPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(), paqueteTuristico.getPrecio()));
            serieCuposPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(), paqueteTuristico.getCuposMaximos()));
        }




        barCharsPrecioPaquetes.getData().addAll(seriePrecioPaquetes);
        bartChartCuposPaquete.getData().addAll(serieCuposPaquetes);

        barChars.getData().addAll(series1);
        bartChartCupos.getData().addAll(seriesCupos);

        bartChartLike.getData().addAll(serisLike);
        bartChartDisLike.getData().addAll(serisDisLike);






    }
}
