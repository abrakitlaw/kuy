package com.android.kuy.base;

import com.android.kuy.KuyApplication;
import com.android.kuy.R;
import com.android.kuy.di.component.ApplicationComponent;
import com.android.kuy.di.module.ActivityModule;
import com.android.kuy.utils.OSUtil;
import com.android.kuy.utils.SizeUtil;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseActivity, v 0.1 2019-09-08 04:40 by Abraham Ginting
 */
public abstract class BaseActivity extends AppCompatActivity implements DisposableHandler,
    PresenterHandler, MultipleClickHandler, FragmentListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @BindView(R.id.ic_image_fg)
    ImageView icImageFg;

    @Nullable
    @BindView(R.id.left_button)
    TextView leftButton;

    @Nullable
    @BindView(R.id.right_button)
    TextView rightButton;

    @Nullable
    @BindView(R.id.loader_wrapper)
    RelativeLayout rightProgressBar;

    @Nullable
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.iv_title_image)
    ImageView toolbarImage;

    @Nullable
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    private CompositeDisposable disposables;

    private List<AbstractContract.AbstractPresenter> presenterList;

    private Unbinder unbinder;

    private boolean clickable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        dispose();
        disposePresenter();
        super.onDestroy();
    }

    /**
     * {@link ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return ((KuyApplication) getApplication()).getApplicationComponent();
    }

    public abstract int getLayout();

    public abstract void configToolbar();

    public abstract void init();

    public void init(Bundle savedInstanceState) {
        init();
    }

    protected void setTitle(String title) {
        //toolbarTitle.setText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableClick();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void setTitle(String title, @DrawableRes int drawableLeft,
        @DrawableRes int drawableTop, @DrawableRes int drawableBottom,
        @DrawableRes int drawablrRight) {
        // toolbarTitle.setText(title);
        // toolbarTitle.setCompoundDrawableWithInstrinsicBounds(drawbleLeft, drawableTop,
        // drawableRight. drawableBottom);
    }

    protected void setCenterTitle(String title, int textColor) {
        setCenterTitle(title);
        toolbarTitle.setTextColor(textColor);
    }

    protected void setCenterTitle(String title) {
        setTitle(title);
    }

    protected void setToolbarImage(@DrawableRes int drawableRes) {
        toolbarTitle.setVisibility(View.GONE);
        toolbarImage.setVisibility(View.VISIBLE);
        toolbarImage.setImageDrawable(getDrawableFromRes(drawableRes));
    }

    public Drawable getDrawableFromRes(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(this, drawableRes);
    }

    /**
     * set toolbar Menu LeftButton
     */
    public void setMenuLeftButton(String leftButtonText) {
        if (leftButton != null) {
            setMarginLeft(10);
            leftButton.setText(leftButtonText);
            if (leftButtonText != null && !leftButtonText.isEmpty()) {
                leftButton.setVisibility(View.VISIBLE);
            } else {
                leftButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * set toolbar Menu LeftButton enabled
     */
    public void setMenuLeftButtonEnabled(boolean isEnabled) {
        if (leftButton != null) {
            leftButton.setEnabled(isEnabled);
        }
    }

    /**
     * set toolbar Menu RightButton
     */
    public void setMenuRightButton(String rightButtonText) {
        if (rightButton != null) {
            setMarginRight(10);
            rightButton.setText(rightButtonText);
            if (rightButtonText != null && !rightButtonText.isEmpty()) {
                rightButton.setVisibility(View.VISIBLE);
            } else {
                rightButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * set toolbar Menu RightButton enabled
     */
    public void setMenuRightButtonEnabled(boolean isEnabled) {
        if (rightButton != null) {
            rightButton.setEnabled(isEnabled);
        }
    }

    public void setMenuRightProgressBar(boolean isProgress) {
        setMenuRightButtonEnabled(!isProgress);
        if (rightButton != null) {
            rightButton.setVisibility(isProgress ? View.GONE : View.VISIBLE);
        }
        if (rightProgressBar != null) {
            rightProgressBar.setVisibility(isProgress ? View.VISIBLE : View.GONE);
            animateProgress();
        }
    }

    /**
     * set left menu using icon
     */
    public void setMenuLeftButton(@DrawableRes int icon) {
        if (toolbar != null && icon != 0) {
            toolbar.setNavigationIcon(icon);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            leftButton.setVisibility(View.INVISIBLE);
        }
    }

    private void animateProgress() {
        Animation refreshAnimation = AnimationUtils
            .loadAnimation(getBaseContext(), R.anim.rotate_counter_clockwise);
        refreshAnimation.setInterpolator(new LinearInterpolator());
        icImageFg.startAnimation(refreshAnimation);
    }

    /**
     * set margin right toolbar
     */
    private void setMarginRight(int marginRight) {
        RelativeLayout.MarginLayoutParams relativeParams = getToolbarParam();
        relativeParams.setMargins(relativeParams.leftMargin, relativeParams.topMargin,
            SizeUtil.convertToPx(marginRight), relativeParams.bottomMargin);
        rlToolbar.setLayoutParams(relativeParams);
    }

    /**
     * set margin left toolbar
     */
    private void setMarginLeft(int marginLeft) {
        RelativeLayout.MarginLayoutParams relativeParams = getToolbarParam();
        relativeParams.setMargins(SizeUtil.convertToPx(marginLeft), relativeParams.topMargin,
            relativeParams.rightMargin, relativeParams.bottomMargin);
        rlToolbar.setLayoutParams(relativeParams);
    }

    /**
     * get margin param of toolbar
     */
    private RelativeLayout.MarginLayoutParams getToolbarParam() {
        return (RelativeLayout.MarginLayoutParams) rlToolbar.getLayoutParams();
    }

    /**
     * set right menu using icon
     */
    public void setMenuRightButton(@DrawableRes int icon) {
        if (rightButton != null && icon != 0) {
            rightButton.setBackground(getDrawableFromRes(icon));
            rightButton.setVisibility(View.VISIBLE);
        }
    }

    @Optional
    @OnClick(R.id.left_button)
    public void onClickLeftMenuButton(View view) {
    }

    @Optional
    @OnClick(R.id.right_button)
    public void onClickRightMenuButton(View view) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onHomeIndicatorClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onHomeIndicatorClick() {
        super.onBackPressed();
    }

    @Optional
    @OnClick(R.id.toolbar_title)
    public void OnClickTitle(View view) {
    }

    /**
     * getStatusBarHeight
     */
    private int getStatusBarHeight() {
        int result = 0;
        if (OSUtil.isKitkatAndAbove()) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
            disposables = null;
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
    public void doAction(String actionKey, Object data) {
        //no implementation in base
    }

    public void showWarningDialog(String message) {
        showWarningDialog(message, null);
    }

    public void showWarningDialog(String message, DialogInterface.OnDismissListener listener) {
        showWarningDialog(message, listener, true, 0);
    }

    public void showWarningDialog(String message,
        DialogInterface.OnDismissListener dismissListener, boolean autoDismiss, int delay) {

    }

    public void showWarningDialog(String message, DialogInterface.OnDismissListener dismissListener,
        boolean autoDismiss) {
        showWarningDialog(message, dismissListener, autoDismiss, 0);
    }

    public void showWarningDialog(String message, DialogInterface.OnDismissListener listener,
        int delay) {
        showWarningDialog(message, listener, true, delay);
    }

    public void showSuccessDialog(String message,
        DialogInterface.OnDismissListener dismissListener) {
    }

    @Override
    public void enableClick() {
        clickable = true;
    }

    @Override
    public void disableClick() {
        clickable = false;
    }

    @Override
    public boolean isClickable() {
        return clickable;
    }
}
