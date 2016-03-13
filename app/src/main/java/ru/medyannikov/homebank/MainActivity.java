package ru.medyannikov.homebank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.medyannikov.homebank.data.network.rest.RestFactory;
import ru.medyannikov.homebank.data.network.rest.RestService;

import ru.medyannikov.homebank.data.storage.models.UserModel;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT = "current_fragment";

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        auth();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testRest();
                auth();
//               Snackbar.make(view, AndroidApplication.getUser().getEmail(), Snackbar.LENGTH_LONG)
 //                      .setAction("Action", null).show();
            }
        });
        initNavigationDrawer();
    }



    private void initNavigationDrawer(){
        if (mNavigationView != null){
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.profile_menu:
                            mFragment = ProfileFragment.getInstance();
                            break;
                    }
                    if (mFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment, TAG_FRAGMENT).addToBackStack(null).commit();
                    }
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void auth(){
        RestService service = RestFactory.getRestService();
        Callback<UserModel> a = new Callback<UserModel>() {
            @Override
            public void success(UserModel user, Response response) {
                if (response.getStatus() == 200){
                    AndroidApplication.setUser(user);
                } else if (response.getStatus() == 401){
                    //TODO not auth
                }
            }
            @Override
            public void failure(RetrofitError error) {
               //TODO error auth
                error.printStackTrace();
            }
        };
        service.getUserModel(a);
    }
}
