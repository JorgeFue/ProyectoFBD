package Database.DAO;

import Clases.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmpleadoDAO {
    Connection conn;
    public EmpleadoDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Empleado> fetchAll() {
        ObservableList<Empleado> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Empleado";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Empleado p = null;
            while(rs.next()) {
                p = new Empleado(
                        rs.getString("CveEmpleado"),
                        rs.getString("Nombre"),
                        rs.getString("CURP"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getInt("CvePuesto"),
                        rs.getInt("CveAgencia"),
                        rs.getString("Ciudad"),
                        rs.getString("NombreEstado"));
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
            String query = "delete from Empleado where CveEmpleado = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Empleado cliente) {
        try {
            String query = "insert into Empleado "
                    + " (CveEmpleado, Nombre, CURP, Direccion,Telefono, CvePuesto, CveAgencia, Ciudad, NombreEstado)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, cliente.getCveEmpleado());
            st.setString(2, cliente.getNombre());
            st.setString(3, cliente.getCURP());
            st.setString(4, cliente.getDireccion());
            st.setString(5, cliente.getTelefono());
            st.setInt(6, cliente.getCvePuesto());
            st.setInt(7, cliente.getCveAgencia());
            st.setString(8, cliente.getCveCiudad());
            st.setString(9,cliente.getCveEstado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Empleado cliente) {
        try {
            String query = "UPDATE cliente SET Nombre = ?, CURP = ?, Direccion = ?, Telefono = ?, CvePuesto = ?, CveAgencia = ?, Ciudad = ?, NombreEstado = ? WHERE CveEmpleado = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, cliente.getCveEmpleado());
            st.setString(2, cliente.getNombre());
            st.setString(3, cliente.getCURP());
            st.setString(4, cliente.getDireccion());
            st.setString(5, cliente.getTelefono());
            st.setInt(6, cliente.getCvePuesto());
            st.setInt(7, cliente.getCveAgencia());
            st.setString(8, cliente.getCveCiudad());
            st.setString(9,cliente.getCveEstado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
