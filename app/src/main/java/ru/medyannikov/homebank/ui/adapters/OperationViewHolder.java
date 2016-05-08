package ru.medyannikov.homebank.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.medyannikov.homebank.R;

/**
 * Created by Vladimir on 20.03.2016.
 */
public class OperationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView about;
    protected TextView sync;
    protected TextView value;
    protected TextView date;
    //protected TextView bill_name;

    public OperationViewHolder(View itemView) {
        super(itemView);
        about = (TextView) itemView.findViewById(R.id.operatAbout);
        sync = (TextView) itemView.findViewById(R.id.operatSync);
        value = (TextView) itemView.findViewById(R.id.operatValue);
        date = (TextView) itemView.findViewById(R.id.operatDate);
        //bill_name = (TextView) itemView.findViewById(R.id.operatBillName);
    }

    @Override
    public void onClick(View v) {

    }
}
