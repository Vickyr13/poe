package com.example.demo.controller.Admin;

import com.example.demo.Model.Categorias;
import com.example.demo.database.CategoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AdminFormCategoria {

    @FXML
    private Button Btn_Agregar_Plato;

    @FXML
    private ToggleGroup RdbEstado;

    @FXML
    private TextField Txt_NombreP;

    @FXML
    private Button btnVolver;

    @FXML
    private RadioButton rdoActivo;

    @FXML
    private RadioButton rdoInactivo;

    @FXML
    void REGRESAR(ActionEvent event) {
    CambiarVista("AdminCategorias");
    }

    CategoriaDAO querys = new CategoriaDAO();
    public void CambiarVista(String Direccion){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Direccion+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Btn_Agregar_Plato(ActionEvent actionEvent) {

        if(validate()){
            JOptionPane.showMessageDialog(null, "si se pudo");

            String nombre_categoria = Txt_NombreP.getText();
            int estado_categoria = 1;
            if (rdoInactivo.isSelected()) {
                estado_categoria = 0;
            } else if (rdoActivo.isSelected()) {
                estado_categoria = 1;
            }

            Categorias categorias1 = new Categorias(nombre_categoria, estado_categoria);
            try {
                querys.insertarCategoria(categorias1);
            }catch(Exception e){
                System.out.println("Error en el Formulario Categoria"+e.getMessage());
            };

        }

    }


    private boolean validate(){
        if(Txt_NombreP.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese nombe de la categoria");
            return false;
        }

        if(!rdoActivo.isSelected() && !rdoInactivo.isSelected()){
            JOptionPane.showMessageDialog(null, "Seleccione estado de la categoria");
            return false;
        }
        return true;
    }

}
