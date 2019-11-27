package Clientes;

import Clases.Cliente;
import Clases.Datos;
import Database.DAO.ClienteDAO;
import Database.DAO.DatosDAO;
import Database.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DatosController implements Initializable {
    @FXML TableView<Datos> tvlDatos;
    @FXML Button btnNuevo,btnEditar, btnBorrar;
    DatosDAO datosDAO;
    Datos datos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("RFC");
        TableColumn chque = new TableColumn("Numero de Cheque");
        TableColumn referencia = new TableColumn("Referencia");
        TableColumn Titular = new TableColumn("Titular Tarjeta");
        TableColumn tarjeta = new TableColumn("Numero de Tarjeta");
        TableColumn cuenta = new TableColumn("Numero de Cuenta");
        TableColumn banco = new TableColumn("Banco");

        idClave.setCellValueFactory(new PropertyValueFactory<>("CveDatos"));
        chque.setCellValueFactory(new PropertyValueFactory<>("NumCheque"));
        referencia.setCellValueFactory(new PropertyValueFactory<>("Referencia"));
        Titular.setCellValueFactory(new PropertyValueFactory<>("TitularTarjeta"));
        tarjeta.setCellValueFactory(new PropertyValueFactory<>("NumTarjeta"));
        cuenta.setCellValueFactory(new PropertyValueFactory<>("NumeroCuenta"));
        banco.setCellValueFactory(new PropertyValueFactory<>("Banco"));

        tvlDatos.getColumns().addAll(idClave, chque, referencia);
        datosDAO = new DatosDAO(MySQL.getConnection());
        tvlDatos.setItems(datosDAO.fetchAll(ClienteController.RFC));

        btnNuevo.setOnAction(eventHandler);
        btnEditar.setOnAction(eventHandler);
        btnBorrar.setOnAction(eventHandler);
    }

    EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnNuevo || event.getSource() == btnEditar){
                mostrarVentana(event);
            }else if (event.getSource() == btnBorrar){
                ConfirmEliminar();
            }
        }
    };

    private void recargarAgencias() {
        tvlDatos.setItems(datosDAO.fetchAll(ClienteController.RFC));
    }

    private void ConfirmEliminar(){
        Datos datos = tvlDatos.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            datosDAO.delete(datos.getRFC());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Administrador");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientes/DatosBancarios.fxml"));

            BancariosController controller = new BancariosController();

            if (event.getSource() == btnEditar) {
                controller.setMode(BancariosController.Modo_Editar);
                datos = tvlDatos.getSelectionModel().getSelectedItem();
                controller.setDatos(datos);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlDatos.getItems().clear();
                    recargarAgencias();
                }
            });

            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
