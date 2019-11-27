package Database.DAO;

import Clases.Seguro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SeguroDAO {
    Connection conn;
    public SeguroDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Seguro> fetchAll() {
        ObservableList<Seguro> vehiculos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM seguro";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Seguro p = null;
            while(rs.next()) {
                p = new Seguro(
                        rs.getString("NumeroPoliza"),
                        rs.getDouble("Deducibles"),
                        rs.getString("CveAseg"));
                vehiculos.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return vehiculos;
    }

    public Boolean delete(String clave) {
        try {
            String query = "delete from seguro where NumeroPoliza = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Seguro vehiculo) {
        try {
            String query = "insert into seguro "
                    + " (NumeroPoliza, Deducibles, CveAseg)"
                    + " values (?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, vehiculo.getNumeroPoliza());
            st.setDouble(2, vehiculo.getDeducible());
            st.setString(3, vehiculo.getAseguradora());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Seguro vehiculo) {
        try {
            String query = "UPDATE seguro SET Deducibles = ? WHERE NumeroPoliza = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, vehiculo.getNumeroPoliza());
            st.setDouble(2, vehiculo.getDeducible());
            st.setString(3, vehiculo.getAseguradora());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
