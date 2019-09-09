package com.android.kuy.base;

import com.android.kuy.di.component.ApplicationComponent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseFragment, v 0.1 2019-09-09 02:15 by Abraham Ginting
 */
public abstract class BaseFragment extends Fragment implements DisposableHandler, PresenterHandler{

    private BaseActivity baseActivity;

    private CompositeDisposable disposables;

    private FragmentListener fragmentListener;

    private List<AbstractContract.AbstractPresenter> presenterList;

    private Unbinder unbinder;

    private MultipleClickHandler multipleClickHandler;

    /**
     * get {@link ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        if (getBaseActivity() != null) {
            return getBaseActivity().getApplicationComponent();
        }
        return null;
    }

    /**
     * get {@link BaseActivity}
     */
    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }

        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

    @Override
    public void dispose() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void registerPresenter(AbstractContract.AbstractPresenter... presenters) {
        if (presenterList == null) {
            presenterList = new ArrayList<>();
        }
        if (presenters != null && presenters.length > 0) {
            presenterList.addAll(Arrays.asList(presenters));
        }
    }

    @Override
    public void disposePresenter() {
        if (presenterList != null) {
            for (AbstractContract.AbstractPresenter presenter : presenterList) {
                if (presenter != null) {
                    presenter.onDestroy();
                }
            }
            presenterList = null;
        }
    }

    @CallSuper
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isViewBinded()) {
            return;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof BaseActivity) {
            baseActivity = (BaseActivity) getActivity();
            fragmentListener = (FragmentListener) context;
            multipleClickHandler = baseActivity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayout() != 0) {
            view = inflater.inflate(getLayout(), container, false);
            unbinder = ButterKnife.bind(this, view);
            return view;
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract int getLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected abstract void init();

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        dispose();
        disposePresenter();
        super.onDestroyView();
    }

    public boolean isViewBinded() {
        return unbinder != null;
    }

    protected void enableClick() {
        if (multipleClickHandler != null) {
            multipleClickHandler.enableClick();
        }
    }

    protected void disableClick() {
        if (multipleClickHandler != null) {
            multipleClickHandler.disableClick();
        }
    }

    protected boolean isClickable() {
        if (multipleClickHandler != null) {
            return multipleClickHandler.isClickable();
        }

        return true;
    }

    protected void activityDoAction(String actionKey, Object data) {
        if (fragmentListener != null) {
            fragmentListener.doAction(actionKey, data);
        }
    }
}
