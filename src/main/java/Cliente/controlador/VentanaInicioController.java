package Cliente.controlador;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaInicioController implements Initializable {

    private static int i = 0;

    @FXML
    private AnchorPane anc_Inicio;
    @FXML
    private Pane paneImagen;
    @FXML
    private BorderPane borderPaneOfertas;
    @FXML
    private Pane paneInternacionales;
    @FXML
    private Pane paneNacionales;
    @FXML
    private Pane paneOfertas;
    @FXML
    private Pane paneSoporte;

    @FXML
    private ImageView avion_1;

    @FXML
    private ImageView avion_2;
    @FXML
    private AnchorPane anc_contenedor;


    @FXML
    void OnAyuda(ActionEvent event) {

        paneSoporte.toFront();

    }

    @FXML
    void OnChat(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();

    }

    @FXML
    void OnDerecha(ActionEvent event) {

        i++;

        if (i == 0) {
            paneImagen.toFront();
        } else if (i == 1) {
            paneOfertas.toFront();

        } else if (i == 2) {
            paneNacionales.toFront();
        } else if (i == 3) {
            paneInternacionales.toFront();
        } else if (i == 4) {
            paneSoporte.toFront();
        } else {
            i = 0;
        }

    }

    @FXML
    void OnInternacionales(ActionEvent event) {
        anc_contenedor.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/ventanaInternacional.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anc_contenedor.getChildren().clear();
                anc_contenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void OnIzquierda(ActionEvent event) {
        i--;

        if (i == 0) {
            paneImagen.toFront();
        } else if (i == 1) {
            paneOfertas.toFront();

        } else if (i == 2) {
            paneNacionales.toFront();
        } else if (i == 3) {
            paneInternacionales.toFront();
        } else if (i == 4) {
            paneSoporte.toFront();
        } else {
            i = 0;
        }


    }

    @FXML
    void OnMapa(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Mapa.fxml"));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();

    }

    @FXML
    void OnMiCuenta(ActionEvent event) {

    }

    @FXML
    void OnNacionales(ActionEvent event) {

        anc_contenedor.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/ventanaVueloNacional.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anc_contenedor.getChildren().clear();
                anc_contenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @FXML
    void OnOfertas(ActionEvent event) throws IOException {

        anc_contenedor.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaCarts.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anc_contenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void OnPaquetes(ActionEvent event) {

    }

    @FXML
    void OnSalir(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();


    }

    @FXML
    void anchorDesaparecer(ActionEvent event) {
        anc_Inicio.setVisible(false);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(avion_1);
        rotate.setDuration(Duration.millis(1700));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
        //Aqui acaba el codigo de la animaciond e la primera imagen.

        //Aqui inicia el de la segunda.
        rotate.setNode(avion_2);
        rotate.setDuration(Duration.millis(1700));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();

    }


}
