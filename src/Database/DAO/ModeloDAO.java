package Database.DAO;

import Clases.Modelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO {
    Connection conn;
    public ModeloDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Modelo> fetchAll() {
        ObservableList<Modelo> estado = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM modelo";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Modelo p = null;
            while(rs.next()) {
                p = new Modelo(
                        rs.getString("NombreModelo"),
                        rs.getString("NombreMarca")
                );
                estado.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return estado;
    }

    public List<Modelo> findAll(int id) {
        List<Modelo> agencias = new ArrayList<Modelo>();
        try {
            String query = "SELECT * FROM modelo WHERE CveMarca = " + id + "? ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Modelo p = null;
            while(rs.next()) {
                p = new Modelo(
                        rs.getString("NombreModelo"),
                        rs.getString("NombreMarca"));
                agencias.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return agencias;
    }
}
