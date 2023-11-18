package Cliente.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaAdministradorController implements Initializable {

    @FXML
    private AnchorPane anc_administrador;
    @FXML
    private AnchorPane anc_bienvenida;


    @FXML
    private AnchorPane anc_contenedor;

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
        limpiarContenido();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanacreacionDestinos.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anc_contenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void limpiarContenido() {
        anc_bienvenida.getChildren().clear();
        anc_contenedor.getChildren().clear();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VentanaUtilidades.agregarAnimacionBoton(btnDestinos);
        VentanaUtilidades.agregarAnimacionBoton(btn_regresar);
        VentanaUtilidades.agregarAnimacionBoton(btnClientes);
        VentanaUtilidades.agregarAnimacionBoton(btnPaquetes);
        VentanaUtilidades.agregarAnimacionBoton(btnEstadisticas);
    }
}
