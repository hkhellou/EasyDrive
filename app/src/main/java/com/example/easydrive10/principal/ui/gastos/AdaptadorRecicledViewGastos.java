package com.example.easydrive10.principal.ui.gastos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ItemsListaGastosBinding;
import com.example.easydrive10.pojos.Gastos;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorRecicledViewGastos extends RecyclerView.Adapter<AdaptadorRecicledViewGastos.ViewHolder> implements ViewGroup.OnClickListener {
    private List<Gastos> listaGastos;

    public AdaptadorRecicledViewGastos(List<Gastos> listaGastos) {
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemsListaGastosBinding itemsListaGastosBinding = DataBindingUtil.inflate(inflater, R.layout.items_lista_gastos,parent,false);
        return new ViewHolder(itemsListaGastosBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemsListaGastosBinding.txtNombreGasto.setText(listaGastos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        //        CANTIDAD DE ELEMENTOS QUE TENDRÁ QUE PROCESAR EL RECYCLER
        return listaGastos.size();
    }

    @Override
    public void onClick(View view) {
        
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemsListaGastosBinding itemsListaGastosBinding;

        public ViewHolder(@NonNull ItemsListaGastosBinding itemsListaGastosBinding) {
            super(itemsListaGastosBinding.getRoot());
            this.itemsListaGastosBinding = itemsListaGastosBinding;
        }
    }
}
