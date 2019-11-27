package Vehiculos;

import Clases.Vehiculo;
import Database.DAO.AgenciaDAO;
import Database.DAO.DistribuidorDAO;
import Database.DAO.VehiculoDAO;
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

public class InformacionAController implements Initializable {
    @FXML TextField txtMatricula, txtTransmision, txtAño, txtDisponible, txtGPS, txtCosto, txtArre, txtPoliza, txtModelo, txtMarca, txtTipo;
    @FXML Button btnGuardar, btnCancelar;

    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Vehiculo vehiculo;
    public InformacionAController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode() {
        return this.mode;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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
        txtMatricula.setText(vehiculo.getMatricula());
        txtTransmision.setText(vehiculo.getTransmision());
        txtAño.setText(vehiculo.getAño() + "");
        txtDisponible.setText(vehiculo.getDisponible());
        txtGPS.setText(vehiculo.getGPS());
        txtCosto.setText(vehiculo.getCostoDia() + "");
        txtArre.setText(vehiculo.getPrecioArre() + "");
        txtPoliza.setText(vehiculo.getNumeroPoliza());
        txtModelo.setText(vehiculo.getCveModelo());
        txtMarca.setText(vehiculo.getCveMarca());
        txtTipo.setText(vehiculo.getCveTipo() + "");
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
                VehiculoDAO vehiculoDAO = new VehiculoDAO(MySQL.getConnection());
                if (getMode() == Modo_Nuevo) {
                    vehiculoDAO.insert(new Vehiculo(
                            txtMatricula.getText(),
                            txtTransmision.getText(),
                            Integer.valueOf(txtAño.getText()),
                            txtDisponible.getText(),
                            txtGPS.getText(),
                            Double.valueOf(txtCosto.getText()),
                            Double.valueOf(txtArre.getText()),
                            txtPoliza.getText(),
                            txtModelo.getText(),
                            txtMarca.getText(),
                            Integer.valueOf(txtTipo.getText())
                    ));
                } else if (getMode() == Modo_Editar) {
                    txtMarca.setEditable(false);
                    txtModelo.setEditable(false);
                    txtTipo.setEditable(false);
                    vehiculoDAO.update(new Vehiculo(
                            txtMatricula.getText(),
                            txtTransmision.getText(),
                            Integer.valueOf(txtAño.getText()),
                            txtDisponible.getText(),
                            txtGPS.getText(),
                            Double.valueOf(txtCosto.getText()),
                            Double.valueOf(txtArre.getText()),
                            txtPoliza.getText(),
                            txtModelo.getText(),
                            txtMarca.getText(),
                            Integer.valueOf(txtTipo.getText())
                    ));
                }
                showMessage("Vehiculo Guardado", "Guardar");
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
