    package com.example.demo.controller;

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


    private  String rutaPedido = "/com/example/demo/views/vista-pedido.fxml";
    private String rutaDomicilio= "/com/example/demo/views/ventanaDomicilio.fxml";
    private String rutaPentiende= "/com/example/demo/views/ventanaPedido.fxml";


    @FXML
        public void but_pagar() {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/vista-pedido.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) pagar.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

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

        public void but_LimpiarMesa(ActionEvent actionEvent) {
            navegar(rutaPentiende);
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

        public void but_domicilio(ActionEvent actionEvent) {

            navegar(rutaDomicilio);
        }


    }

