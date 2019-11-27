package MainWindow;

import Clases.*;
import Database.DAO.*;
import Database.MySQL;
import LogIn.LogInController;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    @FXML TextField txtID, txtCveAgenciaREV, txtCveAgenciaDEV, txtCveDatos, txtCosto, txtHoraREN, txtHoraDEV, txtMatricula, txtModelo, txtRFC;
    @FXML Button btnRegistro, btnLogOut;
    @FXML DatePicker dpFechaREV, dpFechaDEV;
    @FXML ComboBox<String> cbPago,cbMarca, cbOferta;
    @FXML Label lblUsuario;
    @FXML MenuItem menuItemAgencia,menuItemClientes, menuItemCarros, menuItemEmpleados, menuItemSeguro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMarca();
        loadPago();
        loadOfertas();
        lblUsuario.setText("User: " + LogInController.usuario);
        btnLogOut.setOnAction(event);
        btnRegistro.setOnAction(event);
        menuItemAgencia.setOnAction(event);
        menuItemClientes.setOnAction(event);
        menuItemCarros.setOnAction(event);
        menuItemEmpleados.setOnAction(event);
        menuItemSeguro.setOnAction(event);
    }

    private void showMessage(String msg, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try{
                if (event.getSource()==btnLogOut){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Log Out");
                    alert.setHeaderText("Look, A Confirmation Dialog");
                    alert.setContentText("Are You Sure To Log Out?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/LogIn/LogIn.fxml"));
                        Scene scene = new Scene(root, 784, 400);
                        stage.setTitle("Rent A Car");
                        stage.setScene(scene);
                        stage.show();
                        ((Node)event.getSource()).getScene().getWindow().hide();
                    } else {}
                }else if (event.getSource() == menuItemAgencia){
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Agencia/Agencia.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Agencia");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }else if (event.getSource() == menuItemClientes){
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Clientes/Clientes.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Clientes");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }else if (event.getSource() == menuItemCarros){
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Vehiculos/Coche.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Vehiculos");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }else if (event.getSource() == menuItemEmpleados) {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Empleados/Empleados.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Empleado");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }else if (event.getSource() == menuItemSeguro){
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Seguro/Polizas.fxml"));
                    Scene scene = new Scene(root, 928, 600);
                    stage.setTitle("Administrador de Polizas");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }else if (event.getSource() == btnRegistro){
                    AlquilerDAO alquilerDAO = new AlquilerDAO(MySQL.getConnection());
                    LocalDate localDateRen = dpFechaREV.getValue();
                    LocalDate localDateDev = dpFechaDEV.getValue();
                    Date fecharenta = Date.valueOf(localDateRen);
                    Date fechadev = Date.valueOf(localDateDev);

                    String formaPago = cbPago.getSelectionModel().getSelectedItem();

                    alquilerDAO.insert(new Alquiler(
                            Integer.valueOf(txtID.getText()),
                            Integer.valueOf(txtCveAgenciaREV.getText()),
                            Integer.valueOf(txtCveAgenciaDEV.getText()),
                            txtMatricula.getText(),
                            txtRFC.getText(),
                            formaPago,
                            fecharenta,
                            fechadev,
                            txtHoraREN.getText(),
                            txtHoraDEV.getText(),
                            Double.valueOf(txtCosto.getText())
                    ));

                    showMessage("Alquiler Guardado", "Guardar");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    };

    private void loadMarca() {
        MarcaDAO marcaDAO = new MarcaDAO(MySQL.getConnection());
        Iterable<Marca> p = marcaDAO.findAll();
        for (Marca marca : p) {
            cbMarca.getItems().add(marca.getNombre());
        }
    }

    private void loadPago() {
        FormaPagoDAO formaPagoDAO = new FormaPagoDAO(MySQL.getConnection());
        Iterable<FormasPago> p = formaPagoDAO.findAll();
        for (FormasPago formasPago : p) {
            cbPago.getItems().add(formasPago.getTipoPago());
        }
    }

    private void loadOfertas() {
        OfertaDAO ofertaDAO = new OfertaDAO(MySQL.getConnection());
        Iterable<Oferta> p = ofertaDAO.findAll();
        for (Oferta oferta : p) {
            cbOferta.getItems().add(oferta.getNombre());
        }
    }
}
