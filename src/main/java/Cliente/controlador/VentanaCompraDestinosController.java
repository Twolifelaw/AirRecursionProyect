package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static Cliente.modelo.Serializacion.GestionSerializacionDestinos.deserializarDestino;

public class VentanaCompraDestinosController implements Initializable {
    //
    @FXML
    private Button btnComprar;

    @FXML
    private Button btnReserva;

    @FXML
    private ImageView imgDestinos;

    @FXML
    private TextArea txtPropiedadesDestino;

    @FXML
    private Label lblCiudad;

    @FXML
    private Label lblClima;

    @FXML
    private Label lblPais;

    @FXML
    private Label lblPrecio;
    //De aqui para arriba van los componentes.

    /**
     * Accion de botonReservas.
     *
     * @param event
     */
    @FXML
    void onReserva(ActionEvent event) {

    }

    /**
     * Accion de botonComprar.
     *
     * @param event
     */

    @FXML
    void onComprar(ActionEvent event) {
        String aux = "Pais: ";
        for (Destino destino : deserializarDestino("destinos.dat")) {
            if (aux.concat(destino.getPais()).equals(lblPais.getText())) {

                System.out.println("si");
            }
        }
    }

    //Esta parte del codigo esta por definirse.
    public TextArea getTxtPropiedadesDestino() {
        return txtPropiedadesDestino;
    }

    public ImageView getImgDestinos() {
        return imgDestinos;
    }

    public void setImgDestinos(ImageView imgDestinos) {
        this.imgDestinos = imgDestinos;
    }

    public Label getLblCiudad() {
        return lblCiudad;
    }


    public Label getLblClima() {
        return lblClima;
    }


    public Label getLblPais() {
        return lblPais;
    }

    public Label getLblPrecio() {
        return lblPrecio;
    }
    //

    /**
     * Metodo que inicializa lo que va llevar la ventana.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
