package com.dashkasystems.testapp1;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;

public class CompileSceneExerciseActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    LinearLayout itemSelectorLayout;
    RelativeLayout mainSceneLayout;
    ImageView mainImageView;
    CompileSceneExercise exercise;

    String IMAGE_ALREADY_INSERTED_TAG = "IMAGE_ALREADY_INSERTED";

    int itemsInsertedOnCurrentStep = 0;
    int exerciseStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_scene_exercise);

        itemSelectorLayout = (LinearLayout) this.findViewById(R.id.compile_scene_items_selector_layout);
        mainSceneLayout = (RelativeLayout) this.findViewById(R.id.compile_scene_relative_layout);
        mainImageView = (ImageView) this.findViewById(R.id.compile_scene_bg_image);


        this.setupExercise();
        this.setupMainImage();
        this.setupSelectableItems();
        this.setUpTreeObserver();
    }

    private void setUpTreeObserver() {
        ViewTreeObserver viewTreeObserver = mainImageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mainImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int viewWidth = mainImageView.getWidth();
                    int viewHeight = mainImageView.getHeight();
                    exercise.setMainSceneSize(new Size(viewWidth, viewHeight));
                    setupTargetViews();
                }
            });
        }
    }

    private void setupExercise() {
        this.exercise = CompileSceneExercise.getExercise();
    }


    protected void setupMainImage() {
        Drawable mainImage = this.getResources().getDrawable(this.exercise.mainScene);
        mainImageView.setImageDrawable(mainImage);
    }

    protected void setupSelectableItems() {
        float screenWidth = ScreenHelper.getPXWidth(this);

        int itemsCount = exercise.sceneItems[exerciseStep].length;
        int shapeDim = (int) ((screenWidth / itemsCount) - 40);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(shapeDim,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 0);
        for (int i = 0; i < itemsCount; i++) {

            ImageView imageView = new ImageView(this);
            Drawable drawable = this.getResources().getDrawable(exercise.sceneItems[exerciseStep][i].drawable);
            imageView.setImageDrawable(drawable);
            imageView.setTag(exercise.sceneItems[exerciseStep][i].name);
            imageView.setLayoutParams(layoutParams);
            imageView.setOnLongClickListener(this);

            itemSelectorLayout.addView(imageView);
        }
        itemSelectorLayout.setBackgroundColor(getResources().getColor(R.color.lightOrange));
    }


    protected void setupTargetViews() {
        int itemsCount = exercise.sceneItems[exerciseStep].length;
        for (int i = 0; i < itemsCount; i++) {
            for (int j = 0; j < exercise.sceneItems[exerciseStep][i].frames.length; j++) {
                DARect frame = exercise.getFrameForStep(exerciseStep, i, j);
                DAPoint location = frame.origin;
                DASize size = frame.size;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.intWidth(), size.intHeight());
                layoutParams.leftMargin = location.intX();
                layoutParams.topMargin = location.intY();

                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(layoutParams);
                imageView.setTag(exercise.sceneItems[exerciseStep][i].name);
                imageView.setOnDragListener(this);
                mainSceneLayout.addView(imageView);
            }
        }
    }


    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((String) v.getTag());

        ClipData dragData = new ClipData((CharSequence) v.getTag(),
                new String[]{ ClipDescription.MIMETYPE_TEXT_PLAIN }, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

        // Starts the drag

        v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );
        return true;
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
                        ShapeDrawable borderStarted  =  ShapeFactory.drawSquare(v.getWidth(), v.getHeight(), R.color.white, R.color.red);

                        imageView.setImageDrawable(borderStarted);
                        //imageView.setBackgroundColor(getResources().getColor(R.color.white));
                    }


                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
//                ShapeDrawable borderEntered  =  ShapeFactory.drawSquare(v.getWidth(), v.getHeight(), R.color.white, R.color.green);
//                imageView.setImageDrawable(borderEntered);
//                imageView.setBackgroundColor(getResources().getColor(R.color.white));
                // Invalidate the view to force a redraw in the new tint
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
                String name = item.getText().toString();
                if ( name.equals(v.getTag()) ) {
                    int itemsCount = this.exercise.sceneItems[exerciseStep].length;
                    for (int i = 0; i < itemsCount; i++) {
                        if (this.exercise.sceneItems[exerciseStep][i].name.equals(name)) {
                            v.setTag(IMAGE_ALREADY_INSERTED_TAG);
                            this.itemsInsertedOnCurrentStep++;
                            imageView.setImageDrawable(getDrawable(this.exercise.sceneItems[exerciseStep][i].drawable));
                        }
                    }
                }
                v.invalidate();
                int possibleItemsToInsert = 0;
                int itemsCount = this.exercise.sceneItems[exerciseStep].length;
                for (int i = 0; i < itemsCount; i++) {
                    possibleItemsToInsert += this.exercise.sceneItems[exerciseStep][i].frames.length;
                }
                if (itemsInsertedOnCurrentStep >= possibleItemsToInsert) {
                    itemsInsertedOnCurrentStep = 0;
                    exerciseStep++;
                    if (exerciseStep < this.exercise.sceneItems.length) {
                        itemSelectorLayout.removeAllViewsInLayout();
                        this.setupSelectableItems();
                        this.setupTargetViews();
                    } else {
                        gameOver();
                    }
                }
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


    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            super(v);

            ImageView imV = (ImageView) v;
            Drawable dr = imV.getDrawable().mutate().getConstantState().newDrawable();
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

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Молодец!");
        alertDialogBuilder
                .setMessage("Ты правильно составил картину! Можешь попробовать ещё раз позже.")
                .setCancelable(false)
                .setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        CompileSceneExerciseActivity.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
