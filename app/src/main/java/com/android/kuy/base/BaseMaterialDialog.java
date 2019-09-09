package com.android.kuy.base;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.kuy.R;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseMaterialDialog, v 0.1 2019-09-10 03:22 by Abraham Ginting
 */
public abstract class BaseMaterialDialog<E> {

    private Activity activity;

    private Context context;

    private MaterialDialog materialDialog;

    private MaterialDialog.Builder materialDialogBuilder;

    private Unbinder unbinder;

    public BaseMaterialDialog(Context context, DialogInterface.OnDismissListener dismissListener,
        @LayoutRes int resource, E data) {
        View dialogView = View.inflate(context, resource, null);
        this.context = context;
        activity = (context instanceof Activity) ? (Activity) context : null;

        materialDialogBuilder = new MaterialDialog.Builder(context)
            .dismissListener(dismissListener)
            .customView(dialogView, false)
            .positiveColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        boolean isInstanceCancellation = data instanceof Cancellation;

        if (isInstanceCancellation) {
            Cancellation cancellation = (Cancellation) data;
            materialDialogBuilder = materialDialogBuilder.cancelable(cancellation.cancelable)
                .canceledOnTouchOutside(cancellation.cancelOutside);
        }

        materialDialog = materialDialogBuilder.build();
        setBackground();
        bindView(dialogView);
        setup(dialogView, data);
    }

    public Context getContext() {
        return context;
    }

    private void setBackground() {
        Window window = materialDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void bindView(View view) {
        ButterKnife.bind(view);
        unbinder = ButterKnife.bind(this, view);
    }

    protected abstract void setup(View dialogView, E datas);

    public MaterialDialog.Builder getMaterialDialogBuilder() {
        return materialDialogBuilder;
    }

    public void showDialog() {
        if (materialDialog == null) {
            build();
        }
        if (!materialDialog.isShowing()) {
            materialDialog.show();
        }
    }

    public MaterialDialog build() {
        materialDialog = materialDialogBuilder.build();
        return materialDialog;
    }

    public void dismissDialog() {
        if (activity != null && !activity.isFinishing() && materialDialog.isShowing()) {
            materialDialog.dismiss();
            unbinder.unbind();
        }
    }

    public boolean isShowing() {
        return materialDialog != null && materialDialog.isShowing();
    }


    public MaterialDialog getMaterialDialog() {
        return materialDialog;
    }

    public static class Cancellation {

        private boolean cancelOutside = true;

        private boolean cancelable = true;

        protected void setCancelOutsideValue(boolean cancelOutside) {
            this.cancelOutside = cancelOutside;
        }

        protected void setCancelableValue(boolean cancelable) {
            this.cancelable = cancelable;
        }
    }

}
