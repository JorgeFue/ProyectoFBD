package Seguro;

import Clases.Seguro;
import Database.DAO.SeguroDAO;
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
    @FXML
    TextField txtPoliza, txtDeducible, txtAseg;
    @FXML
    Button btnGuardar, btnCancelar;

    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Seguro seguro;
    public InformacionController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode() {
        return this.mode;
    }

    public void setSeguro(Seguro vehiculo) {
        this.seguro = vehiculo;
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
        txtPoliza.setText(seguro.getNumeroPoliza());
        txtDeducible.setText(seguro.getDeducible() + "");
        txtAseg.setText(seguro.getAseguradora());
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
                SeguroDAO seguroDAO = new SeguroDAO(MySQL.getConnection());
                if (getMode() == Modo_Nuevo) {
                    seguroDAO.insert(new Seguro(
                            txtPoliza.getText(),
                            Double.valueOf(txtDeducible.getText()),
                            txtAseg.getText()));
                } else if (getMode() == Modo_Editar) {
                    seguroDAO.update(new Seguro(
                            txtPoliza.getText(),
                            Double.valueOf(txtDeducible.getText()),
                            txtAseg.getText()));
                }
                showMessage("Poliza Guardado", "Guardar");
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
