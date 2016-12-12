package com.dashkasystems.testapp1;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by pandasystems on 11/27/16.
 */
public class ScreenHelper {

    public static float getPXHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;// / displayMetrics.density;
    }

    public static float getPXWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels ;/// displayMetrics.density;
    }
}
