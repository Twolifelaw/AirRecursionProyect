package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    private Button btnDer;

    @FXML
    private Button btnIzq;


    private Destino destino;
    private int indiceImagenActual;


    @FXML
    void ActionBtnDer(ActionEvent event) {


    }

    @FXML
    void actionBtnIzq(ActionEvent event) {

    }


    public Button getBtnComprar() {
        return btnComprar;
    }

    public void setBtnComprar(Button btnComprar) {
        this.btnComprar = btnComprar;
    }

    public ImageView getImagenVuelo() {
        return imagenVuelo;
    }


    public Label getLblCiudad() {
        return lblCiudad;
    }

    public void setLblCiudad(Label lblCiudad) {
        this.lblCiudad = lblCiudad;
    }

    public Label getLblClima() {
        return lblClima;
    }

    public void setLblClima(Label lblClima) {
        this.lblClima = lblClima;
    }

    public Label getLblPais() {
        return lblPais;
    }

    public void setLblPais(Label lblPais) {
        this.lblPais = lblPais;
    }


}
