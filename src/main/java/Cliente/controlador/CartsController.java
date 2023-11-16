package Cliente.controlador;

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

import java.io.IOException;

public class CartsController {

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
    private Label lblDescipcion;
    @FXML
    private Label lblPrecio;


    public void ActionComprar(ActionEvent event) throws IOException {

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


}
