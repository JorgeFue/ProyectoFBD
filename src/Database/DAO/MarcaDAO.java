package Database.DAO;

import Clases.Marca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {
    Connection conn;
    public MarcaDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Marca> fetchAll() {
        ObservableList<Marca> estado = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM marca";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Marca p = null;
            while(rs.next()) {
                p = new Marca(
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

    public List<Marca> findAll() {
        List<Marca> agencias = new ArrayList<Marca>();
        try {
            String query = "SELECT * FROM marca";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Marca p = null;
            while(rs.next()) {
                p = new Marca(
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
