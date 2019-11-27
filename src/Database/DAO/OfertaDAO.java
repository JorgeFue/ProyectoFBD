package Database.DAO;

import Clases.Oferta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {
    Connection conn;
    public OfertaDAO(Connection conn){
        this.conn = conn;
    }

    public List<Oferta> findAll() {
        List<Oferta> agencias = new ArrayList<Oferta>();
        try {
            String query = "SELECT * FROM oferta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Oferta p = null;
            while(rs.next()) {
                p = new Oferta(
                        rs.getString("Nombre"),
                        rs.getDouble("Descuento"));
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
