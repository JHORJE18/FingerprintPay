package com.jhorje18.fingerprintpay.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhorje18.fingerprintpay.MainActivity;
import com.jhorje18.fingerprintpay.Objetos.Producto;
import com.jhorje18.fingerprintpay.R;

import java.util.ArrayList;

/**
 * Created by aeolservice on 13/3/18.
 */

public class AdaptadorCompras extends BaseAdapter {

    //Variables
    Activity activity;
    ArrayList<Producto> listaProductos;

    public AdaptadorCompras(Activity act, ArrayList<Producto> listado) {
        this.activity = act;
        this.listaProductos = listado;
    }

    private void cargarImagen(String link, ImageView postImg) {
        //Si la iamgen esta vacia, no carges nada
        if (!link.equals("")){
            Glide.with(this.activity)
                    .load(link)
                    .into(postImg);
        }
    }

    @Override
    public int getCount() {
        return this.listaProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;

        if (convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.card_compra, null);
        }

        //Elementos Vista
        TextView txtTitulo, txtDescripcion, txtPrecio;
        Button btnComprar;
        ImageView imgProducto;

        txtDescripcion = v.findViewById(R.id.txtCardDescripcion);
        txtPrecio = v.findViewById(R.id.txtCardPrecio);
        txtTitulo = v.findViewById(R.id.txtCardTitulo);
        imgProducto = v.findViewById(R.id.imgCardImagen);
        btnComprar = v.findViewById(R.id.btnCardPay);

        //Establecer valores
        txtTitulo.setText(listaProductos.get(position).getTitulo());
        txtDescripcion.setText(listaProductos.get(position).getDescripcion());
        txtPrecio.setText(String.valueOf((int) listaProductos.get(position).getPrecio()) + "€");

        cargarImagen(listaProductos.get(position).getUrlPicture(), imgProducto);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) activity).solicitarHuella();
            }
        });

        return v;
    }
}
