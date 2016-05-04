package ru.medyannikov.homebank.ui.operationList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragmentPresenter;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragmentPresenterImpl;


/**
 * Created by vladimir on 04.05.16.
 */
@Module
public class OperationModule {
    protected OperationView view;

    public OperationModule(OperationView view){
        this.view = view;
    }

    @Provides
    @OperationScope
    OperationView provideOperationView(){
        return view;
    }

    @Provides
    @Singleton
    OperationPresenter provideOperationPresenter(OperationView view){
        return new OperationPresenterImpl(view);
    }

    @Provides
    @Singleton
    OperationListFragmentPresenter provideOperationListPresenter(){
        return new OperationListFragmentPresenterImpl();
    }
}
