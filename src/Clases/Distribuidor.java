package Clases;

public class Distribuidor {
    int CveAgencia;
    String CveEstado;

    public Distribuidor(int cveAgencia, String cveEstado) {
        CveAgencia = cveAgencia;
        CveEstado = cveEstado;
    }

    public int getCveAgencia() {
        return CveAgencia;
    }

    public void setCveAgencia(int cveAgencia) {
        CveAgencia = cveAgencia;
    }

    public String getCveEstado() {
        return CveEstado;
    }

    public void setCveEstado(String cveEstado) {
        CveEstado = cveEstado;
    }
}
