package com.example.demo;

import com.example.demo.controller.Admin.AdminCategoriasController;
import com.example.demo.database.conneection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo/views/Mesero/login-mesero.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // Crear la escena sin especificar tamaño
        stage.setTitle("Hello!");

        // Quitar la decoración de la ventana
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.sizeToScene(); // Ajusta el tamaño de la ventana para adaptarse a la escena

        stage.show();

        if(conneection.getConnection() != null) {
            System.out.println("Conexión exitosa");


        } else {
            System.out.println("Conexión fallida");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}