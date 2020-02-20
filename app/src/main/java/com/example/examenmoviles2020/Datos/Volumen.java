package com.example.examenmoviles2020.Datos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volumen {
    private String titulo;
    private String VolNUm;
    private String Vol;
    private String Num;
    private String url;
    private String fecha;


    public Volumen(String titulo ,String VolNUm,String Num, String fecha, String url) {
        this.titulo = titulo;
        this.VolNUm = VolNUm;
        this.fecha = fecha;
        this.Num = Num;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVolNUm() {
        return VolNUm;
    }

    public void setVolNUm(String volNUm) {
        VolNUm = volNUm;
    }

    public String getVol() {
        return Vol;
    }

    public void setVol(String vol) {
        Vol = vol;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
