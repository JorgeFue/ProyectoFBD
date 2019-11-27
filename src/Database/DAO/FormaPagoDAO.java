package Database.DAO;

import Clases.FormasPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormaPagoDAO {
    Connection conn;
    public FormaPagoDAO(Connection conn){
        this.conn = conn;
    }

    public List<FormasPago> findAll() {
        List<FormasPago> agencias = new ArrayList<FormasPago>();
        try {
            String query = "SELECT * FROM formapago";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FormasPago p = null;
            while(rs.next()) {
                p = new FormasPago(
                        rs.getString("TipoPago"));
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
