package com.example.examenmoviles2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examenmoviles2020.Datos.Articulos;
import com.example.examenmoviles2020.WebService.Asynchtask;
import com.example.examenmoviles2020.WebService.WebService;
import com.example.examenmoviles2020.adapters.AdapterArticulos;
import com.example.examenmoviles2020.adapters.ItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class paperActivity extends AppCompatActivity implements Asynchtask, ItemClickListener {

    private RecyclerView recyclerView;
    private Context context;
    private AdapterArticulos adapterArticulos;
    private List<Articulos> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        recyclerView = (RecyclerView)findViewById(R.id.rscarticulos);
        context = this;
        Bundle b = getIntent().getExtras();

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getarticles.php?volumen="+b.getString("volumen")+"&num="+b.getString("numero"),
                datos, this, this);
        ws.execute("");
    }

    ImageView imageView;
    public boolean isDownloadManagerAvailable()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
    @Override
    public void onClick(View view, final int position) {
        final String articlee = data.get(position).getTitulo();
        String url = data.get(position).getUrlPdf();
        imageView = (ImageView)view.findViewById(R.id.imgPDFArt);
        imageView.setTag(data.get(position).getUrlPdf());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDownloadManagerAvailable())
                {
                    String url = data.get(position).getUrlPdf();
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setDescription("PDF Paper");
                    request.setTitle(articlee);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url);

                    DownloadManager manager = (DownloadManager)getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                    try {
                        manager.enqueue(request);

                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        //Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
        for (int i=0; i<jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            Articulos volumen = new Articulos(obj.getString("title"),obj.getString("volume"),obj.getString("section_title"),obj.getString("pdf"));
            data.add(volumen);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);//numero columnas
        adapterArticulos = new AdapterArticulos(context,data);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterArticulos);
        recyclerView.setHasFixedSize(true);
        adapterArticulos.setClickListener(this);
    }
}
