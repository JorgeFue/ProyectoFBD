package Clases;

public class Oferta {
    String Nombre;
    double Descuento;

    public Oferta(String nombre, double descuento) {
        Nombre = nombre;
        Descuento = descuento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double descuento) {
        Descuento = descuento;
    }
}
