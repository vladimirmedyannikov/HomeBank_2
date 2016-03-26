package ru.medyannikov.homebank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.data.managers.DataManager;

import ru.medyannikov.homebank.ui.fragments.BillsListFragment;
import ru.medyannikov.homebank.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT = "current_fragment";

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.navigation_drawer)
    DrawerLayout mNavigationDrawer;

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DataManager.auth();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testRest();
                DataManager.auth();
//               Snackbar.make(view, AndroidApplication.getUser().getEmail(), Snackbar.LENGTH_LONG)
 //                      .setAction("Action", null).show();
            }
        });
        initNavigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ProfileFragment.getInstance(), TAG_FRAGMENT).addToBackStack(null).commit();
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment, TAG_FRAGMENT).addToBackStack(null).commit();
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
