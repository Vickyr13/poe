package com.example.demo.controller.Admin;

import com.example.demo.Model.Empleado;
import com.example.demo.database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import java.util.List;
import java.util.Map;

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

    public void initialize() throws SQLException {


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

    public void GuardarEmpleado() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        if (validarCampos()) {
            // Crear los DAO para la inserción en la base de datos
            EmpleadoDAO querys = new EmpleadoDAO();

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
            String pinEncriptado =Encriptar(pin);
            Empleado empleado = new Empleado(nombre, apellido, dui, email, direccion_ingresada, telefono, contratacionDate, rol, estado, pinEncriptado);

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
    public void IngresarEmpleado(ActionEvent actionEvent) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, SQLException {

        // GuardarEmpleado();


        EmpleadoDAO querys = new EmpleadoDAO();
        AdminUsuariosController ProductoController = new AdminUsuariosController();

        if (AdminUsuariosController.getId_button() == 1) {
            guardarCambios();
            return;
        }

        if (AdminUsuariosController.getId_button() == 2){
            if (validarCampos()) {
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

                //Obtener el id de la categoría seleccionada
                String pin = generateKey();
                String pinEncriptado =Encriptar(pin);
                Empleado productos = new Empleado
                        (nombre, apellido, dui, email, direccion_ingresada, telefono, contratacionDate, rol, estado, pinEncriptado);

                try {

                    querys.insertarEmpleado(productos);
                } catch (Exception e) {
                    System.out.println("Error al insertar empleado " + e.getMessage());
                }

            }
            return;
        }
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


    public static String Encriptar(String pin) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        String encryptedString = null;

        try {
            // 1. Generar una clave secreta AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // Puedes usar 128, 192 o 256 bits, aquí usamos 128 para simplicidad
            SecretKey secretKey = keyGen.generateKey(); // Aquí obtenemos la clave secreta

            // 2. Crear un objeto Cipher para manejar el cifrado
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Configuramos el modo cifrado

            // 3. Cifrar el string
            byte[] encryptedData = cipher.doFinal(pin.getBytes()); // Convierte el string a bytes y lo cifra

            // 4. Convertir los datos cifrados a Base64 para poder imprimirlos
            encryptedString = Base64.getEncoder().encodeToString(encryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedString;
    }




    public List<String> obtenerRol() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT id_rol, nombre_rol FROM rol";
        try (Connection con = conneection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("nombre_rol"));
                //   categorias.add(rs.getString("id_categoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }


    private int obtenerIdRol(String rol) {
        // Conexión a la base de datos
        Connection conn = conneection.getConnection();
        // Consulta SQL que valida la categoría
        String sql = "SELECT id_rol FROM rol WHERE nombre_rol = ?";
        int id_rol = -1;
        try {
            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rol);

            // Ejecutar la consulta
            ResultSet resultSet = stmt.executeQuery();

            // Verificar si se encontraron resultados
            if (resultSet.next()) {
                // Extraer el valor de id_categoria
                id_rol = resultSet.getInt("id_rol");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar el rol: " + e.getMessage(), e);
        }
        finally {
            // Cerrar la conexión, el PreparedStatement y el ResultSet
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Devolver el id de la categoría o -1 si no se encontró
        return id_rol;
    }
    private int id_Empleado;
    private int idrol;  // Variable para almacenar el ID del producto que estamos editando
    int idRol= 0;
    public void cargarDatosParaEditar(Map<String, Object> rol, String idrol) {
        txtNombre.setText((String) rol.get("nombre_empleado"));
        txtApellido.setText(String.valueOf(rol.get("apellido_empleado")));
        txtDUI.setText((String) rol.get("dui"));
        txtTelefono.setText((String) rol.get("telefono"));
        txtEmail.setText(String.valueOf(rol.get("email")));
        txtDireccion.setText((String) rol.get("direccion"));
        PICKERContratacion.setValue(((Date) rol.get("fecha_contratacion")).toLocalDate());


        // Establecer el estado activo/inactivo
        String estado = (String) rol.get("estado_empleado");
        if ("Activo".equals(estado)) {
            RbtnActivo.setSelected(true);
        } else {
            RbtnInactivo.setSelected(true);
        }

        // Establecer el rol activo/inactivo
        String rol1 = (String) rol.get("id_rol");
        if ("1".equals(rol1)) {
            RdB_Mesero.setSelected(true);
        } else if ("2".equals(rol1)){
            RdB_Cocinero.setSelected(true);
        }else{
            RdB_Repartidor.setSelected(true);
        }

        // Asignar el valor del idRol en función del valor de idrol
        if(idrol.equals("Mesero")) {
            idRol = 1;
        } else if(idrol.equals("Cocinero")) {
            idRol = 3;
        } else {
            idRol = 4; // Valor por defecto si no es Mesero o Cocinero
        }

// Ya no necesitas convertir porque idRol ya es un entero
        try {
            this.id_Empleado = id_Empleado; // Asignar directamente ya que idRol es un entero
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el idrol a entero: " + e.getMessage());
            this.id_Empleado = -1; // Valor por defecto en caso de error (aunque no debería ocurrir)
        }
    }


    public void guardarCambios() throws SQLException {
        EmpleadoDAO querys = new EmpleadoDAO();
        if (validarCampos()) {
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


            estado = RbtnActivo.isSelected() ? 1 : 0;

            Empleado empleado = new Empleado();
            int id = empleado.getId_Empleado();
            // Actualizar los datos en la base de datos usando idProducto
            try {
                querys.actualizarEmpleado(nombre, apellido, dui, email, direccion_ingresada, telefono, contratacionDate, rol, estado);
                JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}