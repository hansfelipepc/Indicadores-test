package com.hans.indicadores.model;

public class IndicadoresResponse {

    private String version, autor, fecha;
    private Indicador uf, dolar, utm;

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

    public Indicador getDolar() {
        return dolar;
    }

    public void setDolar(Indicador dolar) {
        this.dolar = dolar;
    }

    public Indicador getUtm() {
        return utm;
    }

    public void setUtm(Indicador utm) {
        this.utm = utm;
    }
}