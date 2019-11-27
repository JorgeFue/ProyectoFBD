package Database.DAO;

import Clases.Agencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {
    Connection conn;
    public AgenciaDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Agencia> fetchAll() {
        ObservableList<Agencia> agencias = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM alquilerautos";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Agencia p = null;
            while(rs.next()) {
                p = new Agencia(
                        rs.getInt("CveAgencia"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"));
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

    public Boolean delete(int clave) {
        try {
            String query = "delete from alquilerautos where CveAgencia = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Agencia> findAll() {
        List<Agencia> agencias = new ArrayList<Agencia>();
        try {
            String query = "SELECT * FROM alquilerautos";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Agencia p = null;
            while(rs.next()) {
                p = new Agencia(
                        rs.getInt("CveAgencia"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"));
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

    public Boolean insert(Agencia agencia) {
        try {
            String query = "insert into alquilerautos "
                        + " (CveAgencia, Direccion, Telefono)"
                    + " values (?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, agencia.getCveAgencia());
            st.setString(2, agencia.getDireccion());
            st.setString(3, agencia.getTelefono());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Agencia agencia) {
        try {
            String query = "UPDATE alquilerautos SET Direccion = ?, Telefono = ? WHERE CveAgencia = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   3, agencia.getCveAgencia());
            st.setString(1, agencia.getDireccion());
            st.setString(2, agencia.getTelefono());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
