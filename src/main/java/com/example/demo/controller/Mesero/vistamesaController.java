package com.example.demo.controller.Mesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;



public class vistamesaController {
    @FXML
    private Pane Pmesa5;

    @FXML
    private Button pagar;

    @FXML
    private  Pane panel;


    private  String rutaPedido = "/com/example/demo/views/Mesero/vista-pedido.fxml";
    private String rutaDomicilio= "/com/example/demo/views/Mesero/ventanaDomicilio.fxml";
    private String rutaPentiende= "/com/example/demo/views/Mesero/ventanaPedido.fxml";

    private int numeeroMesa = 0;

    @FXML
    public void inicilite(int numeeroMesa){
    }

    @FXML
    public void but_pagar() {

    }


    public void mesa5(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            numeeroMesa = 5;
            navegar(rutaPedido);
        }
    }

    public void mesa1(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 1;
            navegar(rutaPedido);
        }else{
            System.out.println("Prueva");
        }
    }

    public void mesa7(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 7;
            navegar(rutaPedido);
        }
    }

    public void mesa11(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 11;
            navegar(rutaPedido);
        }
    }

    public void mesa16(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 16;
            navegar(rutaPedido);
        }
    }

    public void mesa2(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 2;
            navegar(rutaPedido);
        }
    }

    public void mesa3(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 3;
            navegar(rutaPedido);
        }
    }

    public void mesa4(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 4;
            navegar(rutaPedido);
        }
    }

    public void mesa8(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa =8;
            navegar(rutaPedido);
        }
    }

    public void mesa9(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa =9;
            navegar(rutaPedido);
        }
    }

    public void mesa10(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa =10;
            navegar(rutaPedido);
        }
    }

    public void mesa12(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 12;
            navegar(rutaPedido);
        }
    }
    public void mesa13(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 13;
            navegar(rutaPedido);
        }
    }

    public void mesa14(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 14;
            navegar(rutaPedido);
        }
    }
    public void mesa15(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 15;
            navegar(rutaPedido);
        }
    }

    public void mesa17(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 17;
            navegar(rutaPedido);
        }
    }

    public void mesa18(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 18;
            navegar(rutaPedido);
        }
    }

    public void mesa19(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 19;
            navegar(rutaPedido);
        }
    }

    public void mesa20(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2) {
            numeeroMesa = 20;
            navegar(rutaPedido);
        }
    }

    // vistas emejertes

    public void but_LimpiarMesa(ActionEvent actionEvent) {
        navegarNo(rutaPentiende);
    }

    public void but_domicilio(ActionEvent actionEvent) {

        navegarNo(rutaDomicilio);
    }


    public void navegar(String ruta){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista
            vistapedidoController pedidoController = loader.getController();

            // Pasar el número de mesa al método initialize
            pedidoController.initialize(String.valueOf(numeeroMesa));

            // Cambiar la escena a la nueva vista
            Stage stage = (Stage) Pmesa5.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void navegarNo(String ruta){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();


            vistamesaController mesa = loader.getController();
            mesa.inicilite(numeeroMesa);


            // Crear un nuevo Stage para la ventana emergente
            Stage newStage = new Stage();
            Scene scene = new Scene(root);

            newStage.setScene(scene);

            // Mostrar la nueva ventana encima de la actual
            newStage.initOwner(Pmesa5.getScene().getWindow()); // Establece el propietario como la ventana actual
            newStage.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana sea modal (bloquea la interacción con la ventana principal)
            newStage.showAndWait(); // Espera a que la ventana se cierre antes de volver al control de la ventana principal

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

