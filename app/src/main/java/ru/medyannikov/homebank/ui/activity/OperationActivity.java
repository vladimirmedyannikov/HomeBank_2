package ru.medyannikov.homebank.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.adapters.OperationAdapter;
import ru.medyannikov.homebank.ui.adapters.OperationTabsFragment;

/**
 * Created by Vladimir on 20.03.2016.
 */
public class OperationActivity extends AppCompatActivity implements View.OnClickListener {
    private static Fragment instance;
    private FloatingActionButton mFloatingActionButton;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    OperationAdapter adapter;
    private List<Operation> listOperation;
    private Bill bill;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        bill = (Bill) getIntent().getSerializableExtra(Bill.BILL_EXTRA);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        OperationTabsFragment pager = new OperationTabsFragment(getSupportFragmentManager(),this);
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void refreshAdapter() {
        listOperation = DataManager.getOperations(bill);
        //adapter.notifyDataSetChanged();
    }

   /* public static Fragment getInstance(){
        if (instance == null)
        {
            instance = new OperationActivity();
        }
        return instance;
    }*/



    @Override
    public void onClick(View v) {

    }
}
