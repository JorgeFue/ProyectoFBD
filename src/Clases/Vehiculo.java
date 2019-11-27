package Clases;

public class Vehiculo {
    String Matricula, Transmision;
    int Año;
    String Disponible, GPS;
    double CostoDia, PrecioArre;
    String NumeroPoliza, CveModelo, CveMarca;
    int CveTipo;

    public Vehiculo(String matricula, String transmision, int año, String disponible, String GPS, double costoDia, double precioArre, String numeroPoliza, String cveModelo, String cveMarca, int cveTipo) {
        Matricula = matricula;
        Transmision = transmision;
        Año = año;
        Disponible = disponible;
        this.GPS = GPS;
        CostoDia = costoDia;
        PrecioArre = precioArre;
        NumeroPoliza = numeroPoliza;
        CveModelo = cveModelo;
        CveMarca = cveMarca;
        CveTipo = cveTipo;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getTransmision() {
        return Transmision;
    }

    public void setTransmision(String transmision) {
        Transmision = transmision;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int año) {
        Año = año;
    }

    public String getDisponible() {
        return Disponible;
    }

    public void setDisponible(String disponible) {
        Disponible = disponible;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public double getCostoDia() {
        return CostoDia;
    }

    public void setCostoDia(double costoDia) {
        CostoDia = costoDia;
    }

    public double getPrecioArre() {
        return PrecioArre;
    }

    public void setPrecioArre(double precioArre) {
        PrecioArre = precioArre;
    }

    public String getNumeroPoliza() {
        return NumeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        NumeroPoliza = numeroPoliza;
    }

    public String getCveModelo() {
        return CveModelo;
    }

    public void setCveModelo(String cveModelo) {
        CveModelo = cveModelo;
    }

    public String getCveMarca() {
        return CveMarca;
    }

    public void setCveMarca(String cveMarca) {
        CveMarca = cveMarca;
    }

    public int getCveTipo() {
        return CveTipo;
    }

    public void setCveTipo(int cveTipo) {
        CveTipo = cveTipo;
    }
}
