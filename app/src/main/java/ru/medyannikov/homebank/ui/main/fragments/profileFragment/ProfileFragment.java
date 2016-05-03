package ru.medyannikov.homebank.ui.main.fragments.profileFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.ui.base.BaseFragment;
import ru.medyannikov.homebank.ui.main.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Account;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class ProfileFragment extends BaseFragment implements ProfileView{
    //private  ProfileFragment fragment;

    @Bind(R.id.textview_phone_value)
    TextView phone_value;
    @Bind(R.id.textview_email_value)
    TextView email_value;
    @Bind(R.id.textview_vk_value)
    TextView vk_value;
    @Bind(R.id.textview_about_value)
    TextView about_value;
    @Bind(R.id.textview_firstname_value)
    TextView firstName;

    @Inject
    ProfilePresenter profilePresenter;

    private FloatingActionButton mFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        ButterKnife.bind(this, view);
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        getActivity().setTitle(getResources().getString(R.string.drawer_menu_profile));
        ((MainActivity)getActivity()).getComponent().inject(this);
        //((MainActivity)getActivity()).lockAppBar(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePresenter.init(this);
        prepareFragment();
        profilePresenter.showProfile();
    }

    private void prepareFragment() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.fragment_container);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT;
        mFloatingActionButton.setImageResource(R.drawable.ic_assignment_24dp);
        mFloatingActionButton.setLayoutParams(params);
        ((MainActivity)getActivity()).setTitle("Профиль");
    }

    public static Fragment getInstance() {
        /*if (fragment == null){
            fragment = new ProfileFragment();
        }*/
        return new ProfileFragment();
    }

    @Override
    public void onUpdateData(Account user) {
        if (user != null) {
            firstName.setText(user.getFullName());
            phone_value.setText(user.getPhone());
            email_value.setText(user.getEmail());
            vk_value.setText(user.getUrlVk());
            about_value.setText(user.getAbout());
        }
    }
}