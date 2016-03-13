package ru.medyannikov.homebank.data.network.rest;

import java.util.List;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import ru.medyannikov.homebank.data.network.rest.models.Bill;
import ru.medyannikov.homebank.data.storage.models.TokenModel;
import ru.medyannikov.homebank.data.storage.models.UserModel;


/**
 * Created by Vladimir on 07.03.2016.
 */
public interface RestService {
    String BASE_URL = "http://mangyst.ddns.net:3101";

    @GET("/mysql/bills/list")
    void  billList(Callback<List<Bill>> res);

    /*@GET("/mysql/bills/list/{limit}/{offset}")
    void  billList(@Path("limit") int limit, @Path("offset") int offset, Callback<List<Bill>> res);

    @GET("/mysql/bills/{id}")
    Bill getBill(@Path("id") int id);*/

    //@Headers({"Autorization : Bearer OPkJmTsbzKQp/vbpIdHZBNkhmTCnW8nSUiZ4/sk/+00="})
    @GET("/test")
    void getUserModel(Callback<UserModel> callback);

    @POST("/mysql/signup")
    UserModel signUp();

    @FormUrlEncoded
    @POST("/oauth/token")
    void signIn(@Field("grant_type") String grantType, @Field("client_id") String clientId,
                      @Field("client_secret") String clientSecret, @Field("username") String username,
                      @Field("password") String password, Callback<TokenModel> callback);

}
