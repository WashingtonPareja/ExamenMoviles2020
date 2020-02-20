package com.example.examenmoviles2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenmoviles2020.Datos.Volumen;
import com.example.examenmoviles2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterVolumenes extends RecyclerView.Adapter<AdapterVolumenes.volViewHolder> {

    private Context context;
    private List<Volumen> volumen;
    private ItemClickListener clickListener;
    public AdapterVolumenes(Context context,List<Volumen> volumen) {
        this.volumen = volumen;
        this.context = context;
    }

    @Override
    public volViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new volViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(volViewHolder holder, int position) {
        Volumen vol= volumen.get(position);
        String s= vol.getUrl();
        Picasso.with(context).load(s).into(holder.imgmodulo);
        holder.bind(vol);
    }

    @Override
    public int getItemCount() {
        return volumen.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class volViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgmodulo;
        TextView lblTitulo ,lblVol, lblFecha;
        private String mItem;
        public volViewHolder(View itemView) {
            super(itemView);
            imgmodulo = (ImageView) itemView.findViewById(R.id.imgRevista);
            lblTitulo = (TextView) itemView.findViewById(R.id.lbTitulo);
            lblVol = (TextView) itemView.findViewById(R.id.lbVol);
            lblFecha = (TextView) itemView.findViewById(R.id.lbFecha);
            itemView.setOnClickListener(this);


        }
        public void setItem(String item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }


        private void bind(Volumen volumen) {
            lblTitulo.setText(volumen.getTitulo());
            lblVol.setText(volumen.getVolNUm());
            lblFecha.setText(volumen.getFecha());

        }
    }
}

