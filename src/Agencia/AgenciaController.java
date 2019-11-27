package Agencia;

import Clases.Agencia;
import Clases.Distribuidor;
import Database.DAO.AgenciaDAO;
import Database.DAO.DistribuidorDAO;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AgenciaController implements Initializable{
    @FXML TableView<Agencia> tvlAgencia;
    @FXML Button btnNuevo,btnEditar, btnBorrar;
    AgenciaDAO agenciaDAO;
    DistribuidorDAO distribuidorDAO;
    Agencia agencia;
    Distribuidor distribuidor;
    int CveAgencia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("Clave");
        TableColumn direccion = new TableColumn("Direccion");
        TableColumn telefono = new TableColumn("Telefono");

        idClave.setCellValueFactory(new PropertyValueFactory<>("CveAgencia"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        tvlAgencia.getColumns().addAll(idClave, direccion, telefono);
        agenciaDAO = new AgenciaDAO(MySQL.getConnection());
        tvlAgencia.setItems(agenciaDAO.fetchAll());
        tvlAgencia.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEditar.setDisable(false);
                btnBorrar.setDisable(false);
            }
        });

        btnNuevo.setOnAction(eventHandler);
        btnEditar.setOnAction(eventHandler);
        btnBorrar.setOnAction(eventHandler);
        btnEditar.setDisable(true);
        btnBorrar.setDisable(true);

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
        tvlAgencia.setItems(agenciaDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Agencia agencia = tvlAgencia.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            //distribuidorDAO.delete(agencia.getCveAgencia());
            agenciaDAO.delete(agencia.getCveAgencia());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Administrador");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Agencia/Informacion.fxml"));

            InformacionController controller = new InformacionController();

            if (event.getSource() == btnEditar) {
                controller.setMode(InformacionController.Modo_Editar);
                agencia = tvlAgencia.getSelectionModel().getSelectedItem();
                controller.setAgencia(agencia);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlAgencia.getItems().clear();
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
