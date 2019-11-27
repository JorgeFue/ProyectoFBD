package Clases;

public class Datos {
    int CveDatos;
    String RFC;
    int NumCheque;
    String Referencia,TitularTarjeta;
    int CodigoSeg, NumTarjeta,NumeroCuenta;
    String Banco;

    public Datos(int cveDatos, String RFC, int numCheque, String referencia, String titularTarjeta, int codigoSeg, int numTarjeta, int numeroCuenta, String banco) {
        CveDatos = cveDatos;
        this.RFC = RFC;
        NumCheque = numCheque;
        Referencia = referencia;
        TitularTarjeta = titularTarjeta;
        CodigoSeg = codigoSeg;
        NumTarjeta = numTarjeta;
        NumeroCuenta = numeroCuenta;
        Banco = banco;
    }

    public int getCveDatos() {
        return CveDatos;
    }

    public void setCveDatos(int cveDatos) {
        CveDatos = cveDatos;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public int getNumCheque() {
        return NumCheque;
    }

    public void setNumCheque(int numCheque) {
        NumCheque = numCheque;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getTitularTarjeta() {
        return TitularTarjeta;
    }

    public void setTitularTarjeta(String titularTarjeta) {
        TitularTarjeta = titularTarjeta;
    }

    public int getCodigoSeg() {
        return CodigoSeg;
    }

    public void setCodigoSeg(int codigoSeg) {
        CodigoSeg = codigoSeg;
    }

    public int getNumTarjeta() {
        return NumTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        NumTarjeta = numTarjeta;
    }

    public int getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        NumeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String banco) {
        Banco = banco;
    }
}
