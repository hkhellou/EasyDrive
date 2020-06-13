package com.example.easydrive10.principal.ui.viajes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ItemsListaViajesBinding;
import com.example.easydrive10.pojos.Viajes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorRecicledViewViajes extends RecyclerView.Adapter<AdaptadorRecicledViewViajes.ViewHolder> {
    private List<Viajes> listaViajes;

    public AdaptadorRecicledViewViajes(List<Viajes> listaViajes) {
        this.listaViajes = listaViajes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemsListaViajesBinding itemsListaViajesBinding = DataBindingUtil.inflate(inflater, R.layout.items_lista_viajes,parent,false);
        return new ViewHolder(itemsListaViajesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtFechaSalidaViajes.setText(listaViajes.get(position).getFechaSalida());
        holder.binding.txtPaisDestinoViajes.setText(listaViajes.get(position).getPaisDestino());
        holder.binding.txtFechaLlegada.setText(listaViajes.get(position).getFechaLlegada());

    }
    public void removeItem(int position) {
//        BORRAR ELEMENTO DE LA LISTA
        listaViajes.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
//        CANTIDAD DE ELEMENTOS QUE TENDRÁ QUE PROCESAR EL RECYCLER
        return listaViajes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private ItemsListaViajesBinding binding;

        public ViewHolder(@NonNull ItemsListaViajesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public List<Viajes> getListaViajes() {
        return listaViajes;
    }
}
