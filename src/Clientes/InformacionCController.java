package Clientes;
import Clases.Ciudad;
import Clases.Cliente;
import Clases.Estado;
import Database.DAO.ClienteDAO;
import Database.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InformacionCController implements Initializable {
    @FXML Button btnGuardar, btnCancelar;
    @FXML TextField txtRFC, txtNombre, txtDireccion, txtTelefono, txtCiudad, txtEstado;
    @FXML ComboBox<Ciudad> cbCiudad;
    @FXML ComboBox<Estado> cbEstado;
    @FXML MenuItem menuItemDatos;
    private int parameter = 0;
    public static final byte Modo_Nuevo = 1;
    public static final byte Modo_Editar = 2;
    private byte mode = Modo_Nuevo;
    private Cliente cliente;

    public InformacionCController() {
        System.out.println("#..." + parameter);
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode(){
        return this.mode;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancelar.setOnAction(handlerButtons);
        btnGuardar.setOnAction(handlerButtons);
        menuItemDatos.setOnAction(handlerButtons);
        if (getMode() == Modo_Editar) {
            loadData();
        }
    }

    private void loadData() {
        txtRFC.setText(cliente.getRFC());
        txtNombre.setText(cliente.getNombre());
        txtDireccion.setText(cliente.getDireccion());
        txtTelefono.setText(cliente.getTelefono());
        txtCiudad.setText(cliente.getCveCiudad());
        txtEstado.setText(cliente.getCveEstado());
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
            try {
                if (event.getSource() == btnCancelar) {
                    hideStage();
                } else if (event.getSource() == btnGuardar) {
                    ClienteDAO clienteDAO = new ClienteDAO(MySQL.getConnection());
                    if (getMode() == Modo_Nuevo) {
                        clienteDAO.insert(new Cliente(
                                txtRFC.getText(),
                                txtNombre.getText(),
                                txtDireccion.getText(),
                                txtTelefono.getText(),
                                txtCiudad.getText(),
                                txtEstado.getText()
                        ));
                    } else if (getMode() == Modo_Editar) {
                        clienteDAO.update(new Cliente(
                                txtRFC.getText(),
                                txtNombre.getText(),
                                txtDireccion.getText(),
                                txtTelefono.getText(),
                                txtCiudad.getText(),
                                txtEstado.getText()
                        ));

                    }
                    showMessage("Cliente Guardado", "Guardar");
                    hideStage();
                } else if (event.getSource() == menuItemDatos) {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Clientes/Datos.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Datos de Clientes");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
            }catch (IOException e){
                e.printStackTrace();
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
