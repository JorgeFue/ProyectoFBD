package Clases;

public class Modelo {
    String Nombre, Marca;

    public Modelo(String nombre, String marca) {
        Nombre = nombre;
        Marca = marca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }
}
