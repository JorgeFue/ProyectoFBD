package Clases;

public class Empleado {
    String CveEmpleado, Nombre, CURP, Direccion, Telefono;
    int CvePuesto, CveAgencia;
    String CveCiudad, CveEstado;

    public Empleado(String cveEmpleado, String nombre, String CURP, String direccion, String telefono, int cvePuesto, int cveAgencia, String cveCiudad, String cveEstado) {
        CveEmpleado = cveEmpleado;
        Nombre = nombre;
        this.CURP = CURP;
        Direccion = direccion;
        Telefono = telefono;
        CvePuesto = cvePuesto;
        CveAgencia = cveAgencia;
        CveCiudad = cveCiudad;
        CveEstado = cveEstado;
    }

    public String getCveEmpleado() {
        return CveEmpleado;
    }

    public void setCveEmpleado(String cveEmpleado) {
        CveEmpleado = cveEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
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

    public int getCvePuesto() {
        return CvePuesto;
    }

    public void setCvePuesto(int cvePuesto) {
        CvePuesto = cvePuesto;
    }

    public int getCveAgencia() {
        return CveAgencia;
    }

    public void setCveAgencia(int cveAgencia) {
        CveAgencia = cveAgencia;
    }

    public String getCveCiudad() {
        return CveCiudad;
    }

    public void setCveCiudad(String cveCiudad) {
        CveCiudad = cveCiudad;
    }

    public String getCveEstado() {
        return CveEstado;
    }

    public void setCveEstado(String cveEstado) {
        CveEstado = cveEstado;
    }
}
