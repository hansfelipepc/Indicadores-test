package com.hans.indicadores.model;

public class IndicadoresResponse {

    private String version;
    private String autor;
    private String fecha;
    private Indicador uf;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Indicador getUf() {
        return uf;
    }

    public void setUf(Indicador uf) {
        this.uf = uf;
    }
}