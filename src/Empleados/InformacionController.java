package Empleados;

import Clases.Empleado;
import Database.DAO.EmpleadoDAO;
import Database.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InformacionController implements Initializable {
    @FXML TextField txtClave, txtNombre, txtCURP, txtDireccion, txtTelefono, txtPuesto, txtAgencia, txtCiudad, txtEstado;
    @FXML Button btnGuardar, btnCancelar;
    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Empleado empleado;

    public InformacionController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode() {
        return this.mode;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancelar.setOnAction(handlerButtons);
        btnGuardar.setOnAction(handlerButtons);
        if (getMode() == Modo_Editar) {
            loadData();
        }
    }

    private void loadData() {
        txtClave.setText(empleado.getCveEmpleado());
        txtNombre.setText(empleado.getNombre());
        txtCURP.setText(empleado.getCURP());
        txtDireccion.setText(empleado.getDireccion());
        txtTelefono.setText(empleado.getTelefono());
        txtPuesto.setText(empleado.getCvePuesto() + "");
        txtAgencia.setText(empleado.getCveAgencia() + "");
        txtCiudad.setText(empleado.getCveCiudad());
        txtEstado.setText(empleado.getCveEstado());
    }

    private void message() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parameter");
        alert.setContentText(parameter + "");
        alert.showAndWait();
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    EventHandler<ActionEvent> handlerButtons = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnCancelar) {
                hideStage();
            } else if (event.getSource() == btnGuardar) {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO(MySQL.getConnection());
                if (getMode() == Modo_Nuevo) {
                    empleadoDAO.insert(new Empleado(
                            txtClave.getText(),
                            txtNombre.getText(),
                            txtCURP.getText(),
                            txtDireccion.getText(),
                            txtTelefono.getText(),
                            Integer.valueOf(txtPuesto.getText()),
                            Integer.valueOf(txtAgencia.getText()),
                            txtCiudad.getText(),
                            txtEstado.getText()
                    ));
                } else if (getMode() == Modo_Editar) {
                    txtEstado.setVisible(false);
                    empleadoDAO.update(new Empleado(
                            txtClave.getText(),
                            txtNombre.getText(),
                            txtCURP.getText(),
                            txtDireccion.getText(),
                            txtTelefono.getText(),
                            Integer.valueOf(txtPuesto.getText()),
                            Integer.valueOf(txtAgencia.getText()),
                            txtCiudad.getText(),
                            txtEstado.getText()
                    ));
                }
                showMessage("Empleado Guardado", "Guardar");
                hideStage();
            }
        }
    };

    private void showMessage(String msg, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void closeStage() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void hideStage() {
        ((Node) btnCancelar).getScene().getWindow().hide();
    }

}
