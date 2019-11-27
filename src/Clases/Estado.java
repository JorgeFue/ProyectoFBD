package Clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Estado {
    String Nombre;

    public Estado(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
