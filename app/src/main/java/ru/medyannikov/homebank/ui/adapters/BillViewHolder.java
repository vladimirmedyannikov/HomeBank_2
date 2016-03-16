package ru.medyannikov.homebank.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.medyannikov.homebank.R;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class BillViewHolder extends RecyclerView.ViewHolder {
    protected TextView nameBill;
    protected TextView aboutBill;
    protected TextView summValue;
    protected TextView syncBill;

    public BillViewHolder(View itemView) {
        super(itemView);
        nameBill = (TextView) itemView.findViewById(R.id.billName);
        aboutBill = (TextView) itemView.findViewById(R.id.billAbout);
        summValue = (TextView) itemView.findViewById(R.id.billValue);
        syncBill = (TextView) itemView.findViewById(R.id.billSync);
    }
}
