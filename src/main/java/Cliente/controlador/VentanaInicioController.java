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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class VentanaInicioController implements Initializable {
    //
    @FXML
    private AnchorPane anchorPaneBienvenida;

    @FXML
    private Button btnAyuda;

    @FXML
    private AnchorPane anchorPaneBotones;

    @FXML
    private AnchorPane anchorPaneContenedor;

    @FXML
    private AnchorPane anchorPaneInicio;

    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private ImageView imagenUsuario;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnCuenta;

    @FXML
    private Button btnInternacionales;

    @FXML
    private Button btnMapa;

    @FXML
    private Button btnNacionales;

    @FXML
    private Button btnOfertas;

    @FXML
    private Button btnPaquetes;

    @FXML
    private Button btnSalir;
    //De aqui para arriba son componentes.

    /**
     * Accion del botonMiCuenta.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onMiCuenta(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/VentanaMiCuenta.fxml"));
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

    /**
     * Accion de botonMapa.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void OnMapa(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Mapa.fxml"));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();

    }

    /**
     * Accion de botonPaquetes.
     *
     * @param event
     */

    @FXML
    void onPaquetes(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/VentanaPaquetes.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea, esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    /**
     * Accion de botonChat.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onChat(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();

    }

    /**
     * Accion del botonSalir.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onSalir(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/Login/ventanaLogin.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta línea, esconde el stage del login y carga el nuevo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Accion de botonAyuda.
     *
     * @param event
     */

    @FXML
    void onAyuda(ActionEvent event) {
    }

    /**
     * Accion del botonInternacionales.
     *
     * @param event
     */

    @FXML
    void onInternacionales(ActionEvent event) {
        anchorPaneContenedor.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaInternacional.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().clear();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Accion del botonOfertas.
     *
     * @param event
     * @throws IOException
     */

    @FXML
    void onOfertas(ActionEvent event) throws IOException {
        anchorPaneContenedor.toFront();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaOfertas.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().clear();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Accion de botonNacionales.
     *
     * @param event
     */

    @FXML
    void onNacionales(ActionEvent event) {

        anchorPaneContenedor.toFront();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/vista/ventanas/VentanaVueloNacional.fxml"));
                AnchorPane anchorPaneOfertas = loader.load();
                anchorPaneContenedor.getChildren().clear();
                anchorPaneContenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Metodo que inicia todo en la ventana.
     *
     * @param location
     * @param resources
     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animacionElementos();
    }

    public void animacionElementos() {
        VentanaUtilidades.agregarAnimacionBoton(btnNacionales);
        VentanaUtilidades.agregarAnimacionBoton(btnInternacionales);
        VentanaUtilidades.agregarAnimacionBoton(btnOfertas);
        VentanaUtilidades.agregarAnimacionBoton(btnCuenta);
        VentanaUtilidades.agregarAnimacionBoton(btnMapa);
        VentanaUtilidades.agregarAnimacionBoton(btnPaquetes);
        VentanaUtilidades.agregarAnimacionBoton(btnChat);
        VentanaUtilidades.agregarAnimacionBoton(btnSalir);
        VentanaUtilidades.agregarAnimacionBoton(btnAyuda);
        VentanaUtilidades.girarImagen(imagenAvion1);
        VentanaUtilidades.girarImagen(imagenAvion2);
        VentanaUtilidades.girarImagen(imagenUsuario);

    }
}
