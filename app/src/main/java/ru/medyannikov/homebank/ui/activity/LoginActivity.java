package ru.medyannikov.homebank.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.medyannikov.homebank.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.LoginFailedEvent;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
import ru.medyannikov.homebank.data.managers.events.NetworkStatusError;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_password)
    EditText mPasswordField;

    @Bind(R.id.login_email)
    EditText mEmailField;

    @Bind(R.id.sign_in)
    Button mBntSingIn;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        DataManager.getBus().register(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       if (DataManager.isLogged()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //TODO activity build
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    @OnClick(R.id.sign_in)
    public void signIn(){
        dialog = new ProgressDialog(this);
        dialog.setTitle("Запрос");
        dialog.setMessage("Аутентификация");
        dialog.setCancelable(false);
        String login = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        DataManager.signIn(login, password);
        dialog.show();
    }

    @Subscribe
    public void loginUser(LoginSuccessEvent event){
        dialog.dismiss();
        DataManager.setToken(event.getTokenModel().getAccessToken());
        DataManager.auth();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void loginFailed(LoginFailedEvent event){
        Toast.makeText(this, "Произошла ошибка авторизации", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Subscribe
    public void errorNetwork(NetworkStatusError error){
        Toast.makeText(AndroidApplication.getContext(), "Error connetcion internet", Toast.LENGTH_SHORT).show();
    }
}
