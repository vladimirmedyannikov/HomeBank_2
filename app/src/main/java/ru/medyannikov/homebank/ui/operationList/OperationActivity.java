package ru.medyannikov.homebank.ui.operationList;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.ui.adapters.OperationTabsFragment;
import ru.medyannikov.homebank.ui.base.BaseActivity;

/**
 * Created by Vladimir on 20.03.2016.
 */
public class OperationActivity extends BaseActivity implements View.OnClickListener, OperationView {
    private static Fragment instance;
    private FloatingActionButton mFloatingActionButton;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private long billId;
    private Toolbar toolbar;
    private Bill bill;

    OperationComponent component;

    @Inject
    OperationPresenter presenter;

    public OperationComponent getComponent(){
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerOperationComponent.builder()
                .operationModule(new OperationModule(this)).build();
        component.inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter.onCreate();
    }

    public void initializeTabs() {
        setTitle("");
        billId = getIntent().getLongExtra(Bill.BILL_EXTRA, 0);
        bill = DataManager.getBill(billId);
        OperationTabsFragment pager = new OperationTabsFragment(bill, getSupportFragmentManager(),this);
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    public void setupComponent(AndroidApplicationComponent component) {

    }

    @Override
    public void onClick(View v) {

    }
}
