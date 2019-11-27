package Empleados;

import Clases.Empleado;
import Database.DAO.EmpleadoDAO;
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

public class EmpleadosController implements Initializable{
    @FXML TableView<Empleado> tvlEmpleados;
    @FXML
    Button btnNuevo,btnEditar, btnBorrar;
    EmpleadoDAO empleadoDAO;
    Empleado empleado;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("Clave");
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn curp = new TableColumn("CURP");
        TableColumn direccion = new TableColumn("Direccion");
        TableColumn telefono = new TableColumn("Telefono");
        TableColumn ciudad = new TableColumn("Ciudad");
        TableColumn estado = new TableColumn("Estado");


        idClave.setCellValueFactory(new PropertyValueFactory<>("CveEmpleado"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        curp.setCellValueFactory(new PropertyValueFactory<>("CURP"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        ciudad.setCellValueFactory(new PropertyValueFactory<>("Ciudad"));
        estado.setCellValueFactory(new PropertyValueFactory<>("NombreEstado"));

        tvlEmpleados.getColumns().addAll(idClave, nombre, curp, direccion, telefono, ciudad, estado);
        empleadoDAO = new EmpleadoDAO(MySQL.getConnection());
        tvlEmpleados.setItems(empleadoDAO.fetchAll());
        tvlEmpleados.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        tvlEmpleados.setItems(empleadoDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Empleado empleado = tvlEmpleados.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            empleadoDAO.delete(empleado.getCveEmpleado());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Administrador");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Empleados/Informacion.fxml"));

            InformacionController controller = new InformacionController();

            if (event.getSource() == btnEditar) {
                controller.setMode(Agencia.InformacionController.Modo_Editar);
                empleado = tvlEmpleados.getSelectionModel().getSelectedItem();
                controller.setEmpleado(empleado);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlEmpleados.getItems().clear();
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
