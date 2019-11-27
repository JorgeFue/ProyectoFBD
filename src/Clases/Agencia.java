package Clases;

public class Agencia {
    int CveAgencia;
    String Direccion, Telefono;

    public Agencia(int cveAgencia, String direccion, String telefono) {
        CveAgencia = cveAgencia;
        Direccion = direccion;
        Telefono = telefono;
    }

    public int getCveAgencia() {
        return CveAgencia;
    }

    public void setCveAgencia(int cveAgencia) {
        CveAgencia = cveAgencia;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
