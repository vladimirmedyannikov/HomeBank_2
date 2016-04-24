package ru.medyannikov.homebank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Bill;
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

    private long billId;
    private Toolbar toolbar;
    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        billId = getIntent().getLongExtra(Bill.BILL_EXTRA, 0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        ButterKnife.bind(this);

        bill = DataManager.getBill(billId);
        OperationTabsFragment pager = new OperationTabsFragment(bill, getSupportFragmentManager(),this);
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {

    }
}
