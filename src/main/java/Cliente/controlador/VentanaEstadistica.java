package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import Cliente.modelo.objetos.PaqueteTuristico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.*;
import static Cliente.modelo.Serializacion.GestionPaquetes.*;

public class VentanaEstadistica implements Initializable {

    public BarChart<String,Number> bartChartCuposPaquete;
    public CategoryAxis CategoryCuposPaquetes;
    public NumberAxis NumberCuposPaquetes;
    public BarChart<String,Number> barCharsPrecioPaquetes;
    public CategoryAxis CategoriPrecioPaquetes;
    public NumberAxis NumberPrecioPaquetes;
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

    public static ArrayList<Destino> destinos = deserializarDestino("destinos.dat");
    public static ArrayList<PaqueteTuristico> paqueteTuristicoArrayList = deserializarPaquetes("paquetes.dat");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesCupos = new XYChart.Series<>();
        //Destinos
        for (Destino destino : destinos){
            series1.getData().add(new XYChart.Data<>(destino.getPais(),Integer.parseInt(destino.getPrecio())));
            seriesCupos.getData().add(new XYChart.Data<>(destino.getPais(),destino.getNumeroCupos()));

        }


        //Paquetes Touristic

        XYChart.Series<String,Number> seriePrecioPaquetes = new XYChart.Series<>();
        XYChart.Series<String,Number> serieCuposPaquetes = new XYChart.Series<>();
        for (PaqueteTuristico paqueteTuristico :paqueteTuristicoArrayList ){
            seriePrecioPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(),paqueteTuristico.getPrecio()));
            serieCuposPaquetes.getData().add(new XYChart.Data<>(paqueteTuristico.getNombre(),paqueteTuristico.getCuposMaximos()));
        }

        barCharsPrecioPaquetes.getData().addAll(seriePrecioPaquetes);
        bartChartCuposPaquete.getData().addAll(serieCuposPaquetes);

        barChars.getData().addAll(series1);
        bartChartCupos.getData().addAll(seriesCupos);





    }
}
