package ru.medyannikov.homebank.ui.operationList;

import javax.inject.Singleton;

import dagger.Component;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragment;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragmentPresenter;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragmentView;

/**
 * Created by vladimir on 04.05.16.
 */
@OperationScope
@Singleton
@Component(
        modules = {
                OperationModule.class,
                AndroidApplicationModule.class
        }
)
public interface OperationComponent {
    void inject(OperationView view);
    void inject(OperationActivity view);
    void inject(OperationListFragment view);
    OperationPresenter operationPresenter();
    OperationListFragmentPresenter operationListPresenter();
}
