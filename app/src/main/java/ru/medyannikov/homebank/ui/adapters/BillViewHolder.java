package ru.medyannikov.homebank.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.activity.OperationActivity;
import ru.medyannikov.homebank.ui.fragments.OperationListFragment;
import ru.medyannikov.homebank.utils.ConstantManager;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView nameBill;
    protected TextView aboutBill;
    protected TextView summValue;
    protected TextView syncBill;
    protected Bill bill;

    public BillViewHolder(View itemView) {
        super(itemView);
        nameBill = (TextView) itemView.findViewById(R.id.billName);
        aboutBill = (TextView) itemView.findViewById(R.id.billAbout);
        summValue = (TextView) itemView.findViewById(R.id.billValue);
        syncBill = (TextView) itemView.findViewById(R.id.billSync);
        itemView.setOnClickListener(this);
    }

    public void setBill(Bill bill){
        this.bill = bill;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), OperationActivity.class);
        /*Context c = v.getContext();
        ((FragmentActivity) v.getContext())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, OperationActivity.getInstance(), "")
                .commit();*/
        intent.putExtra(Bill.BILL_EXTRA, bill);
        v.getContext().startActivity(intent);
    }
}
