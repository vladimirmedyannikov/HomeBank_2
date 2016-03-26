package ru.medyannikov.homebank.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.Operation;

/**
 * Created by Vladimir on 20.03.2016.
 */
public class OperationAdapter extends RecyclerView.Adapter<OperationViewHolder> {
    private List<Operation> listOperation;

    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_operation_item,parent, false);
        return new OperationViewHolder(view);
    }

    public OperationAdapter(List<Operation> list){
        listOperation = list;
    }

    @Override
    public void onBindViewHolder(OperationViewHolder holder, int position) {
        holder.about.setText(listOperation.get(position).getAbout());
        holder.value.setText(listOperation.get(position).getValue().toString());
        holder.date.setText(listOperation.get(position).getDateCreated().toString());
        holder.itemView.setId(listOperation.get(position).getIdOperation());
    }

    @Override
    public int getItemCount() {
        return listOperation.size();
    }
}
