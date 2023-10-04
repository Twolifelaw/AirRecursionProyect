package Cliente.controlador;

import Cliente.exceptions.verificarException;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VentanaLoginController implements Initializable {
    String nombre;
    String contrasena;

    @FXML
    private ImageView AirRecursion;
    @FXML
    private Button btnContraseña;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Label lblAction;

    @FXML
    private PasswordField pswContrasena;

    @FXML
    private TextField txtUsuario;

    @FXML
    private AnchorPane ventanaLogin;
    @FXML
    void ingresar(ActionEvent event) {
        try{
            nombre = txtUsuario.getText();
            contrasena = pswContrasena.getText();
            if(nombre.isEmpty() && contrasena.isEmpty()){
                throw new verificarException("Llene los campos");
            }else if(nombre.isEmpty() || contrasena.isEmpty()){
                throw new verificarException("Campo vacio llenar porfavor");
            }else{
                boolean usuarioEncontrado = false;
                try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        // Verificar si la línea contiene el nombre y la contraseña
                        if (linea.startsWith("Nombre Usuario: ") && linea.contains(txtUsuario.getText())) {
                            // Leer la siguiente línea para verificar la contraseña
                            linea = reader.readLine();
                            if (linea != null && linea.startsWith("Contaseña Usuario: ") && linea.contains(pswContrasena.getText())) {
                                System.out.println("Las credenciales son correctas.");
                                lblAction.setText("Se ingreso correctamente.");
                            mostrarLoginErrorTemporalmente();
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaPrincipal.fxml")));
                            Scene escena = new Scene(root);
                            stage.setScene(escena);
                            stage.show();
                            // en esta linea , esconde el stage del login y carga el nuevo stage
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                            usuarioEncontrado = true;}
                        }else{
                            lblAction.setText("No se encontro el usuario");
                            mostrarLoginErrorTemporalmente();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



                if(!usuarioEncontrado){
                    throw new verificarException("No se encontro usuario");
                }

            }

        } catch (verificarException e) {
            lblAction.setText(e.getMessage());
            mostrarLoginErrorTemporalmente();
        }

    }

    @FXML
    void action_registrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaRegistro.fxml")));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
        // en esta linea , esconde el stage del login y carga el nuecvo stage
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void action_recuperar(ActionEvent event) {

    }

    private void mostrarLoginErrorTemporalmente() {
        lblAction.setVisible(true);

        // Configurar un Timeline para ocultar el mensaje después de 2 segundos (por ejemplo).
        Duration delay = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblAction.setVisible(false);
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(AirRecursion);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(200);
        translate.setAutoReverse(true);
        translate.play();
    }
}
