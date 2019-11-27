package Clases;

public class Puesto {
    int CvePuesto;
    String Nombre;
    double Sueldo;

    public Puesto(int cvePuesto, String nombre, double sueldo) {
        CvePuesto = cvePuesto;
        Nombre = nombre;
        Sueldo = sueldo;
    }

    public int getCvePuesto() {
        return CvePuesto;
    }

    public void setCvePuesto(int cvePuesto) {
        CvePuesto = cvePuesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getSueldo() {
        return Sueldo;
    }

    public void setSueldo(double sueldo) {
        Sueldo = sueldo;
    }
}
