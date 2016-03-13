package ru.medyannikov.homebank.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.medyannikov.homebank.MainActivity;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.UserModel;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 13.03.2016.
 */
public class ProfileFragment extends Fragment {
    private static ProfileFragment fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        getActivity().setTitle(getResources().getString(R.string.drawer_menu_profile));


        //((MainActivity)getActivity()).lockAppBar(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserModel user = AndroidApplication.getUser();
        ((MainActivity)getActivity()).setTitle(user.getFirstName());

    }

    public static Fragment getInstance() {
        if (fragment == null){
            fragment = new ProfileFragment();
        }
        return fragment;
    }
}
