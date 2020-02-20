package com.example.examenmoviles2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.examenmoviles2020.Datos.Volumen;
import com.example.examenmoviles2020.WebService.Asynchtask;
import com.example.examenmoviles2020.WebService.WebService;
import com.example.examenmoviles2020.adapters.AdapterVolumenes;
import com.example.examenmoviles2020.adapters.ItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask, ItemClickListener {
    private RecyclerView recyclerView;
    private Context context;
    private AdapterVolumenes adapterVolumenes;
    private List<Volumen> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.rcvvolumenes);
        context = this;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getrevistas.php",
                datos, this, this);
        ws.execute("");
    }





    @Override
    public void onClick(View view, int position) {

        String vol , numero;
        numero = data.get(position).getNum();
        vol = data.get(position).getVolNUm();
        Intent intent = new Intent(MainActivity.this,paperActivity.class);
        intent.putExtra("volumen",vol);
        intent.putExtra("numero",numero);
        startActivity(intent);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("issues");
        //Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
        for (int i=0; i<jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Volumen volumen = new Volumen(obj.getString("title"),obj.getString("volume"),obj.getString("number"),obj.getString("date_published"),obj.getString("portada"));
            data.add(volumen);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);//numero columnas
        adapterVolumenes = new AdapterVolumenes(context,data);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterVolumenes);
        recyclerView.setHasFixedSize(true);
        adapterVolumenes.setClickListener(this);
    }
}
