package Cliente.controlador;

import Cliente.exceptions.verificarException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class VentanaLoginController {
    String nombre;
    String contrasena;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label lblError;

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
                        // Verificar si la línea contiene el correo electrónico y la contraseña
                        if (linea.contains("Nombre Usuario: " + txtUsuario.getText()) &&
                                reader.readLine().contains("Contraseña: " + pswContrasena.getText())) {
                            System.out.println("Las credenciales son correctas.");
                            lblError.setText("Se ingreso correctamente.");
                            mostrarLoginErrorTemporalmente();
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/vista/ventanas/ventanaPrincipal.fxml")));
                            Scene escena = new Scene(root);
                            stage.setScene(escena);
                            stage.show();
                            // en esta linea , esconde el stage del login y carga el nuevo stage
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                            usuarioEncontrado = true;
                        }else{
                            lblError.setText("No se encontro el usuario");
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
            lblError.setText(e.getMessage());
            mostrarLoginErrorTemporalmente();
        }

    }


    private void mostrarLoginErrorTemporalmente() {
        lblError.setVisible(true);

        // Configurar un Timeline para ocultar el mensaje después de 2 segundos (por ejemplo).
        Duration delay = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblError.setVisible(false);
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }


}
