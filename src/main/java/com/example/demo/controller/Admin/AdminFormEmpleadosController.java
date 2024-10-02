package com.example.demo.controller.Admin;

import com.example.demo.Model.Empleado;
import com.example.demo.database.EmpleadoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminFormEmpleadosController {
    @FXML
    public Button Btn_AgregarEmpleado;
    @FXML
    public DatePicker PICKERContratacion;
    @FXML
    public Button btnregresar;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtTelefono;
    @FXML
    public TextField txtDUI;
    @FXML
    public TextField txtApellido;
    @FXML
    public TextField txtNombre;
    @FXML
    public Pane Btn_Regresar;
    @FXML
    public RadioButton RbtnInactivo;
    @FXML
    public RadioButton RbtnActivo;
    @FXML
    public ToggleGroup Estado;
    @FXML
    private RadioButton RdB_Mesero;
    @FXML
    private RadioButton RdB_Cocinero;
    @FXML
    private RadioButton RdB_Repartidor;

    public void initialize() {

        ToggleGroup group = new ToggleGroup();

        RdB_Mesero.setToggleGroup(group);
        RdB_Cocinero.setToggleGroup(group);
        RdB_Repartidor.setToggleGroup(group);

    }




    public void GuardarEmpleado() {
        //Crear el objeto empleadoDAO
        EmpleadoDAO querys = new EmpleadoDAO();

        //obtener los valores de los campos del formulario y otros controladores
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dui = txtDUI.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        LocalDate contratacion = PICKERContratacion.getValue();
        String cargo = "";
        int estado = 0;
        Date contratacionDate = Date.valueOf(contratacion);


        if (RdB_Mesero.isSelected()) {
            cargo = "Mesero";
        } else if (RdB_Cocinero.isSelected()) {
            cargo = "Cocinero";
        } else if (RdB_Repartidor.isSelected()) {
            cargo = "Repartidor";
        }

        if (RbtnActivo.isSelected()) {
            estado = 1;


        } else if (RbtnInactivo.isSelected()) {
            estado = 0;
        }
        //Agregar el empleado a la base de datos o mostrar un mensaje de error

        Empleado empleado = new Empleado(nombre, apellido, dui, telefono, email, contratacionDate, cargo, estado );

        try {
            querys.insertarEmpleado(empleado);
        }catch(Exception e){
            System.out.println("Error no sea como Javier..."+e.getMessage());
        };

    }

    @FXML
    public void REGRESAR (ActionEvent actionEvent) {
        CambiarVista("AdminUsuarios");
    }
    
    public void CambiarVista(String Direccion){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/" + Direccion + ".fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) btnregresar.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void IngresarEmpleado(ActionEvent actionEvent) {
        GuardarEmpleado();
        System.out.println("Ingresar");
        generateKey();

    }

    public static String generateKey() {
        int[] contraseña = new int[4];
        StringBuilder pin = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            contraseña[i] = random;
            pin.append(random);
        }

        return pin.toString();
    }
}
