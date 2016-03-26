package ru.medyannikov.homebank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.adapters.OperationAdapter;

/**
 * Created by Vladimir on 20.03.2016.
 */
public class OperationActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.recycleViewOperation)
    RecyclerView mRecyclerView;

    @Bind(R.id.fabOperation)
    FloatingActionButton mFloatingActionButton;
    OperationAdapter adapter;
    private List<Operation> listOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        ButterKnife.bind(this);
        mFloatingActionButton.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshAdapter();
        adapter = new OperationAdapter(listOperation);
        mRecyclerView.setAdapter(adapter);

    }

    private void refreshAdapter() {
        listOperation = DataManager.getOperations(DataManager.getAllBills().get(0));
        //adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {

    }
}
