package Cliente.controlador;

import Cliente.modelo.objetos.Destino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.io.IOException;
import java.io.*;
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
    void onComprar(ActionEvent event) throws IOException {
        String aux = "Pais: ";
        for (Destino destino : deserializarDestino("destinos.dat")) {
            if (aux.concat(destino.getPais()).equals(lblPais.getText())) {

                int aux2 = destino.getNumeroCupos();
                destino.setNumeroCupos(aux2-1);

            }
        }

        String rutaDescargas = System.getProperty("user.home") + "/Downloads/";
        String nombreArchivo = "ComprobanteDePago.pdf";
        String rutaCompleta = rutaDescargas + nombreArchivo;
        String contenido = lblPais.getText() + "    " + lblCiudad.getText() + "   "
                + lblClima.getText() + " " +lblPrecio.getText();


        crearPDF(rutaCompleta,contenido);
        System.out.println("PDF creado exitosamente en: " + rutaCompleta);

        //Regresa a la ventana Inicio.

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/VentanaInicio.fxml"));
        // Cargue el archivo FXML
        Parent root = loader.load();
        //Crea un nuevo escenario y prepara el escenario.
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);

        //Muestra la nueva etapa
        stage.show();

        // Hide the stage of the login
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public static void crearPDF(String rutaArchivo, String contenido) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);  // Cambiado a tamaño de página A4
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                //contentStream.setFont(PDType1Font.COURIER, 12);Linea que comente porque no tengo libreria
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                // Dividir el contenido por saltos de línea y agregar cada línea
                String[] lineas = contenido.split("\n");
                for (String linea : lineas) {
                    contentStream.showText(linea);
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            document.save(rutaArchivo);
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

    public void OnRegresar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vista/ventanas/VentanaInicio.fxml"));
        // Cargue el archivo FXML
        Parent root = loader.load();
        //Crea un nuevo escenario y prepara el escenario.
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);

        //Muestra la nueva etapa
        stage.show();

        // Hide the stage of the login
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
