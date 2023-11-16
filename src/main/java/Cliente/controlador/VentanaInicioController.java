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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaInicioController implements Initializable {

    @FXML
    private AnchorPane anc_bienvenida;

    @FXML
    private Button btnAyuda;

    @FXML
    private AnchorPane anc_botones;

    @FXML
    private AnchorPane anc_contenedor;

    @FXML
    private AnchorPane anc_inicio;

    @FXML
    private ImageView avion_1;

    @FXML
    private ImageView avion_2;

    @FXML
    private Button btn_chat;

    @FXML
    private Button btn_cuenta;

    @FXML
    private Button btn_internacionales;

    @FXML
    private Button btn_mapa;

    @FXML
    private Button btn_nacionales;

    @FXML
    private Button btn_ofertas;

    @FXML
    private Button btn_paquete;

    @FXML
    private Button btn_salir;


    @FXML
    void OnMiCuenta(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/ventanaMiCuenta.fxml"));
        Parent root = loader.load();

        // Obtén el controlador de la ventana Mi Cuenta
        VentanaMicuentaController miCuentaController = loader.getController();

        // Llama al método para actualizar los campos de texto
        miCuentaController.actualizarCamposTexto();

        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();

        // en esta línea, esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void OnAyuda(ActionEvent event) {
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
        // en esta línea, esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void anchorDesaparecer(ActionEvent event) {
        anc_bienvenida.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animacionElementos();
    }

    public void animacionElementos() {
        VentanaUtilidades.agregarAnimacionBoton(btn_nacionales);
        VentanaUtilidades.agregarAnimacionBoton(btn_internacionales);
        VentanaUtilidades.agregarAnimacionBoton(btn_ofertas);
        VentanaUtilidades.agregarAnimacionBoton(btn_cuenta);
        VentanaUtilidades.agregarAnimacionBoton(btn_mapa);
        VentanaUtilidades.agregarAnimacionBoton(btn_paquete);
        VentanaUtilidades.agregarAnimacionBoton(btn_chat);
        VentanaUtilidades.agregarAnimacionBoton(btn_salir);
        VentanaUtilidades.agregarAnimacionBoton(btnAyuda);
        //Avion 1
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(avion_1);
        rotate.setDuration(Duration.millis(1700));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
        //Avion 2
        rotate.setNode(avion_2);
        rotate.setDuration(Duration.millis(1700));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
    }


}
