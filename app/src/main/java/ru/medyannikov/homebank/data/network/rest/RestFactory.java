package ru.medyannikov.homebank.data.network.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class RestFactory {

    private static OkHttpClient CLIENT = new OkHttpClient();

    public static RestAdapter getRetrofit(String base_url, final String token){
        RequestInterceptor requestInterceptor;
        RestAdapter.Builder restAdapter = new RestAdapter.Builder();
        restAdapter.setEndpoint(base_url);
        restAdapter.setClient(new OkClient(CLIENT));
        if (token != null){
            requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", "Bearer " + token);
                }
            };
            restAdapter.setRequestInterceptor(requestInterceptor);
        }
        return restAdapter.build();
    }

    public static RestService getRestService(){
        return getRetrofit(RestService.BASE_URL, AndroidApplication.getToken()).create(RestService.class);
    }

}
