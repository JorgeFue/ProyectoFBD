package Database.DAO;

import Clases.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    Connection conn;
    public EstadoDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Estado> fetchAll() {
        ObservableList<Estado> estado = FXCollections.observableArrayList();
        try {
            String query = "SELECT CveEstado FROM estado";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Estado p = null;
            while(rs.next()) {
                p = new Estado(
                        rs.getString("NombreEstado"));
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

    public List<Estado> findAll() {
        List<Estado> agencias = new ArrayList<Estado>();
        try {
            String query = "SELECT * FROM estado";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Estado p = null;
            while(rs.next()) {
                p = new Estado(
                        rs.getString("NombreEstado"));
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
