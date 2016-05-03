package ru.medyannikov.homebank.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.gcm.RegistrationIntentService;
import ru.medyannikov.homebank.data.managers.DataManager;

import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.ui.base.BaseActivity;
import ru.medyannikov.homebank.ui.main.fragments.billListFragment.BillsListFragment;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfileFragment;
import ru.medyannikov.homebank.utils.PlayService;

public class MainActivity extends BaseActivity implements MainActivityView{
    private static final String TAG_FRAGMENT = "current_fragment";

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.navigation_drawer)
    DrawerLayout mNavigationDrawer;

    /*@Inject
    SharedPreferences sharedPreferences;*/

    @Inject MainActivityPresenter presenter;

    private Fragment mFragment;
    private Toolbar toolbar;
    private boolean isReceiverRegistred;
    private BroadcastReceiver mRegBroadcastReceiver;

    private MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerMainActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);
        presenter.onCreate();
        //AndroidApplication.component().inject(this);
        setupToolBar();

        mRegBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean token = false;//sharedPreferences.getBoolean(RegistrationIntentService.SENT_TOKEN_TO_SERVER, false);
                if (token){
                    Log.d("token", "token registred");
                }
                else Log.d("token", "token error registred");
            }
        };

        registerReceiver();

        if (PlayService.checkPlayServices(this)){
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testRest();
                DataManager.getInstance().auth();
//               Snackbar.make(view, AndroidApplication.getUser().getEmail(), Snackbar.LENGTH_LONG)
                //                      .setAction("Action", null).show();
            }
        });
        initNavigationDrawer();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ProfileFragment.getInstance(), TAG_FRAGMENT).addToBackStack(null).commit();
    }

    public MainActivityComponent getComponent(){
        return component;
    }

    private void registerReceiver() {
        if(!isReceiverRegistred){
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegBroadcastReceiver,
                    new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
            isReceiverRegistred = true;
        }
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegBroadcastReceiver);
        isReceiverRegistred = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void setupToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        }
        setTitle("");
    }

    private void initNavigationDrawer(){
        if (mNavigationView != null){
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.profile_menu:
                            mFragment = ProfileFragment.getInstance();
                            mNavigationView.getMenu().findItem(R.id.profile_menu).setChecked(true);
                            break;
                        case R.id.bills_menu:
                            mFragment = BillsListFragment.getInstance();
                            mNavigationView.getMenu().findItem(R.id.bills_menu).setChecked(true);
                            break;
                    }
                    if (mFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment, TAG_FRAGMENT).commit();
                    }
                    mNavigationDrawer.closeDrawers();
                    return false;
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment current = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (current instanceof ProfileFragment){
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else if (current instanceof BillsListFragment){

        }
        super.onBackPressed();
        //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupComponent(AndroidApplicationComponent component) {

    }

    @Override
    public void showMessage() {

    }

    @Override
    public void openProfile() {

    }

    @Override
    public void openListBills() {

    }
}
