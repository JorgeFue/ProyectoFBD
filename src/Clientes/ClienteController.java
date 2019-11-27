package Clientes;

import Clases.Cliente;
import Database.DAO.ClienteDAO;
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

public class ClienteController implements Initializable{
    @FXML TableView<Cliente> tvlCliente;
    @FXML Button btnNuevo,btnEditar, btnBorrar;
    ClienteDAO clienteDAO;
    Cliente cliente;
    public static String RFC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("RFC");
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn telefono = new TableColumn("Telefono");
        TableColumn estado = new TableColumn("Estado");

        idClave.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        estado.setCellValueFactory(new PropertyValueFactory<>("NombreEstado"));

        tvlCliente.getColumns().addAll(idClave, nombre, telefono, estado);
        clienteDAO = new ClienteDAO(MySQL.getConnection());
        tvlCliente.setItems(clienteDAO.fetchAll());

        tvlCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        tvlCliente.setItems(clienteDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Cliente cliente = tvlCliente.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            clienteDAO.delete(cliente.getRFC());
            recargarAgencias();
        }
    }

    private void mostrarVentana(ActionEvent event) {
        try {
            Stage window = new Stage();
            window.setTitle("Informacion Cliente");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientes/Informacion.fxml"));

            InformacionCController controller = new InformacionCController();

            if (event.getSource() == btnEditar) {
                controller.setMode(InformacionCController.Modo_Editar);
                Cliente cliente = tvlCliente.getSelectionModel().getSelectedItem();
                controller.setCliente(cliente);
            }

            fxmlLoader.setController(controller);

            Parent root = (Parent)fxmlLoader.load();
            Scene scene = new Scene(root);

            window.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("hide...");
                    tvlCliente.getItems().clear();
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
