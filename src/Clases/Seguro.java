package Clases;

public class Seguro {
    String NumeroPoliza;
    double Deducible;
    String Aseguradora;

    public Seguro(String numeroPoliza, double deducible, String aseguradora) {
        NumeroPoliza = numeroPoliza;
        Deducible = deducible;
        Aseguradora = aseguradora;
    }

    public String getNumeroPoliza() {
        return NumeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        NumeroPoliza = numeroPoliza;
    }

    public double getDeducible() {
        return Deducible;
    }

    public void setDeducible(double deducible) {
        Deducible = deducible;
    }

    public String getAseguradora() {
        return Aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        Aseguradora = aseguradora;
    }
}
