package ru.medyannikov.homebank.ui.operationList.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationFetchEvent;
import ru.medyannikov.homebank.data.managers.events.OperationInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.adapters.OperationAdapter;
import ru.medyannikov.homebank.ui.base.BaseFragment;
import ru.medyannikov.homebank.ui.operationList.OperationActivity;
import ru.medyannikov.homebank.ui.operationList.OperationPresenter;

/**
 * Created by Vladimir on 02.04.2016.
 */
public class OperationListFragment extends BaseFragment implements View.OnClickListener, OperationListFragmentView {
    private FloatingActionButton mFloatingActionButton;
    private OperationAdapter adapter;
    private Bill bill;

    @Bind(R.id.operation_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.operation_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    OperationListFragmentPresenter presenter;

    public static Fragment getInstance(Bill bill){
        OperationListFragment instance = new OperationListFragment();
        instance.setBill(bill);
        return instance;
    }

    public static Fragment getInstance(){
        return getInstance(null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_list,null, false);
        ButterKnife.bind(this, view);
        ((OperationActivity)getActivity()).getComponent().inject(this);
        presenter.attachView(this);
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_operation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.updateData(bill);
        initializeRecyclerView();
        return view;
    }

    private void initializeRecyclerView() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Operation operation = adapter.getOperation(position);
                presenter.deleteOperation(operation, position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateData(bill);
            }
        });
    }

    @Override
    public void onClick(View v) {
        dialogInsert();
    }

    private void dialogInsert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_operation_add, null, false);
        final Spinner spinnerBills = (Spinner) view.findViewById(R.id.editSpinerBill);
        //Spinner spinnerType = (Spinner) view.findViewById(R.id.operat_type);

        final EditText editSumm = (EditText) view.findViewById(R.id.operat_value);
        final EditText editAbout = (EditText) view.findViewById(R.id.operat_about);

        if (getBill() == null){
            spinnerBills.setVisibility(View.VISIBLE);
            ArrayAdapter adapterBill = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, DataManager.getInstance().getAllBills());
            spinnerBills.setAdapter(adapterBill);
        }

        builder.setTitle("Добавление новой операции")
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Operation operation = new Operation();
                        operation.setValue(Double.valueOf(editSumm.getText().toString()));
                        operation.setAbout(editAbout.getText().toString());
                        operation.setAccount(DataManager.getInstance().getAccount());
                        if (bill == null) {
                            bill = (Bill) (spinnerBills.getSelectedItem());
                        }
                        operation.setBill(bill);
                        presenter.insertOperation(operation);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeFab();
    }

    private void initializeFab() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.operation_recyclerview);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT |Gravity.END;
        mFloatingActionButton.setImageResource(R.drawable.ic_add_24dp);
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setOnClickListener(this);
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //initAdapter();
    }

    @Override
    public void setData(final List<Operation> listOperation) {
        new Runnable(){
            @Override
            public void run() {
                adapter = new OperationAdapter(listOperation);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }.run();
    }

    @Override
    public void operationDeleted(final int position) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.removeOperation(position);
            }
        });
    }

    @Override
    public void operationInserted(final Operation operation) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.insertOperation(operation);
            }
        });
    }
}
