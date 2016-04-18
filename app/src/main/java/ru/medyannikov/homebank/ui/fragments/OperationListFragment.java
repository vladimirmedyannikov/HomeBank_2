package ru.medyannikov.homebank.ui.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationFetchEvent;
import ru.medyannikov.homebank.data.managers.events.OperationInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.adapters.BillAdapter;
import ru.medyannikov.homebank.ui.adapters.OperationAdapter;

/**
 * Created by Vladimir on 02.04.2016.
 */
public class OperationListFragment extends Fragment implements View.OnClickListener {
    private static Fragment instance;
    private FloatingActionButton mFloatingActionButton;
    private OperationAdapter adapter;
    private List<Operation> operationList = new ArrayList<>();
    private Bill bill;

    @Bind(R.id.operation_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.operation_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    public static Fragment getInstance(Bill bill){
        if (instance == null){
            instance = new OperationListFragment();
        }
        ((OperationListFragment)instance).setBill(bill);
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
        DataManager.getBus().register(this);
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_operation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Operation operation = adapter.getOperation(position);
                operation.delete();
                adapter.notifyItemRemoved(position);
                operationList.remove(operation);
                operation.getBill().calcucateSumm();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataManager.fetchOperationAsync(bill);
            }
        });
        return view;
    }

    private void initAdapter() {
        operationList = DataManager.getOperations(bill);
        adapter = new OperationAdapter(operationList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_operation_add, null, false);
        final Spinner spinnerBills = (Spinner) view.findViewById(R.id.editSpinerBill);
        //Spinner spinnerType = (Spinner) view.findViewById(R.id.operat_type);

        final EditText editSumm = (EditText) view.findViewById(R.id.operat_value);
        final EditText editAbout = (EditText) view.findViewById(R.id.operat_about);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),R.layout.support_simple_spinner_dropdown_item,new String[]{"", "Lol"});
        spinnerType.setAdapter(adapter);
        spinnerType.setVisibility(View.GONE);*/

        if (getBill() == null){
            spinnerBills.setVisibility(View.VISIBLE);
            ArrayAdapter adapterBill = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item, DataManager.getAllBills());
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
                        operation.setAccount(DataManager.getAccount());
                        if (bill == null) {
                            bill = (Bill) (spinnerBills.getSelectedItem());
                        }
                        operation.setBill(bill);
                        DataManager.sendOperationAsync(operation);
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


    public void refresh(@Nullable final List<Operation> list) {
        new AsyncTask<Void, Void, List<Operation>>() {
            @Override
            protected List<Operation> doInBackground(Void... params) {
                operationList = list;
                return operationList;
            }

            @Override
            protected void onPostExecute(List<Operation> list) {
                adapter = new OperationAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        }.execute();
    }

    @Subscribe
    public void onEvent(OperationFetchEvent event){
        refresh(event.getItems());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe
    public void onEvent(OperationInsertEvent event){
        new AsyncTask<Void, Void, List<Operation>>() {
            @Override
            protected List<Operation> doInBackground(Void... params) {
                operationList = DataManager.getOperations(bill);
                return operationList;
            }

            @Override
            protected void onPostExecute(List<Operation> list) {
                adapter = new OperationAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onDestroyView() {
        DataManager.getBus().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initAdapter();
    }
}
