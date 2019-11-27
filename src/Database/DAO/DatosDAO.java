package Database.DAO;

import Clases.Datos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatosDAO {
    Connection conn;
    public DatosDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Datos> fetchAll(String datos) {
        ObservableList<Datos> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM datos where RFC = '" + datos + "';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Datos p = null;
            while(rs.next()) {
                p = new Datos(
                        rs.getInt("CveDatos"),
                        rs.getString("RFC"),
                        rs.getInt("NumCheque"),
                        rs.getString("Referencia"),
                        rs.getString("TitularTarjeta"),
                        rs.getInt("CodigoSeg"),
                        rs.getInt("NumTarjeta"),
                        rs.getInt("NumeroCuenta"),
                        rs.getString("Banco"));
                clientes.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }

    public Boolean delete(String clave) {
        try {
            String query = "delete from datos where CveDatos = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Datos cliente) {
        try {
            String query = "insert into datos "
                    + " (CveDatos, RFC, NumCheque, Referencia, TitularTarjeta, CodigoSeg, NumTarjeta, NumeroCuenta, Banco)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, cliente.getCveDatos());
            st.setString(2, cliente.getRFC());
            st.setInt(3, cliente.getNumCheque());
            st.setString(4, cliente.getReferencia());
            st.setString(5, cliente.getTitularTarjeta());
            st.setInt(6, cliente.getCodigoSeg());
            st.setInt(7, cliente.getNumCheque());
            st.setInt(8, cliente.getNumeroCuenta());
            st.setString(9, cliente.getBanco());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Datos cliente) {
        try {
            String query = "UPDATE datos SET NumCheque = ?, Referencia = ?, TitularTarjeta = ?, CodigoSeg = ?, NumTarjeta = ?, NumeroCuenta = ?, Banco = ? WHERE CveDatos = ? AND RFC = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(8, cliente.getCveDatos());
            st.setString(9, cliente.getRFC());
            st.setInt(1, cliente.getNumCheque());
            st.setString(2, cliente.getReferencia());
            st.setString(3, cliente.getTitularTarjeta());
            st.setInt(4, cliente.getCodigoSeg());
            st.setInt(5, cliente.getNumCheque());
            st.setInt(6, cliente.getNumeroCuenta());
            st.setString(7, cliente.getBanco());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
