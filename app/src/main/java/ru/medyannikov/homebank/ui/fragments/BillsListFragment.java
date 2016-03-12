package ru.medyannikov.homebank.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.medyannikov.homebank.R;

/**
 * Created by Vladimir on 07.03.2016.
 */
public class BillsListFragment extends Fragment {

    private static BillsListFragment fragment;

    public static BillsListFragment getInstance(){
        if (fragment == null){
            fragment = new BillsListFragment();
        }
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills_list,null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
