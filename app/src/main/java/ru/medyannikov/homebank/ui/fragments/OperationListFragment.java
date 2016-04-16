package ru.medyannikov.homebank.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.adapters.OperationAdapter;

/**
 * Created by Vladimir on 02.04.2016.
 */
public class OperationListFragment extends Fragment implements View.OnClickListener {
    private static Fragment instance;
    private FloatingActionButton mFloatingActionButton;
    private OperationAdapter adapter;
    private List<Operation> operationList = new ArrayList<>();

    @Bind(R.id.operation_recyclerview)
    RecyclerView recyclerView;

    public static Fragment getInstance(){
        if (instance == null){
            instance = new OperationListFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_list,null, false);
        ButterKnife.bind(this, view);
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_operation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter = new OperationAdapter(operationList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.operation_recyclerview);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT |Gravity.END;
        mFloatingActionButton.setImageResource(R.drawable.ic_add_24dp);
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setOnClickListener(this);*/

    }
}
