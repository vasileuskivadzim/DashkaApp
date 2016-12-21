package com.dashkasystems.testapp1;

import android.content.ClipData;
import android.content.ClipDescription;
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
        this.setupTargetViews();
    }

    private void setupExercise() {
        Point[] emptyOrigin = new Point[0];
        Size[] emptySize = new Size[0];

        //first step
        Point[] oakOrigin = new Point[1];
        oakOrigin[0] = new Point(0, 200);
        Size[] oakSize = new Size[1];
        oakSize[0] = new Size((int) (200*3.2), (int)(278*3.2));
        CompileSceneItem item1 = new CompileSceneItem(R.drawable.oak, "OAK", oakOrigin, oakSize);
        CompileSceneItem item2 = new CompileSceneItem(R.drawable.spruce, "SPRUCE", emptyOrigin, emptySize);

        //second step
        Point[] basketOrigin = new Point[2];
        basketOrigin[0] = new Point(600, 850);
        basketOrigin[1] = new Point(890, 850);
        Size[] basketSize = new Size[2];
        basketSize[0] = new Size(170, 170);
        basketSize[1] = new Size(170, 170);
        CompileSceneItem item3 = new CompileSceneItem(R.drawable.portfolio, "PORTFOLIO", emptyOrigin, emptySize);
        CompileSceneItem item4 = new CompileSceneItem(R.drawable.basket, "BASKET", basketOrigin, basketSize);

        //third step
        Point[] mushroomOrigin = new Point[6];
        mushroomOrigin[0] = new Point(600, 860);
        mushroomOrigin[1] = new Point(625, 860);
        mushroomOrigin[2] = new Point(650, 860);
        mushroomOrigin[3] = new Point(675, 860);
        mushroomOrigin[4] = new Point(890, 860);
        mushroomOrigin[5] = new Point(940, 860);
        Size[] mushroomSize = new Size[6];
        mushroomSize[0] = new Size((int) (304/3),(int) (352/3));
        mushroomSize[1] = new Size((int) (304/3),(int) (352/3));
        mushroomSize[2] = new Size((int) (304/3),(int) (352/3));
        mushroomSize[3] = new Size((int) (304/3),(int) (352/3));
        mushroomSize[4] = new Size((int) (304/3),(int) (352/3));
        mushroomSize[5] = new Size((int) (304/3),(int) (352/3));
        CompileSceneItem item5 = new CompileSceneItem(R.drawable.cone, "CONE", emptyOrigin, emptySize);
        CompileSceneItem item6 = new CompileSceneItem(R.drawable.mushroom, "MUSHROOM", mushroomOrigin, mushroomSize);

        //fourth step
        Point[] squirrelOrigin = new Point[1];
        squirrelOrigin[0] = new Point(150, 720);
        Size[] squirrelSize = new Size[1];
        squirrelSize[0] = new Size((int) (372*0.4),(int) (378*0.4));
        CompileSceneItem item7 = new CompileSceneItem(R.drawable.squirrel, "SQUIRREL", squirrelOrigin, squirrelSize);
        CompileSceneItem item8 = new CompileSceneItem(R.drawable.sparrow, "SPARROW", emptyOrigin, emptySize);

        //fifth step
        Point[] nutOrigin = new Point[1];
        nutOrigin[0] = new Point(269, 773);
        Size[] nutSize = new Size[1];
        nutSize[0] = new Size((int) (280*0.2),(int) (256*0.2));
        CompileSceneItem item9 = new CompileSceneItem(R.drawable.nut, "NUT", nutOrigin, nutSize);
        CompileSceneItem item10 = new CompileSceneItem(R.drawable.cone, "CONE", emptyOrigin, emptySize);

        //sixth step
        Point[] woodpeckerOrigin = new Point[1];
        woodpeckerOrigin[0] = new Point(320, 405);
        Size[] woodpeckerSize = new Size[1];
        woodpeckerSize[0] = new Size((int) (342*0.4),(int) (582*0.4));
        CompileSceneItem item11 = new CompileSceneItem(R.drawable.sparrow, "SPARROW", emptyOrigin, emptySize);
        CompileSceneItem item12 = new CompileSceneItem(R.drawable.woodpecker, "WOODPECKER", woodpeckerOrigin, woodpeckerSize);


        CompileSceneItem[][] items = {{item1, item2},
                                    {item3, item4},
                                    {item5, item6},
                                    {item7, item8},
                                    {item9, item10},
                                    {item11, item12}};
        @DrawableRes int mainImage = R.drawable.boywithgirl;
        this.exercise = new CompileSceneExercise(items, mainImage);

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
            for (int j = 0; j < exercise.sceneItems[exerciseStep][i].location.length; j++) {
                Point location = exercise.sceneItems[exerciseStep][i].location[j];
                Size size = exercise.sceneItems[exerciseStep][i].size[j];
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size.getWidth(), size.getHeight());
                layoutParams.leftMargin = location.x;
                layoutParams.topMargin = location.y;

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
                    possibleItemsToInsert += this.exercise.sceneItems[exerciseStep][i].location.length;
                }
                if (itemsInsertedOnCurrentStep >= possibleItemsToInsert) {
                    itemsInsertedOnCurrentStep = 0;
                    exerciseStep++;
                    if (exerciseStep < this.exercise.sceneItems.length) {
                        itemSelectorLayout.removeAllViewsInLayout();
                        this.setupSelectableItems();
                        this.setupTargetViews();
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
