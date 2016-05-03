package ru.medyannikov.homebank.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.medyannikov.homebank.ui.main.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.ui.base.BaseActivity;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @Bind(R.id.login_password)
    EditText mPasswordField;

    @Bind(R.id.login_email)
    EditText mEmailField;

    @Bind(R.id.sign_in)
    Button mBntSingIn;

    @Inject LoginPresenter loginPresenter;


    private LoginActivityComponent component;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerLoginActivityComponent.builder()
                .loginActivityModule(new LoginActivityModule(this))
                .build();
        component.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        loginPresenter.onDestroy();
        super.onDestroy();
    }


    @OnClick(R.id.sign_in)
    public void signIn(){
        String login = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        loginPresenter.signIn(login,password);
    }

    @Subscribe
    public void loginUser(LoginSuccessEvent event){
        dialog.dismiss();
        DataManager.getInstance().setToken(event.getTokenModel().getAccessToken());
        DataManager.getInstance().auth();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_login;
    }

    @Override
    public void setupComponent(AndroidApplicationComponent component) {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void auth() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Запрос");
        dialog.setMessage("Аутентификация");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void hideProgress() {
        if (dialog != null){
            dialog.dismiss();
        }
    }
}
