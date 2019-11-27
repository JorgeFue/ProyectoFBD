package Clases;

public class Dispone {
    int CveAgencia;
    String Matricula;

    public Dispone(int cveAgencia, String matricula) {
        CveAgencia = cveAgencia;
        Matricula = matricula;
    }

    public int getCveAgencia() {
        return CveAgencia;
    }

    public void setCveAgencia(int cveAgencia) {
        CveAgencia = cveAgencia;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }
}
