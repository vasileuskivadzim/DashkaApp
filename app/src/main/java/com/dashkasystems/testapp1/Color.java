package com.dashkasystems.testapp1;

import android.support.annotation.ColorRes;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Color {
    public @ColorRes int color;
    public String name;

    public Color(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }

    public static Color[] all() {
        Color[] all = new Color[6];
        all[0] = red();
        all[1] = orange();
        all[2] = yellow();
        all[3] = green();
        all[4] = blue();
        all[5] = darkBlue();
        return all;
    }

    public static Color red() {
        return new Color(R.color.red, "красный");
    }

    public static Color orange() {
        return new Color(R.color.orange, "оранжевый");
    }

    public static Color yellow() {
        return new Color(R.color.yellow, "жёлтый");
    }

    public static Color green() {
        return new Color(R.color.green, "зелёный");
    }

    public static Color blue() {
        return new Color(R.color.blue, "голубой");
    }

    public static Color darkBlue() {
        return new Color(R.color.darkBlue, "синий");
    }

    public static Color white() {
        return new Color(R.color.white, "белый");
    }
}
