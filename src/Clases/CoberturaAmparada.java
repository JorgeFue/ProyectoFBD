package Clases;

public class CoberturaAmparada {
    String NumeroPoliza;
    int CveCobertura;

    public CoberturaAmparada(String numeroPoliza, int cveCobertura) {
        NumeroPoliza = numeroPoliza;
        CveCobertura = cveCobertura;
    }

    public String getNumeroPoliza() {
        return NumeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        NumeroPoliza = numeroPoliza;
    }

    public int getCveCobertura() {
        return CveCobertura;
    }

    public void setCveCobertura(int cveCobertura) {
        CveCobertura = cveCobertura;
    }
}
