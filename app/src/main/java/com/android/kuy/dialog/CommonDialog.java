package com.android.kuy.dialog;

import com.android.kuy.base.BaseMaterialDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version CommonDialog, v 0.1 2019-09-10 04:06 by Abraham Ginting
 */
public class CommonDialog extends BaseMaterialDialog<CommonDialog.Builder> {

    public CommonDialog(Context context,
        DialogInterface.OnDismissListener dismissListener, int resource,
        Builder data) {
        super(context, dismissListener, resource, data);
    }

    @Override
    protected void setup(View dialogView, Builder datas) {

    }

    public class Builder {

    }
}
