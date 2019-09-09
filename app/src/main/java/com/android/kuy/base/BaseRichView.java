package com.android.kuy.base;

import com.android.kuy.di.ComponentHolder;
import com.android.kuy.di.component.ApplicationComponent;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseRichView, v 0.1 2019-09-09 02:20 by Abraham Ginting
 */
public abstract class BaseRichView extends FrameLayout implements DisposableHandler, PresenterHandler {

    private CompositeDisposable disposables;

    private List<AbstractContract.AbstractPresenter> presenterList;

    private Unbinder unbinder;

    private MultipleClickHandler multipleClickHandler;

    private SingleClickListener singleClickListener;

    private View view;

    public BaseRichView(@NonNull Context context) {
        super(context);
    }

    public BaseRichView(@NonNull Context context,
        @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRichView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseRichView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr,
        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void init(Context context, AttributeSet attrs) {
        boolean isInjected = false;
        if (!isInEditMode()) {
            injectComponent(ComponentHolder.provide());
            isInjected = true;
        }

        view = View.inflate(context, getLayout(), this);
        onInjectedView(view);
        unbinder = ButterKnife.bind(this.view);
        parseAttrs(context, attrs);
        setup(isInEditMode());

        if (!isInEditMode()) {
            injected(isInjected);
        }
        multipleClickHandler = getBaseActivity();
        setSingleClick();
    }

    protected abstract void injected(boolean isInjected);

    public void setup(boolean inEditMode) {
        setup();
    }

    public abstract void setup();

    protected abstract void parseAttrs(Context context, AttributeSet attrs);

    protected void onInjectedView(View view) {
        //implementation to override
    }

    protected abstract int getLayout();

    private void injectComponent(ApplicationComponent applicationComponent) {
        //empty implementation
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this, view);
        }
    }

    public boolean isViewBinded() {
        return unbinder != null;
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
                presenter.onDestroy();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        dispose();
        disposePresenter();

        if (unbinder != null) {
            try {
                unbinder.unbind();
                unbinder = null;
            } catch (IllegalStateException illegalException) {
                Timber.e(illegalException);
            }
        }
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

    protected boolean isRichViewClickable() {
        if (multipleClickHandler != null) {
            return multipleClickHandler.isClickable();
        }

        return true;
    }

    protected SingleClickListener getSingleItemClickListener() {
        if (singleClickListener == null) {
            singleClickListener = new SingleClickListener(getBaseActivity()) {
                @Override
                public void singleClick(View v) {
                    onSingleClick(v);
                }
            };
        }

        return singleClickListener;
    }

    protected void setSingleClick() {
        //no implementation in base
    }

    protected void onSingleClick(View v) {
        //no implementation in base
    }

    protected void showWarningDialog(String message) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null && !TextUtils.isEmpty(message)) {
            baseActivity.showWarningDialog(message);
        }
    }

    protected BaseActivity getBaseActivity() {
        if (getContext() instanceof BaseActivity) {
            return (BaseActivity) getContext();
        }
        return null;
    }
}
