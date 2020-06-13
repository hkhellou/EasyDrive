package com.example.easydrive10.principal.ui.partes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ItemsListaPartesBinding;
import com.example.easydrive10.pojos.Partes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorRecilerViewPartes extends RecyclerView.Adapter<AdaptadorRecilerViewPartes.ViewHolder> {
    private List<Partes> listaPartes;

    public AdaptadorRecilerViewPartes(List<Partes> listaPartes) {
        this.listaPartes = listaPartes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemsListaPartesBinding itemsListaPartesBinding = DataBindingUtil.inflate(inflater, R.layout.items_lista_partes,parent,false);
        return new ViewHolder(itemsListaPartesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.binding.txtCamionParte.setText(listaPartes.get(position).getCamion());
    holder.binding.txtNombreParte.setText(listaPartes.get(position).getNombre());
    }
    public void removeItem(int position) {
//        BORRAR ELEMENTO DE LA LISTA
        listaPartes.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return listaPartes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private ItemsListaPartesBinding binding;

        public ViewHolder(@NonNull ItemsListaPartesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public List<Partes> getListaPartes() {
        return listaPartes;
    }
}
