package com.example.demo.controller.Mesero;

import com.example.demo.database.OrdenesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class PagoController {

    @FXML
    private Button bt_pagar;

    @FXML
    private Label lb_total;

    @FXML
    private TextField txt_efectivo;
    private Integer idOrden;

    public void recibirIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
        // Aquí puedes usar el idOrden para cargar datos específicos de la orden
        System.out.println("ID de la orden recibida: " + idOrden);
    }
    OrdenesDAO orden = new OrdenesDAO();
    @FXML
    void bt_pagar(ActionEvent event) {
        try {
            double total = Double.parseDouble(lb_total.getText());
            double efectivo = Double.parseDouble(txt_efectivo.getText());

            if(efectivo >= total){
                double cambio = efectivo - total;
                JOptionPane.showMessageDialog(null, "Cambio: $"+cambio);
                System.out.println("el id recibido de la orden es" + idOrden);

                OrdenesDAO queryOrden = new OrdenesDAO();
                queryOrden.inactivarOrden(idOrden);
                orden.finalizarOrden(nmesa);

                Stage stage = (Stage) bt_pagar.getScene().getWindow();
                stage.close();
            }else{
                JOptionPane.showMessageDialog(null, "Error al inactivar cuenta");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
    }

    @FXML
    void txt_efectivo(ActionEvent event) {

    }

    public void recibirTotal(String total) {
        lb_total.setText(total); // Muestra el mensaje en el Label
    }

    int nmesa;
    public void recibirMesa(int mesa) {
        nmesa = mesa;// Muestra el mensaje en el Label
    }


    int id;
    public void recibirId(int idRecibido) {
        id = idRecibido;// Muestra el mensaje en el Label



        }

}
