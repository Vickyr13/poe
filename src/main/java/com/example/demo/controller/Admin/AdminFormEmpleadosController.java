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

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
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

        // Validación para que los campos de nombre solo acepten letras
        txtNombre.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));

        // Validación para que los campos de apellido solo acepten letras
        txtApellido.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));

        // Validación para el campo de DUI en formato #########-#
        txtDUI.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,8}-?\\d?")) {
                return change;
            } else {
                return null;
            }
        }));

        // Validación para el campo de teléfono (8 números)
        txtTelefono.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,8}")) {
                return change;
            } else {
                return null;
            }
        }));

        // Validación para el campo de email que contenga "@"
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("@gmail.com")) {
                txtEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                txtEmail.setStyle("");
            }
        });

        // Agrupar los botones de rol
        ToggleGroup groupRol = new ToggleGroup();
        RdB_Mesero.setToggleGroup(groupRol);
        RdB_Cocinero.setToggleGroup(groupRol);
        RdB_Repartidor.setToggleGroup(groupRol);

        // Agrupar los botones de estado (Activo/Inactivo)
        ToggleGroup groupEstado = new ToggleGroup();
        RbtnActivo.setToggleGroup(groupEstado);
        RbtnInactivo.setToggleGroup(groupEstado);
    }

    public void GuardarEmpleado() {

        if (validarCampos()) {
            // Crear los DAO para la inserción en la base de datos
            EmpleadoDAO querys = new EmpleadoDAO();
            TelefonoDAO queryTel = new TelefonoDAO();
            DireccionDAO queryDire = new DireccionDAO();

            // Obtener los valores de los campos
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

            // Determinar el rol seleccionado
            if (RdB_Mesero.isSelected()) {
                rol = 1;
            } else if (RdB_Cocinero.isSelected()) {
                rol = 2;
            } else if (RdB_Repartidor.isSelected()) {
                rol = 4;
            }

            // Determinar el estado seleccionado
            if (RbtnActivo.isSelected()) {
                estado = 1;
            } else if (RbtnInactivo.isSelected()) {
                estado = 0;
            }


            String pin = generateKey();
            Empleado empleado = new Empleado(nombre, apellido, dui, email, direccion_ingresada, telefono, contratacionDate, rol, estado, pin);

            // Intentar insertar los datos en la base de datos
            try {

                querys.insertarEmpleado(empleado);
                JOptionPane.showMessageDialog(null, "Empleado agregado correctamente");
                JOptionPane.showMessageDialog(null, "La contraseña de empleados es: " + pin);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al insertar empleados " + e.getMessage());
            }
        }
    }

    @FXML
    public void REGRESAR(ActionEvent actionEvent) {
        CambiarVista("AdminUsuarios");
    }

    public void CambiarVista(String Direccion) {
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

    // Boton para ingresar el empleados
    public void IngresarEmpleado(ActionEvent actionEvent) {
        GuardarEmpleado();
    }

    // Generación de una clave de 4 dígitos
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

    // Validar que todos los campos estén correctamente completados
    public boolean validarCampos() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dui = txtDUI.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        LocalDate contratacion = PICKERContratacion.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || dui.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de agregar.");
            return false;
        } else if (!dui.matches("\\d{8}-\\d")) {
            JOptionPane.showMessageDialog(null, "El DUI debe tener el formato ########-#.");
            return false;
        } else if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener 8 dígitos.");
            return false;
        } else if (!email.contains("@gmail.com")) {
            JOptionPane.showMessageDialog(null, "Inserte un email valido.");
            return false;
        }

        // Validar que la fecha de contratación sea válida
        if (contratacion == null || contratacion.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de contratación válida.");
            return false;
        }
        return true;
    }


    public static String generateKey2() {
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