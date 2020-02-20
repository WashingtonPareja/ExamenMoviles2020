package com.example.examenmoviles2020.Datos;



public class Articulos {
    public String Titulo;
    public String Fecha;
    public String Seccion;
    public String UrlPdf;

    public String getTitulo() {
        return Titulo;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getSeccion() {
        return Seccion;
    }

    public String getUrlPdf() {
        return UrlPdf;
    }


    public Articulos (String Titulo, String Fecha, String Seccion,String UrlPdf){
        this.Titulo = Titulo;
        this.Fecha = Fecha;
        this.Seccion = Seccion;
        this.UrlPdf = UrlPdf;
    }

}
