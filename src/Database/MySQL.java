package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
    private static Connection conn = null;
    private static String puerto = "3306";
    private static String servidor   = "localhost";
    private static String usuario = "root";
    private static String passusr = "19Sep1900";
    private static String cadconx = "jdbc:mariadb://"+servidor+":"+puerto+"/AlquilerDeAutos";
    //public static Connection conn = null;

    public static void Connect() {
        try{

            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(cadconx, usuario, passusr);
            System.out.println("Conexion Exitosa");

        }catch(Exception e){

            e.printStackTrace();//lanza una alerta y para el programa

        }
    }
    
    public static Connection getConnection(){
        if(conn == null) Connect();
        return conn;
    }
    
    public static void Disconnect() {
        try{
            conn.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
