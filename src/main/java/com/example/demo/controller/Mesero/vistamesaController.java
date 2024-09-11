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


    @FXML
    public void inicilite(){
    }

    @FXML
        public void but_pagar() {

        }


        public void mesa5(MouseEvent mouseEvent) {
            navegar(rutaPedido);
    }


        public void mesa1(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa7(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa11(MouseEvent mouseEvent) {
            navegar(rutaPedido);}

        public void mesa16(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa2(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa3(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa4(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa8(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa9(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa10(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa12(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa13(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa14(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa15(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa17(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa18(MouseEvent mouseEvent) {
            navegar(rutaPedido);

        }

        public void mesa19(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        public void mesa20(MouseEvent mouseEvent) {
            navegar(rutaPedido);
        }

        // vistas emejertes

        public void but_LimpiarMesa(ActionEvent actionEvent) {
            navegarNo(rutaPentiende);
        }

        public void but_domicilio(ActionEvent actionEvent) {

            navegarNo(rutaDomicilio);
        }


    //metodo para ir a la vista de pedidos
        public void navegar(String ruta){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
                Parent root = loader.load();

                Stage stage = (Stage) Pmesa5.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void navegarNo(String ruta){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
                Parent root = loader.load();

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

