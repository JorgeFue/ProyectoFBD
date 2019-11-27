package Vehiculos;

import Clases.Vehiculo;
import Database.DAO.VehiculoDAO;
import Database.MySQL;
import com.sun.glass.ui.Menu;
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

public class CarroController implements Initializable {
    @FXML TableView<Vehiculo> tvlAuto;
    @FXML Button btnNuevo,btnEditar, btnBorrar;
    VehiculoDAO vehiculoDAO;
    Vehiculo vehiculo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("Clave");
        TableColumn disponible = new TableColumn("Disponibilidad");
        TableColumn dia = new TableColumn("Costo por Dia");
        TableColumn precio = new TableColumn("Precio Arrendamiento");
        TableColumn poliza = new TableColumn("Numero de Poliza");
        TableColumn modelo = new TableColumn("Modelo");
        TableColumn marca = new TableColumn("Marca");


        idClave.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("Disponible"));
        dia.setCellValueFactory(new PropertyValueFactory<>("CostoDia"));
        precio.setCellValueFactory(new PropertyValueFactory<>("PrecioArre"));
        poliza.setCellValueFactory(new PropertyValueFactory<>("NumeroPoliza"));
        modelo.setCellValueFactory(new PropertyValueFactory<>("NombreModelo"));
        marca.setCellValueFactory(new PropertyValueFactory<>("NombreMarca"));

        tvlAuto.getColumns().addAll(idClave, disponible, dia, precio, poliza, modelo, marca);
        vehiculoDAO = new VehiculoDAO(MySQL.getConnection());
        tvlAuto.setItems(vehiculoDAO.fetchAll());
        tvlAuto.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        tvlAuto.setItems(vehiculoDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Vehiculo vehiculo = tvlAuto.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            vehiculoDAO.delete(vehiculo.getMatricula());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Administrador");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vehiculos/InformacionController.fxml"));

            InformacionAController controller = new InformacionAController();

            if (event.getSource() == btnEditar) {
                controller.setMode(InformacionAController.Modo_Editar);
                vehiculo = tvlAuto.getSelectionModel().getSelectedItem();
                controller.setVehiculo(vehiculo);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlAuto.getItems().clear();
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
