package Clases;

import java.sql.Date;

public class Alquiler {
    int CveAlquiler, CveAgenciaRenta, CveAgenciaDev;
    String Matricula, RFC;
    String  CveFormaPago;
    Date FechaRenta, FechaDev;
    String HoraRenta, HoraDev;
    double CostoTotal;

    public Alquiler(int cveAlquiler, int cveAgenciaRenta, int cveAgenciaDev, String matricula, String RFC, String cveFormaPago, Date fechaRenta, Date fechaDev, String horaRenta, String horaDev, double costoTotal) {
        CveAlquiler = cveAlquiler;
        CveAgenciaRenta = cveAgenciaRenta;
        CveAgenciaDev = cveAgenciaDev;
        Matricula = matricula;
        this.RFC = RFC;
        CveFormaPago = cveFormaPago;
        FechaRenta = fechaRenta;
        FechaDev = fechaDev;
        HoraRenta = horaRenta;
        HoraDev = horaDev;
        CostoTotal = costoTotal;
    }

    public int getCveAlquiler() {
        return CveAlquiler;
    }

    public void setCveAlquiler(int cveAlquiler) {
        CveAlquiler = cveAlquiler;
    }

    public int getCveAgenciaRenta() {
        return CveAgenciaRenta;
    }

    public void setCveAgenciaRenta(int cveAgenciaRenta) {
        CveAgenciaRenta = cveAgenciaRenta;
    }

    public int getCveAgenciaDev() {
        return CveAgenciaDev;
    }

    public void setCveAgenciaDev(int cveAgenciaDev) {
        CveAgenciaDev = cveAgenciaDev;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getCveFormaPago() {
        return CveFormaPago;
    }

    public void setCveFormaPago(String cveFormaPago) {
        CveFormaPago = cveFormaPago;
    }

    public Date getFechaRenta() {
        return FechaRenta;
    }

    public void setFechaRenta(Date fechaRenta) {
        FechaRenta = fechaRenta;
    }

    public Date getFechaDev() {
        return FechaDev;
    }

    public void setFechaDev(Date fechaDev) {
        FechaDev = fechaDev;
    }

    public String getHoraRenta() {
        return HoraRenta;
    }

    public void setHoraRenta(String horaRenta) {
        HoraRenta = horaRenta;
    }

    public String getHoraDev() {
        return HoraDev;
    }

    public void setHoraDev(String horaDev) {
        HoraDev = horaDev;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }
}
