package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.*;
import java.io.IOException;
import java.util.ArrayList;

public class DiseñoDestinosController {
    public Button btnLike;
    public Button btnDisLike;
    //
    @FXML
    private Button btnComprar;

    @FXML
    private ImageView imagenVuelo;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblClima;

    @FXML
    private Label lblPais;

    @FXML
    private TextArea lblDescipcion;

    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblId;

    //public static ArrayList<Destino> destinos = deserializarDestino("destinos.dat");
    //De aqui para arriba son los componentes.

    /**
     * Accion que hace el botonComprar
     *
     * @param event
     * @throws IOException
     */

    public void onComprar(ActionEvent event) throws IOException {
        // Crear una instancia de cargador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/VentanaCompraDestinos.fxml"));

        // Cargue el archivo FXML
        Parent root = loader.load();

        // Acceder al controlador asociado al archivo FXML
        VentanaCompraDestinosController controller = loader.getController();

        // Now you can access the TextArea or any other non-static methods from the controller
        TextArea txtPropiedadesDestino = controller.getTxtPropiedadesDestino();
        txtPropiedadesDestino.setText(lblDescipcion.getText());

        Label labelPaisV = controller.getLblPais();
        labelPaisV.setText(lblPais.getText());

        Label lblCiudadV = controller.getLblCiudad();
        lblCiudadV.setText(lblCiudad.getText());

        Label lblClimaV = controller.getLblClima();
        lblClimaV.setText(lblClima.getText());

        Label lblPrecioV = controller.getLblPrecio();
        lblPrecioV.setText(lblPrecio.getText());

        Label lblIdV = controller.getLblid();
        lblIdV.setText(lblId.getText());



        ImageView imageView = controller.getImgDestinos();
        imageView.setImage(imagenVuelo.getImage());


        // Create a new stage and set the scene
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);

        // Show the new stage
        stage.show();

        // Hide the stage of the login
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }



    public void onDisLike(ActionEvent mouseEvent) {
        ArrayList<Destino> destinos = deserializarDestino("destinos.dat");
        if(destinos!=null){
            for(Destino destino:destinos){
                if(destino.getId().equals(lblId.getText())){
                    int aux = destino.getNumDisLike()+1;
                    destino.setNumDisLike(aux);
                    break;
                }

            }
            serializarDestino("destinos.dat",destinos);
        }
    }

    public void onLike(ActionEvent event) {
        ArrayList<Destino> destinos = deserializarDestino("destinos.dat");
        if(destinos!=null){

            for(Destino destino:destinos){
                if(destino.getId().equals(lblId.getText())){
                    int aux = destino.getNumLike()+1;
                    destino.setNumLike(aux);
                    System.out.println(destino.getNumLike());
                    break;
                }

            }
            serializarDestino("destinos.dat",destinos);

        }
    }
}
