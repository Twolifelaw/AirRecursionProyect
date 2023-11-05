package Cliente.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VentanaInicioController {

    private static int i = 0;
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

        paneInternacionales.toFront();

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

        paneNacionales.toFront();

    }

    @FXML
    void OnOfertas(ActionEvent event) throws IOException {

        paneOfertas.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaCarts.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                paneOfertas.getChildren().add(anchorPaneOfertas);
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
}
