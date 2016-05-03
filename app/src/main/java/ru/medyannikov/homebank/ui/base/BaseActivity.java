package ru.medyannikov.homebank.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 27.04.16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setupComponent(AndroidApplication.component());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void inject(BaseFragment fragment){

    }

    public abstract int getLayoutId();
    public abstract void setupComponent(AndroidApplicationComponent component);
}
