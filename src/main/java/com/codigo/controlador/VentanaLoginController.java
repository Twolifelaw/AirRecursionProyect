package com.codigo.controlador;

import com.codigo.exceptions.verificarException;
import com.codigo.modelo.objetos.DataBase;
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
                for (int i = 0; i < DataBase.empleados.size(); i++) {
                    if (DataBase.empleados.get(i).getNombre().equals(nombre) && DataBase.empleados.get(i).getContrasena().equals(contrasena)) {
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
                        break;
                    }else{
                        lblError.setText("No se encontro el usuario");
                        mostrarLoginErrorTemporalmente();
                    }

                }
                if(!usuarioEncontrado){
                    throw new verificarException("No se encontro usuario");
                }

            }

        } catch (verificarException e) {
            lblError.setText(e.getMessage());
            mostrarLoginErrorTemporalmente();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
