package com.teamallqu.frd;

public class c_ficha {
    int numsujeto;
    String numhistoria;
    String edad;
    String talla;
    String peso;
    String imc;
    String estado;
    String diagnostico;
    String fecha;

    public int getNumsujeto() {
        return numsujeto;
    }

    public void setNumsujeto(int numsujeto) {
        this.numsujeto = numsujeto;
    }

    public c_ficha(int numsujeto, String numhistoria, String edad, String talla, String peso, String imc, String estado, String diagnostico, String fecha) {
        this.numsujeto = numsujeto;
        this.numhistoria = numhistoria;
        this.edad = edad;
        this.talla = talla;
        this.peso = peso;
        this.imc = imc;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.fecha = fecha;
    }

    public c_ficha() {
    }

    public String getNumhistoria() {
        return numhistoria;
    }

    public void setNumhistoria(String numhistoria) {
        this.numhistoria = numhistoria;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public c_ficha(String numhistoria, String edad, String talla, String peso, String imc, String estado, String diagnostico, String fecha) {
        this.numhistoria = numhistoria;
        this.edad = edad;
        this.talla = talla;
        this.peso = peso;
        this.imc = imc;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.fecha = fecha;
    }
}
