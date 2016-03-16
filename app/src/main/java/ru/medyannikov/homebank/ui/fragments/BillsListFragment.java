package ru.medyannikov.homebank.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.adapters.BillAdapter;

/**
 * Created by Vladimir on 07.03.2016.
 */
public class BillsListFragment extends Fragment {
    @Bind(R.id.bills_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.bills_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<Bill> billList = new ArrayList<>();
    private static BillsListFragment fragment;

    BillAdapter adapter;

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
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BillAdapter(billList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBill();
            }
        });



        return view;
    }

    private void refreshBill() {
        Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
        billList.addAll(DataManager.getAllBills());
        adapter.notifyItemInserted(billList.size());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
