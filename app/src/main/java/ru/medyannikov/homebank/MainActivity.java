package ru.medyannikov.homebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.medyannikov.homebank.data.network.rest.RestFactory;
import ru.medyannikov.homebank.data.network.rest.RestService;
import ru.medyannikov.homebank.data.network.rest.models.Bill;

import ru.medyannikov.homebank.data.storage.models.UserModel;
import ru.medyannikov.homebank.ui.AndroidApplication;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testRest();
                testRest3();
//               Snackbar.make(view, AndroidApplication.getUser().getEmail(), Snackbar.LENGTH_LONG)
 //                      .setAction("Action", null).show();
            }
        });
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

    public void testRest(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestService.BASE_URL)
                .build();
        RestService service = restAdapter.create(RestService.class);
        Callback<List<Bill>> a = new Callback<List<Bill>>() {
            @Override
            public void success(List<Bill> bills, Response response) {
                /*for(Bill bill:bills)
                {
                    bill.add(post);
                    adapter.notifyItemInserted(posts.size());
                }
                swipeRefreshLayout.setRefreshing(false);
                adapter.setLoaded();*/
                //result = 1;
                System.out.print(" ");
            }
            @Override
            public void failure(RetrofitError error) {
                //posts = new LinkedList<Posts>();
                //result = 0;
                //retrofitError = error;
                //swipeRefreshLayout.setRefreshing(false);
            }
        };
        service.billList(a);
    }

    public void testRest2(){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Bearer " + "OPkJmTsbzKQp/vbpIdHZBNkhmTCnW8nSUiZ4/sk/+00=");
                //request.addQueryParam("device_type", "Samsung S4");
            }
        };


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestService.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();
        RestService service = restAdapter.create(RestService.class);
        Callback<UserModel> a = new Callback<UserModel>() {
            @Override
            public void success(UserModel user, Response response) {
                if (response.getStatus() == 200){

                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("Retrofit error ","Url "+ error.getUrl() + " \n " + error.getBody().toString());
            }
        };
        service.getUserModel(a);
    }

    public void testRest3(){
        RestService service = RestFactory.getRestService();
        Callback<UserModel> a = new Callback<UserModel>() {
            @Override
            public void success(UserModel user, Response response) {
                if (response.getStatus() == 200){
                    AndroidApplication.setUser(user);
                }
                Toast.makeText(getApplication(),"lol",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void failure(RetrofitError error) {
               // Log.e("Retrofit error ","Url "+ error.getUrl() + " \n " + error.getBody().toString());
                Log.e("errr", " ");
                error.printStackTrace();
                Toast.makeText(getApplication(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        service.getUserModel(a);
    }



}
