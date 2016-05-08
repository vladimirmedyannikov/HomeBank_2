package ru.medyannikov.homebank.ui.main.fragments.billListFragment;



import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillFetchEvent;
import ru.medyannikov.homebank.data.managers.events.BillInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.adapters.BillAdapter;
import ru.medyannikov.homebank.ui.main.MainActivity;

/**
 * Created by Vladimir on 07.03.2016.
 */
public class BillsListFragment extends Fragment implements View.OnClickListener, BillListView {
    @Bind(R.id.bills_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.bills_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    BillListPresenter presenter;

    private FloatingActionButton mFloatingActionButton;

    private List<Bill> billList = new ArrayList<>();
    private BillAdapter adapter;

    public static BillsListFragment getInstance(){
        /*if (fragment == null){
            fragment = new BillsListFragment();
        }*/
        return new BillsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("lo","createview");
        View view = inflater.inflate(R.layout.fragment_bills_list,null, false);
        ((MainActivity)getActivity()).getComponent().inject(this);
        presenter.attachView(this);
        ButterKnife.bind(this, view);

        getActivity().setTitle("Счета");
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BillAdapter(billList);
        recyclerView.setAdapter(adapter);
        presenter.updateBills();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateBills();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Bill bill = adapter.getBill(position);
                presenter.deleteBill(bill, position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.bills_recyclerview);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT |Gravity.END;
        mFloatingActionButton.setImageResource(R.drawable.ic_add_24dp);
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setOnClickListener(this);
        //getLoaderManager().initLoader(0,null,this);
    }
    /**
     * Fab listener
     * */
    @Override
    public void onClick(View v) {
        this.showInsertDialog();
    }


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }


    @Override
    public void updateData(final List<Bill> bills) {
        new Runnable(){
            @Override
            public void run() {
                billList = bills;
                adapter = new BillAdapter(billList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }.run();
    }

    @Override
    public void showInsertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_bill,null,false);
        final EditText nameBill = (EditText) view.findViewById(R.id.billName);
        final EditText aboutBill = (EditText) view.findViewById(R.id.billAbout);

        dialog.setTitle("Создание счета")
                .setView(view)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bill bill = new Bill();
                        bill.setName(nameBill.getText().toString());
                        bill.setAbout(aboutBill.getText().toString());
                        bill.setAccount(DataManager.getInstance().getAccount());
                        presenter.insertBill(bill);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        dialog.show();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void billDeleted(final int positionAdapter, Bill bill) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.remove(positionAdapter);
            }
        });
    }

    @Override
    public void billDeleted() {

    }

    @Override
    public void emptyData() {
        //adapter.notifyAll();
    }

    public void billInserted(final Bill bill){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.insert(bill);
            }
        });
    }
}
