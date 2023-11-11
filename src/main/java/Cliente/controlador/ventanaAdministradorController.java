package Cliente.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ventanaAdministradorController {


    @FXML
    private Button btn_regresar;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnDestinos;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnPaquetes;

    @FXML
    private BorderPane borderPanePrincipla;

    @FXML
    void actionbtnClientes(ActionEvent event) {

    }

    @FXML
    void actionbtnDestinos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanacreacionDestinos.fxml"));
            AnchorPane anchorPaneOfertas = loader.load();
            borderPanePrincipla.setCenter(anchorPaneOfertas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionbtnEstadisticas(ActionEvent event) {

    }

    @FXML
    void actionbtnPaquetes(ActionEvent event) {

    }
    @FXML
    void regresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}
