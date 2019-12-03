package com.easysitp.easysitp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView mensaje;
    private TextView hora;

    public HolderMensaje(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.mensajeMensaje);
        mensaje = itemView.findViewById(R.id.nombreMensaje);
        hora = itemView.findViewById(R.id.horaMensaje);

    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }
}