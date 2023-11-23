package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import Cliente.modelo.objetos.PaqueteTuristico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static Cliente.modelo.Serializacion.GestionPaquetes.deserializarPaquetes;
import static Cliente.modelo.Serializacion.GestionPaquetes.serializarPaquetes;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.deserializarDestino;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.serializarDestino;

public class DiseñoPaquetesController {

    @FXML
    private Button btnDisLike;

    @FXML
    private Button btnLike;

    @FXML
    private TextArea txtAreaDestinos;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblDuracion;
    @FXML
    private Label lblprecio;
    @FXML
    private Label lblServicios;

    @FXML
    private Label lblcupoMaximo;

    @FXML
    private ImageView imgvImagensPaquetes;


    public void actionVerMas(ActionEvent event) {

    }

    @FXML
    void onDisLike(ActionEvent event) {
        ArrayList<PaqueteTuristico> paquetes = deserializarPaquetes("paquetes.dat");
        if(paquetes!=null){

            for(PaqueteTuristico paquete:paquetes){
                if(paquete.getNombre().equals(lblNombre.getText())){
                    int aux = paquete.getNumDislikes();
                    paquete.setNumDislikes(aux);
                    System.out.println(paquete.getNumDislikes());
                    break;
                }
            }
            serializarPaquetes("destinos.dat",paquetes);

        }

    }

    @FXML
    void onLike(ActionEvent event) {
        ArrayList<PaqueteTuristico> paquetes = deserializarPaquetes("paquetes.dat");
        if(paquetes!=null){

            for(PaqueteTuristico paquete:paquetes){
                if(paquete.getNombre().equals(lblNombre.getText())){
                    int aux = paquete.getNumlikes()+1;
                    paquete.setNumlikes(aux);
                    System.out.println(paquete.getNumlikes());
                    break;
                }
            }
            serializarPaquetes("destinos.dat",paquetes);

        }

    }



}
