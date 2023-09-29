package com.codigo.modelo;

import com.codigo.controlador.VentanaLoginController;
import com.codigo.modelo.objetos.DataBase;
import com.codigo.modelo.objetos.Empleado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaLoginController.class.getResource("/com/vista/Login/ventanaLogin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        DataBase baseDeDatos = new DataBase();
        ArrayList<Empleado> empleados = baseDeDatos.setValoresQuemados();
        launch(args);
    }
}