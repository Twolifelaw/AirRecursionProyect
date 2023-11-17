package Cliente.controlador;

import Cliente.modelo.Serializacion.GestionSerializacionDestinos;
import Cliente.modelo.objetos.Destino;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class VentanaInicioController implements Initializable {

    public ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");


    @FXML
    private ComboBox<String> cbx_filtro;
    @FXML
    private Label lblStatus;
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
    private ImageView img_usuario;

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
    private Button btnBuscar;

    @FXML
    private TextField txt_buscador;
    VentanaCartsController ventanaCartsController = new VentanaCartsController();

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
    void filtrado(ActionEvent event) {

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
                anc_contenedor.getChildren().clear();
                anc_contenedor.getChildren().add(anchorPaneOfertas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void onBuscar(ActionEvent event) {
        String filtroSeleccionado = cbx_filtro.getValue();
        String valorBusqueda = txt_buscador.getText();
        if(filtroSeleccionado != null && !valorBusqueda.isEmpty()){
            buscarDestinoPorFiltro(filtroSeleccionado,valorBusqueda);
        }else{
            System.out.println("Seleccione un filtro y proporcione un valor de búsqueda.");
        }
    }
    private void buscarDestinoPorFiltro(String filtro, String valor) {
        ArrayList<Destino> destinosFiltrados = new ArrayList<>();

        for (Destino destino : destinosCargados) {
            String valorAtributo = null;

            switch (filtro.toLowerCase()) {
                case "pais":
                    valorAtributo = destino.getPais();
                    break;
                case "ciudad":
                    valorAtributo = destino.getCiudad();
                    break;
                case "clima":
                    valorAtributo = destino.getClima();
                    break;
                case "precio":
                    valorAtributo = destino.getPrecio();
                    break;
                case "id":
                    valorAtributo = destino.getId();
                    break;
                case "cupos":
                    valorAtributo = String.valueOf(destino.getNumeroCupos());
                    break;
                // Agrega más casos según tus atributos
                default:
                    // Manejar el caso en que el filtro no coincide con ningún atributo
                    System.out.println("Filtro no válido");
                    return;
            }

            // Realiza la comparación, ignorando mayúsculas y minúsculas
            if (valorAtributo != null && valorAtributo.equalsIgnoreCase(valor)) {
                destinosFiltrados.add(destino);
            }
        }

        if (destinosFiltrados.isEmpty()) {
            System.out.println("No se encontraron destinos que coincidan con el filtro proporcionado.");
        } else {
            ventanaCartsController.mostrarDestinos(destinosFiltrados);
        }
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
        VentanaUtilidades.girarImagen(avion_1);
        VentanaUtilidades.girarImagen(avion_2);
        VentanaUtilidades.girarImagen(img_usuario);
        cbx_filtro.getItems().addAll("Pais","Ciudad","Clima","Precio","ID","Cupo");
    }
}
