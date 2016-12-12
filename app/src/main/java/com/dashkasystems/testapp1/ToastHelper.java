package com.dashkasystems.testapp1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pandasystems on 10/30/16.
 */
public class ToastHelper {

    public static void showToast(Context context, boolean success) {
        int bgColor = success ? Color.GREEN : Color.RED;
        int resId = success ? R.string.toast_message_success : R.string.toast_message_fail;

        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        View v = toast.getView();
        v.setBackgroundColor(bgColor);
        toast.show();
    }
}
