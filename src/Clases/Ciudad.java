package Clases;

public class Ciudad {
    String CveCiudad, Nombre, CveEstado;

    public Ciudad(String cveCiudad, String nombre, String cveEstado) {
        CveCiudad = cveCiudad;
        Nombre = nombre;
        CveEstado = cveEstado;
    }

    public String getCveCiudad() {
        return CveCiudad;
    }

    public void setCveCiudad(String cveCiudad) {
        CveCiudad = cveCiudad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCveEstado() {
        return CveEstado;
    }

    public void setCveEstado(String cveEstado) {
        CveEstado = cveEstado;
    }
}
