package ru.medyannikov.homebank.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.medyannikov.homebank.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        DataManager.getBus().register(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO activity build
    }

    /**
     * Инициализация ToolBar
     */
    private void setupToolBar(){
        //setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        }
        setTitle("Lesson 1");
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @OnClick(R.id.sign_in)
    public void signIn(){
        String login = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        DataManager.signIn(login, password);
    }

    @Subscribe
    public void loginUser(LoginSuccessEvent event){
        SharedPreferences.Editor editor = AndroidApplication.getSharedPreferences().edit();
        editor.putString(getString(R.string.token_key), event.getTokenModel().getAccessToken()).apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
