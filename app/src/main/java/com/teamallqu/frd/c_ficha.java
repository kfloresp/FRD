package com.teamallqu.frd;

public class c_ficha {
    String numsujeto;
    String nombreape;
    String numhistoria;
    String fecha;
    String edad;
    double talla;
    double peso;
    double imc;
    String resultado;
    String diagnostico;

    public String getNumsujeto() {
        return numsujeto;
    }

    public void setNumsujeto(String numsujeto) {
        this.numsujeto = numsujeto;
    }

    public String getNombreape() {
        return nombreape;
    }

    public void setNombreape(String nombreape) {
        this.nombreape = nombreape;
    }

    public String getNumhistoria() {
        return numhistoria;
    }

    public void setNumhistoria(String numhistoria) {
        this.numhistoria = numhistoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public c_ficha(String numsujeto, String nombreape, String numhistoria, String fecha, String edad, double talla, double peso, double imc, String resultado, String diagnostico) {
        this.numsujeto = numsujeto;
        this.nombreape = nombreape;
        this.numhistoria = numhistoria;
        this.fecha = fecha;
        this.edad = edad;
        this.talla = talla;
        this.peso = peso;
        this.imc = imc;
        this.resultado = resultado;
        this.diagnostico = diagnostico;
    }

    public c_ficha() {
    }
}
