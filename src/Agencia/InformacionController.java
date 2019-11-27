package Agencia;

import Clases.Agencia;
import Clases.Distribuidor;
import Clases.Estado;
import Database.DAO.AgenciaDAO;
import Database.DAO.DistribuidorDAO;
import Database.DAO.EstadoDAO;
import Database.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

public class InformacionController implements Initializable{
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    TextField txtID, txtDireccion, txtTelefono, txtEstado;

    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Agencia agencia;
    private Distribuidor distribuidor;
    public InformacionController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode() {
        return this.mode;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
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
        txtID.setText(agencia.getCveAgencia() + "");
        txtDireccion.setText(agencia.getDireccion());
        txtTelefono.setText(agencia.getTelefono());
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
                AgenciaDAO agenciaDAO = new AgenciaDAO(MySQL.getConnection());
                DistribuidorDAO distribuidorDAO = new DistribuidorDAO(MySQL.getConnection());
                if (getMode() == Modo_Nuevo) {
                    distribuidorDAO.insert(new Distribuidor(
                            Integer.valueOf(txtID.getText()),
                            txtEstado.getText()
                    ));
                    agenciaDAO.insert(new Agencia(
                            Integer.valueOf(txtID.getText()),
                            txtDireccion.getText(),
                            txtTelefono.getText()
                    ));
                } else if (getMode() == Modo_Editar) {
                    txtEstado.setVisible(false);
                    agenciaDAO.update(new Agencia(
                            Integer.valueOf(txtID.getText()),
                            txtDireccion.getText(),
                            txtTelefono.getText()
                    ));
                }
                showMessage("Agencia Guardada", "Guardar");
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
