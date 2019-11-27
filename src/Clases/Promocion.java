package Clases;

public class Promocion {
    int CveAlquiler, CveAgencia;

    public Promocion(int cveAlquiler, int cveAgencia) {
        CveAlquiler = cveAlquiler;
        CveAgencia = cveAgencia;
    }

    public int getCveAlquiler() {
        return CveAlquiler;
    }

    public void setCveAlquiler(int cveAlquiler) {
        CveAlquiler = cveAlquiler;
    }

    public int getCveAgencia() {
        return CveAgencia;
    }

    public void setCveAgencia(int cveAgencia) {
        CveAgencia = cveAgencia;
    }
}
