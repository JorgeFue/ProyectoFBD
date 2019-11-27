package Database.DAO;

import Clases.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VehiculoDAO {
    Connection conn;
    public VehiculoDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Vehiculo> fetchAll() {
        ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM vehiculo";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Vehiculo p = null;
            while(rs.next()) {
                p = new Vehiculo(
                rs.getString("Matricula"),
                rs.getString("Transmision"),
                rs.getInt("Año"),
                rs.getString("Disponible"),
                rs.getString("GPS"),
                rs.getDouble("CostoDia"),
                rs.getDouble("PrecioArre"),
                rs.getString("NumeroPoliza"),
                rs.getString("NombreModelo"),
                rs.getString("NombreMarca"),
                rs.getInt("CveTipo"));
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
            String query = "delete from vehiculo where Matricula = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Vehiculo vehiculo) {
        try {
            String query = "insert into vehiculo "
                    + " (Matricula, Transimision, Año, Disponible, GPS, CostoDia, PrecioArre, NumeroPoliza, NombreModelo, NombreMarca, CveTipo)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, vehiculo.getMatricula());
            st.setString(2, vehiculo.getTransmision());
            st.setInt(3, vehiculo.getAño());
            st.setString(4, vehiculo.getDisponible());
            st.setString(5, vehiculo.getGPS());
            st.setDouble(6, vehiculo.getCostoDia());
            st.setDouble(7,vehiculo.getPrecioArre());
            st.setString(8, vehiculo.getNumeroPoliza());
            st.setString(9, vehiculo.getCveModelo());
            st.setString(10, vehiculo.getCveMarca());
            st.setInt(11, vehiculo.getCveTipo());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Vehiculo vehiculo) {
        try {
            String query = "UPDATE vehiculo SET Transmision = ?, Año = ?, GPS = ?, CostoDia = ?, PrecioArre = ? WHERE Matricula = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, vehiculo.getTransmision());
            st.setInt(2, vehiculo.getAño());
            st.setString(3, vehiculo.getGPS());
            st.setDouble(4, vehiculo.getCostoDia());
            st.setDouble(5,vehiculo.getPrecioArre());
            st.setString(6, vehiculo.getMatricula());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
