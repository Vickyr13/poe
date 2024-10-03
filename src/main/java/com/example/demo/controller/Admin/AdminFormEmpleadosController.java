package com.example.demo.controller.Admin;

import com.example.demo.Model.Direccion;
import com.example.demo.Model.Empleado;
import com.example.demo.Model.Telefono;
import com.example.demo.database.DireccionDAO;
import com.example.demo.database.EmpleadoDAO;
import com.example.demo.database.TelefonoDAO;
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
    public TextField txtDireccion;
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
        TelefonoDAO queryTel = new TelefonoDAO();
        DireccionDAO queryDire = new DireccionDAO();

        //obtener los valores de los campos del formulario y otros controladores
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dui = txtDUI.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String direccion_ingresada = txtDireccion.getText();
        LocalDate contratacion = PICKERContratacion.getValue();
        int rol = 1;
        int estado = 0;
        Date contratacionDate = Date.valueOf(contratacion);


        if (RdB_Mesero.isSelected()) {
            rol = 1;
        } else if (RdB_Cocinero.isSelected()) {
            rol = 2;
        } else if (RdB_Repartidor.isSelected()) {
            rol = 4;
        }

        if (RbtnActivo.isSelected()) {
            estado = 1;


        } else if (RbtnInactivo.isSelected()) {
            estado = 0;
        }


        //Agregar el empleado a la base de datos o mostrar un mensaje de error
        Telefono telefono1 = new Telefono("123456789", telefono);
        Direccion direccion1 = new Direccion(direccion_ingresada);

        Empleado empleado = new Empleado(nombre, apellido, dui, email, 1, 1, contratacionDate, rol, estado, generateKey() );

        try {
            queryDire.insertDireccion(direccion1);
            queryTel.insertTelefono(telefono1);
            querys.insertarEmpleado(empleado);
        }catch(Exception e){
            System.out.println(direccion1.getId_direccion());
            System.out.println(telefono1.getId_telefono());
            System.out.println("Error al insertar empleados "+e.getMessage());
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
