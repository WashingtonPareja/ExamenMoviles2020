package com.example.examenmoviles2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examenmoviles2020.Datos.Articulos;
import com.example.examenmoviles2020.R;

import java.util.List;

public class AdapterArticulos extends RecyclerView.Adapter<AdapterArticulos.ArticuloViewHolder> {

    private Context context;
    private List<Articulos> articulos;
    private ItemClickListener clickListener;
    public AdapterArticulos(Context context,List<Articulos> articulos) {
        this.articulos = articulos;
        this.context = context;
    }

    @Override
    public ArticuloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articulos,parent,false);
        return new ArticuloViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ArticuloViewHolder holder, int position) {
        Articulos art= articulos.get(position);
        holder.bind(art);
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ArticuloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPDFArt;
        TextView lblTituloArt ,lblSeccionArt, lblFechaArt, lblPdfArt;
        private String mItem;
        public ArticuloViewHolder(View itemView) {
            super(itemView);
            imgPDFArt = (ImageView) itemView.findViewById(R.id.imgPDFArt);
            lblTituloArt = (TextView) itemView.findViewById(R.id.lblTituloArt);
            lblSeccionArt = (TextView) itemView.findViewById(R.id.lblSeccionArt);
            lblFechaArt = (TextView) itemView.findViewById(R.id.lblFechaArt);
            lblPdfArt = (TextView) itemView.findViewById(R.id.lblPdfArt);
            itemView.setOnClickListener(this);


        }
        public void setItem(String item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }


        private void bind(Articulos articulos) {


            lblTituloArt.setText(articulos.getTitulo());
            lblSeccionArt.setText(articulos.getSeccion());
            lblFechaArt.setText(articulos.getFecha());
            lblPdfArt.setText(articulos.getUrlPdf());
            Glide.with(context).load(R.drawable.pdf).into(imgPDFArt);

        }
    }
}


