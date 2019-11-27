package Database.DAO;

import Clases.Agencia;
import Clases.Distribuidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistribuidorDAO {
    Connection conn;
    public DistribuidorDAO(Connection conn){
        this.conn = conn;
    }

    public Boolean insert(Distribuidor agencia) {
        try {
            String query = "insert into distribuidor "
                    + " (CveAgencia, NombreEstado)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, agencia.getCveAgencia());
            st.setString(2, agencia.getCveEstado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean delete(int clave) {
        try {
            String query = "delete from distribuidor where CveAgencia = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Distribuidor> findAll(int id) {
        List<Distribuidor> agencias = new ArrayList<Distribuidor>();
        try {
            String query = "SELECT * FROM distribuidor WHERE CveAgencia = " + id + "?";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Distribuidor p = null;
            while(rs.next()) {
                p = new Distribuidor(
                        rs.getInt("CveAgencia"),
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
