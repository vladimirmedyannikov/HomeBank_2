package ru.medyannikov.homebank.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class BillAdapter extends RecyclerView.Adapter<BillViewHolder> {
    private List<Bill> billList = new ArrayList<>();

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_bill_item, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.nameBill.setText(bill.getName());
        holder.aboutBill.setText(bill.getAbout());
        holder.summValue.setText(bill.getValue().toString());
        holder.itemView.setId(bill.getIdBill());
    }

    @Override
    public long getItemId(int position) {
        return billList.get(position).getIdBill();
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }
}
