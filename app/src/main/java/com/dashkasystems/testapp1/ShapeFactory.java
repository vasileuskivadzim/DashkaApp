package com.dashkasystems.testapp1;

import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by pandasystems on 11/27/16.
 */
public class ShapeFactory {
    public static ShapeDrawable drawCircle (int width, int height, int color, int borderColor) {
        BorderedShape borderedOval = new BorderedShape(new OvalShape(), color, borderColor, 10);
        borderedOval.setIntrinsicHeight (height);
        borderedOval.setIntrinsicWidth (width);
        borderedOval.setPadding (10,10,10,10);
        return borderedOval;
    }

    //To draw a rectangle dynamically:

    public static ShapeDrawable drawRectangle (int width, int height, int color, int borderColor) {
        Path p = new Path();
        p.moveTo((float) (0.0), (float) (0.25*width));
        p.lineTo(width, (float) (0.25*width));
        p.lineTo(width, (float) (0.75*width));
        p.lineTo((float) (0.0), (float) (0.75*width));
        p.lineTo((float) (0.0), (float) (0.25*width));
        BorderedShape borderedRect = new BorderedShape(new PathShape(p, width, height), color, borderColor, 10);
        borderedRect.setIntrinsicHeight (height);
        borderedRect.setIntrinsicWidth (width);
        return borderedRect;
    }

    public static ShapeDrawable drawSquare (int width, int height, int color, int borderColor) {
        BorderedShape borderedRect = new BorderedShape(new RectShape(), color, borderColor, 10);
        borderedRect.setIntrinsicHeight(height);
        borderedRect.setIntrinsicWidth(width );
        return borderedRect;
    }

    public static ShapeDrawable drawStar (int width, int height, int color, int borderColor) {
        Path p = new Path();
        p.moveTo((float) (0.50*width),(float) (0.03*width));
        p.lineTo((float) (0.23*width),width);
        p.lineTo((float) (width),(float) (0.40*width));
        p.lineTo((float) (0.00*width),(float) (0.40*width));
        p.lineTo((float) (0.77*width),width);
        p.lineTo((float) (0.50*width),(float) (0.03*width));
        BorderedShape borderedRect = new BorderedShape(new PathShape(p, width, height), color, borderColor, 10);
        borderedRect.setIntrinsicHeight (height);
        borderedRect.setIntrinsicWidth (width);
        return borderedRect;
    }


    public static ShapeDrawable drawTriangle (int width, int height, int color, int borderColor) {
        Path p = new Path();
        p.moveTo((float) (0.0), width);
        p.lineTo((float) (0.5*width), (float) (0.0));
        p.lineTo((float) (width), width);
        p.lineTo((float) (0.0), width);
        BorderedShape borderedRect = new BorderedShape(new PathShape(p, width, height), color, borderColor, 10);
        borderedRect.setIntrinsicHeight (height);
        borderedRect.setIntrinsicWidth (width);
        return borderedRect;
    }

    public static ShapeDrawable drawHexagon (int width, int height, int color, int borderColor) {
        Path p = new Path();
        p.moveTo((float) (0.0),(float) (0.5*width));
        p.lineTo((float) (0.25*width),(float) (0.067*width));
        p.lineTo((float) (0.75*width),(float) (0.067*width));
        p.lineTo(width,(float) (0.50*width));
        p.lineTo((float) (0.75*width),(float) (width-0.067*width));
        p.lineTo((float) (0.25*width),(float) (width-0.067*width));
        p.lineTo((float) (0.0),(float) (0.5*width));
        BorderedShape borderedRect = new BorderedShape(new PathShape(p, width, height), color, borderColor, 10);
        borderedRect.setIntrinsicHeight (height);
        borderedRect.setIntrinsicWidth (width);
        return borderedRect;
    }

    //To draw a Rounded-rectangle dynamically:

    public static ShapeDrawable drawRoundCornerRectange (int width, int height, float[] outerRadii, int color) {
        ShapeDrawable rndrect = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
        rndrect.setIntrinsicHeight (height);
        rndrect.setIntrinsicWidth (width);
        rndrect.getPaint ().setColor (color);
        return rndrect;
    }

    //To create a Gradient drawable dynamically:

    public static GradientDrawable setGradientColors(int bottomColor, int topColor) {
        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[] {bottomColor, topColor});
        gradient.setShape (GradientDrawable.RECTANGLE);
        //gradient.setCornerRadius(10.f);
        return gradient;

    }
}


