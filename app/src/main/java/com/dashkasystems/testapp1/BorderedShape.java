package com.dashkasystems.testapp1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by pandasystems on 11/28/16.
 */
public class BorderedShape extends ShapeDrawable {
    private final Paint fillpaint, strokepaint;
    private final int strokeWidth;

    public BorderedShape(Shape s, int fill, int stroke, int strokeWidth) {
        super(s);
        fillpaint = new Paint(this.getPaint());
        fillpaint.setColor(fill);
        strokepaint = new Paint(fillpaint);
        strokepaint.setStyle(Paint.Style.STROKE);
        float density = ScreenHelper.getDensity();
        this.strokeWidth = (int) ((float)strokeWidth * density);//strokeWidth;
        strokepaint.setStrokeWidth(this.strokeWidth);
        strokepaint.setColor(stroke);
    }

    @Override
    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        shape.resize(canvas.getClipBounds().right,
                canvas.getClipBounds().bottom);
        shape.draw(canvas, fillpaint);

        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0, 0, canvas.getClipBounds().right,
                        canvas.getClipBounds().bottom),
                new RectF(strokeWidth/2, strokeWidth/2, canvas.getClipBounds().right - strokeWidth/2,
                        canvas.getClipBounds().bottom - strokeWidth/2),
                Matrix.ScaleToFit.FILL);
        canvas.concat(matrix);

        shape.draw(canvas, strokepaint);
    }
}


