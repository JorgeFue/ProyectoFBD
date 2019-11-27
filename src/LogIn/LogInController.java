package LogIn;

import Database.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML TextField txtUsuario;
    @FXML Button btnLogIn;
    @FXML Label lblAviso;
    public static String usuario;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogIn.setOnAction(log);
    }

    EventHandler<ActionEvent> log = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnLogIn){
                conn = MySQL.getConnection();
                String sql = "select * from Empleado where CveEmpleado = ? and CvePuesto = 3 or CvePuesto = 1";
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, txtUsuario.getText());
                    rs = ps.executeQuery();
                    if (rs.next()){
                        usuario = txtUsuario.getText();
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/MainWindow/MainWindow.fxml"));
                        Scene scene = new Scene(root,1200,700);
                        stage.setTitle("Rent A Car");
                        stage.setScene(scene);
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                        lblAviso.setText("SDADct");
                        lblAviso.setTextFill(Color.web("#C70039"));
                    }else{
                        lblAviso.setText("User Key incorrect");
                        lblAviso.setTextFill(Color.web("#C70039"));
                    }
                }catch (Exception e){
                    System.out.println("" + e);
                }
            }
        }
    };
}
