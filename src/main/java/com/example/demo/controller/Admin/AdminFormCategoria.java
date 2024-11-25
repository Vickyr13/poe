package com.example.demo.controller.Admin;

import com.example.demo.Model.Categorias;
import com.example.demo.database.CategoriaDAO;
import com.example.demo.database.productosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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

    private int idbuttom = 0;


    @FXML
    void REGRESAR(ActionEvent event) {
        CambiarVista("AdminCategorias");
    }

    @FXML
    private void initialize(){
        Txt_NombreP.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-z,A-Z]*")) ? change : null));

        Txt_NombreP.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), null, change -> {
            // Expresión regular que solo permite letras (sin números, caracteres especiales ni espacios)
            if (change.getText().matches("[a-zA-Z]*")) {
                return change; // Permitir el cambio si cumple con la condición
            }
            return null; // Si no cumple, rechazar el cambio
        }));
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

        if(idbuttom == 2){
            Ingresar_Usuario();
        }else {
            guardarCambios();
        }
    }

    private void Ingresar_Usuario(){
        if(validate()){

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
                JOptionPane.showMessageDialog(null, "Categoria agregada correctamente.");
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

    private int id_categoria;  // Variable para almacenar el ID del producto que estamos editando



    public void cargarDatosParaEditarCatego(Map<String, Object> categoria, String id_categoria) {
        Txt_NombreP.setText((String) categoria.get("nombre_categoria"));

        // Establecer el estado activo/inactivo
        String estado = (String) categoria.get("estado_categoria");
        if ("Activo".equals(estado)) {
            rdoActivo.setSelected(true);
        } else {
            rdoInactivo.setSelected(true);
        }

        // Guardar el id_producto
        try {
            this.id_categoria = Integer.parseInt(id_categoria);  // Convertir el ID a entero
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el idProducto a entero: " + e.getMessage());
            this.id_categoria = -1;
        }
    }

    public void guardarCambios() {
        CategoriaDAO querys = new CategoriaDAO();
        if (validate()) {
            String nombre_categoria = Txt_NombreP.getText();

            int estado = rdoActivo.isSelected() ? 1 : 0;

            // Actualizar los datos en la base de datos usando idProducto
            try {
                querys.actualizarCategoria( id_categoria, nombre_categoria, estado );
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}