package Seguro;

import Clases.Seguro;
import Database.DAO.SeguroDAO;
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

public class PolizasController implements Initializable{
    @FXML TableView<Seguro> tvlPoliza;
    @FXML Button btnNuevo,btnEditar, btnBorrar;
    SeguroDAO seguroDAO;
    Seguro seguro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("Poliza");
        TableColumn deducible = new TableColumn("Deducible");
        TableColumn aseguradora = new TableColumn("Clave Aseguradora");

        idClave.setCellValueFactory(new PropertyValueFactory<>("NumeroPoliza"));
        deducible.setCellValueFactory(new PropertyValueFactory<>("Deducibles"));
        aseguradora.setCellValueFactory(new PropertyValueFactory<>("ClaveAseg"));

        tvlPoliza.getColumns().addAll(idClave, deducible, aseguradora);
        seguroDAO = new SeguroDAO(MySQL.getConnection());
        tvlPoliza.setItems(seguroDAO.fetchAll());
        tvlPoliza.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        tvlPoliza.setItems(seguroDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Seguro seguro = tvlPoliza.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            seguroDAO.delete(seguro.getNumeroPoliza());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Administrador");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Seguro/Informacion.fxml"));

            InformacionController controller = new InformacionController();

            if (event.getSource() == btnEditar) {
                controller.setMode(InformacionController.Modo_Editar);
                seguro = tvlPoliza.getSelectionModel().getSelectedItem();
                controller.setSeguro(seguro);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlPoliza.getItems().clear();
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
