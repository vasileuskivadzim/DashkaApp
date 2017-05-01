package com.dashkasystems.testapp1;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.dashkasystems.testapp1.Aquarium.AquariumExercise;
import com.dashkasystems.testapp1.Aquarium.Inhabitant;

import java.util.List;


public class AquariumActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    RelativeLayout aquariumView;
    LinearLayout inhabitantsView;

    AquariumExercise exercise = new AquariumExercise();

    String IMAGE_ALREADY_INSERTED_TAG = "IMAGE_ALREADY_INSERTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquarium);

        aquariumView = (RelativeLayout) findViewById(R.id.aquarium_view);

        inhabitantsView = (LinearLayout) findViewById(R.id.inhabitants_view);

        setupInhabitants();
        setupInhabitantsTargets();
    }


    protected void setupInhabitantsTargets() {
        int cellSize = (int) ((ScreenHelper.getPXWidth(this) - 40) / exercise.getDimension());
        List<Integer> indexesList = exercise.targetIndexesForCurrentStep();
        Integer[] indexes = indexesList.toArray(new Integer[indexesList.size()]);
        int x = colForIndex(indexes[0]) * cellSize;
        int y = rowForIndex(indexes[0]) * cellSize;

        int width = cellSize;
        int height = cellSize;

        for (int i = 1; i < indexes.length; i++) {
            int nextX = colForIndex(indexes[i]) * cellSize;
            int nextY = rowForIndex(indexes[i]) * cellSize;
            if (nextX == x + width) {
                width += cellSize;
            }

            if (nextY == y + height) {
                height += cellSize;
            }
        }
        
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(layoutParams);
        imageView.setTag(exercise.getRightInhabitCandidateIndex());
        imageView.setOnDragListener(this);
        aquariumView.addView(imageView);
    }

    protected void setupInhabitants() {
        float screenWidth = ScreenHelper.getPXWidth(this);

        Inhabitant[] candidates = exercise.getInhabitCandidates();

        int itemsCount = candidates.length;
        int shapeDim = (int) ((screenWidth / itemsCount) - 120);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(shapeDim,
                shapeDim);
        layoutParams.setMargins(10, 10, 10, 0);
        for (int i = 0; i < itemsCount; i++) {

            ImageView imageView = new ImageView(this);
            Drawable drawable = this.getResources().getDrawable(R.drawable.ball);
            imageView.setImageDrawable(drawable);
            imageView.setTag(i);
            imageView.setLayoutParams(layoutParams);
            imageView.setOnLongClickListener(this);

            inhabitantsView.addView(imageView);
        }
        inhabitantsView.setBackgroundColor(getResources().getColor(R.color.lightOrange));
    }

    protected int colForIndex(int index) {
        return index % exercise.getDimension();
    }

    protected int rowForIndex(int index) {
        return index / exercise.getDimension();
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        ImageView imageView = (ImageView) v;
        // Handles each of the expected events
        switch(action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    if (imageView.getDrawable() == null) {
                        ShapeDrawable borderStarted = ShapeFactory.drawSquare(v.getWidth(), v.getHeight(), R.color.white, R.color.red);

                        imageView.setImageDrawable(borderStarted);
                        //imageView.setBackgroundColor(getResources().getColor(R.color.white));
                    }

                    v.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
//                ShapeDrawable borderExited  =  ShapeFactory.drawSquare(v.getWidth(), v.getHeight(), R.color.white, R.color.red);
//                imageView.setImageDrawable(borderExited);
//                imageView.setBackgroundColor(getResources().getColor(R.color.white));

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                String index = item.getText().toString();
                Log.e("INDEX", index);
                Log.e("TAG",  v.getTag().toString());
                if ( index.equals(v.getTag().toString()) ) {
                    Log.e("Inside rigth", exercise.getRightInhabitCandidateIndex() + "");
                    v.setTag(IMAGE_ALREADY_INSERTED_TAG);
                    imageView.setImageDrawable(getDrawable(R.drawable.bear));

                    boolean isNotEnd = exercise.nextStep();
                    Log.e("IS NOT END", isNotEnd + "");
                    if (isNotEnd) {
                        Log.e("nextStep", "+");
                        inhabitantsView.removeAllViewsInLayout();
                        this.setupInhabitants();
                        this.setupInhabitantsTargets();
                    }
                }
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                // Invalidates the view to force a redraw
                v.invalidate();
                return true;
            // An unknown action type was received.
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item(v.getTag().toString());

        ClipData dragData = new ClipData( v.getTag().toString(),
                new String[]{ ClipDescription.MIMETYPE_TEXT_PLAIN }, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new AquariumActivity.MyDragShadowBuilder(v);

        // Starts the drag

        v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );
        return true;
    }


    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {
            super(v);
            ImageView imV = (ImageView) v;
            Drawable dr = imV.getDrawable();
            shadow = dr;
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            int width, height;
            width = getView().getWidth();// / 2;
            height = getView().getHeight();// / 2;
            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            shadow.draw(canvas);
        }
    }
}
