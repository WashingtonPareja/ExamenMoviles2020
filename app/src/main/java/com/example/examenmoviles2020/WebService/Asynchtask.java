package com.example.examenmoviles2020.WebService;


import android.view.View;

import org.json.JSONException;

public interface Asynchtask {
    void onClick(View view, int position);

    /**
     * ESta funcion retorna los datos devueltos por el ws
     * @param result
     */
    void processFinish(String result) throws JSONException;

}
