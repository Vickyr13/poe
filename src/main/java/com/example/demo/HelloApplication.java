package com.example.demo;

import com.example.demo.controller.database.conneection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo/views/admin/AdminDashboard.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // Crear la escena sin especificar tamaño
        stage.setTitle("Hello!");

        // Quitar la decoración de la ventana
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.sizeToScene(); // Ajusta el tamaño de la ventana para adaptarse a la escena
        stage.show();

        try {
            if(conneection.getConnection() != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Malio sal");
        }


    }

    public static void main(String[] args) {
        launch();
    }
}