package Clientes;

import Clases.Datos;
import Database.DAO.DatosDAO;
import Database.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BancariosController implements Initializable{
    @FXML TextField txtClave,txtRFC, txtCheque,txtReferencia, txtTitular, txtCodigo, txtTarjeta, txtCuenta, txtBanco;
    @FXML Button btnGuardar, btnCancelar;
    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Datos datos;

    public BancariosController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode(){
        return this.mode;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
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
        txtClave.setText(datos.getCveDatos() + "");
        txtRFC.setText(datos.getRFC());
        txtCheque.setText(datos.getNumCheque() + "");
        txtReferencia.setText(datos.getReferencia());
        txtTitular.setText(datos.getTitularTarjeta());
        txtCodigo.setText(datos.getCodigoSeg() + "");
        txtTarjeta.setText(datos.getNumTarjeta() + "");
        txtCuenta.setText(datos.getNumeroCuenta() + "");
        txtBanco.setText(datos.getBanco());
    }

    private void message(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parameter");
        alert.setContentText(parameter + "");
        alert.showAndWait();
    }

    public void setParameter(int parameter){
        this.parameter = parameter;
    }

    EventHandler<ActionEvent> handlerButtons = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnCancelar) {
                hideStage();
            } else if (event.getSource() == btnGuardar) {
                DatosDAO datosDAO = new DatosDAO(MySQL.getConnection());
                if (getMode() == Modo_Nuevo) {
                    datosDAO.insert(new Datos(
                            Integer.valueOf(txtClave.getText()),
                            txtRFC.getText(),
                            Integer.valueOf(txtCheque.getText()),
                            txtReferencia.getText(),
                            txtTitular.getText(),
                            Integer.valueOf(txtCodigo.getText()),
                            Integer.valueOf(txtTarjeta.getText()),
                            Integer.valueOf(txtCuenta.getText()),
                            txtBanco.getText()
                    ));
                } else if (getMode() == Modo_Editar) {
                    datosDAO.update(new Datos(
                            Integer.valueOf(txtClave.getText()),
                            txtRFC.getText(),
                            Integer.valueOf(txtCheque.getText()),
                            txtReferencia.getText(),
                            txtTitular.getText(),
                            Integer.valueOf(txtCodigo.getText()),
                            Integer.valueOf(txtTarjeta.getText()),
                            Integer.valueOf(txtCuenta.getText()),
                            txtBanco.getText()
                    ));
                }
                showMessage("Datos Guardados", "Guardar");
                hideStage();
            }
        }
    };

    private void showMessage(String msg, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void closeStage(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void hideStage(){
        ((Node)btnCancelar).getScene().getWindow().hide();
    }
}
