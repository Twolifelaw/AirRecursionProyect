package Cliente.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaAdministradorController implements Initializable {
    //
    @FXML
    private AnchorPane anchorPaneAdministrador;

    @FXML
    private Label lblReloj;

    @FXML
    private AnchorPane anchorPaneBienvenida;

    @FXML
    private AnchorPane anchorPaneContenedor;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnDestinos;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnPaquetes;
    //De aqui para arriba van los componentes

    /**
     * Accion de botonClientes.
     *
     * @param event
     */
    @FXML
    void onClientes(ActionEvent event) {
        limpiarContenido();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaClientes.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Accion de botonDestinos.
     *
     * @param event
     */

    @FXML
    void onDestinos(ActionEvent event) {
        limpiarContenido();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaCreacionDestinos.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * metodo que se encarga de limpiar los anchorPane.
     */

    private void limpiarContenido() {
        anchorPaneBienvenida.getChildren().clear();
        anchorPaneContenedor.getChildren().clear();
    }

    /**
     * Accion de botonEstadisticas.
     *
     * @param event
     */
    @FXML
    void onEstadisticas(ActionEvent event) {
        limpiarContenido();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaEstadistica.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Accion de botonPaquetes.
     *
     * @param event
     */
    @FXML
    void onPaquetes(ActionEvent event) {
        limpiarContenido();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaCreacionPaquetes.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Accion de botonRegresar.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onRegresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private void inicializarReloj() {
        // Crear un objeto Timeline para actualizar el reloj cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String horaActual = sdf.format(new Date());
            lblReloj.setText(horaActual);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    /**
     * Metodo donde se inicializa lo de esta ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarReloj();
        VentanaUtilidades.agregarAnimacionBoton(btnDestinos);
        VentanaUtilidades.agregarAnimacionBoton(btnRegresar);
        VentanaUtilidades.agregarAnimacionBoton(btnClientes);
        VentanaUtilidades.agregarAnimacionBoton(btnPaquetes);
        VentanaUtilidades.agregarAnimacionBoton(btnEstadisticas);
    }
}
