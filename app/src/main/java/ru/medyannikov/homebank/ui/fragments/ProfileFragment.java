package ru.medyannikov.homebank.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.UserModel;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class ProfileFragment extends Fragment {
    private static ProfileFragment fragment;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getResources().getString(R.string.drawer_menu_profile));


        //((MainActivity)getActivity()).lockAppBar(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserModel user = AndroidApplication.getUser();
        ((MainActivity)getActivity()).setTitle("Профиль");

        firstName.setText(user.getFullName());
        phone_value.setText(user.getPhoneMobile());
        email_value.setText(user.getEmail());
        vk_value.setText(user.getUrlVk());
        about_value.setText(user.getAbout());

    }

    public static Fragment getInstance() {
        if (fragment == null){
            fragment = new ProfileFragment();
        }
        return fragment;
    }
}
