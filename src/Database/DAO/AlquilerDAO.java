package Database.DAO;

import Clases.Alquiler;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AlquilerDAO {
    Connection conn;
    public AlquilerDAO(Connection conn){
        this.conn = conn;
    }

    public Boolean insert(Alquiler alquiler) {
        try {
            String query = "insert into alquiler"
                    + " (CveAlquiler, CveAgenciaRenta, CveAgenciaDev, Matricula, RFC, TipoPago, FechaRenta,FechaDev,HoraRenta,HoraDev,CostoTotal)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, alquiler.getCveAlquiler());
            st.setInt(2, alquiler.getCveAgenciaRenta());
            st.setInt(3, alquiler.getCveAgenciaDev());
            st.setString(4, alquiler.getMatricula());
            st.setString(5, alquiler.getRFC());
            st.setString(6, alquiler.getCveFormaPago());
            st.setDate(7, alquiler.getFechaRenta());
            st.setDate(8, alquiler.getFechaDev());
            st.setString(9, alquiler.getHoraRenta());
            st.setString(10, alquiler.getHoraDev());
            st.setDouble(11, alquiler.getCostoTotal());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
