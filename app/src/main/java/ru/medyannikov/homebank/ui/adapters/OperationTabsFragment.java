package ru.medyannikov.homebank.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.fragments.OperationListFragment;

/**
 * Created by Vladimir on 27.03.2016.
 */
public class OperationTabsFragment extends FragmentPagerAdapter {
    private String[] tabs;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Bill bill;

    public OperationTabsFragment(FragmentManager fm, Context context) {
        super(fm);
        tabs = context.getResources().getStringArray(R.array.operation_tab);
    }

    public OperationTabsFragment(Bill bill, FragmentManager fm, Context context) {
        super(fm);
        tabs = context.getResources().getStringArray(R.array.operation_tab);
        this.bill = bill;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragment = OperationListFragment.getInstance(bill);
                break;
            case 1:
                fragment = new Fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
