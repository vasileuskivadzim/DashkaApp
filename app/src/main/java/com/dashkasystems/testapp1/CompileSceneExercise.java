package com.dashkasystems.testapp1;

import android.graphics.Point;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.util.Size;

import java.util.Dictionary;



class CompileSceneExercise {
    public CompileSceneItem[][] sceneItems;
    public @DrawableRes int mainScene;

    private Size mainSceneSize;
    private Size normalizatorSize;

    private Text text;

    public void setMainSceneSize(Size size) {
        mainSceneSize = size;
    }

    public void setNormalizatorSize(Size size) {
        normalizatorSize = size;
    }


    public DARect getFrameForStep(int step, int sceneItem, int frameIndex) {
        DARect relativeFrame = this.sceneItems[step][sceneItem].frames[frameIndex];
        double dx = mainSceneSize.getWidth() / (double) normalizatorSize.getWidth();
        double dy = mainSceneSize.getHeight() / (double) normalizatorSize.getHeight();
        return new DARect(relativeFrame.x() * dx,
                            relativeFrame.y() * dy,
                            relativeFrame.width() * dx,
                            relativeFrame.height() * dy);
    }

    public Text getText() {
        return this.text;
    }

    public void vocalize() {

    }



    public CompileSceneExercise(Text text, CompileSceneItem[][] items, @DrawableRes int mainScene) {
        this.sceneItems = items;
        this.mainScene = mainScene;
        this.text = text;
    }


    static public CompileSceneExercise getExercise() {
        String title = "Прогулка в лес";
        String textContent = "Аня и Дима пошли в лес за грибами. Они взяли с собой две корзинки." +
                " Аня положила в свою корзинку два гриба. Коля положил четыре. " +
                "По пути дети увидели большой дуб. На ветке сидела рыжая маленькая белка и ела орехи." + "" +
                " Выше сидел дятел и клювом делал дупло. Очень понравилось детям гулять в лесу!";
        @RawRes int textSound = R.raw.sasha_came_back;
        Text text = new Text(title, textContent, textSound);



        DARect[] emptyFrame = new DARect[0];

        //first step
        DARect[] oakFrame = new DARect[1];
        oakFrame[0] = new DARect(0, 200, 200*3.2, 278*3.2);
        CompileSceneItem item1 = new CompileSceneItem(R.drawable.oak, "OAK", oakFrame);
        CompileSceneItem item2 = new CompileSceneItem(R.drawable.spruce, "SPRUCE", emptyFrame);

        //second step
        DARect[] basketFrames = new DARect[2];
        basketFrames[0] = new DARect(600, 850, 170, 170);
        basketFrames[1] = new DARect(890, 850, 170, 170);
        CompileSceneItem item3 = new CompileSceneItem(R.drawable.portfolio, "PORTFOLIO", emptyFrame);
        CompileSceneItem item4 = new CompileSceneItem(R.drawable.basket, "BASKET", basketFrames);

        //third step
        DARect[] mushroomFrames = new DARect[6];
        mushroomFrames[0] = new DARect(600, 860, 304/3, 352/3);
        mushroomFrames[1] = new DARect(625, 860, 304/3, 352/3);
        mushroomFrames[2] = new DARect(650, 860, 304/3, 352/3);
        mushroomFrames[3] = new DARect(675, 860, 304/3, 352/3);
        mushroomFrames[4] = new DARect(890, 860, 304/3, 352/3);
        mushroomFrames[5] = new DARect(940, 860, 304/3, 352/3);

        CompileSceneItem item5 = new CompileSceneItem(R.drawable.cone, "CONE", emptyFrame);
        CompileSceneItem item6 = new CompileSceneItem(R.drawable.mushroom, "MUSHROOM", mushroomFrames);

        //fourth step
        DARect[] squirrelFrame = new DARect[1];
        squirrelFrame[0] = new DARect(150, 720, 372*0.4, 378*0.4);
        CompileSceneItem item7 = new CompileSceneItem(R.drawable.squirrel, "SQUIRREL", squirrelFrame);
        CompileSceneItem item8 = new CompileSceneItem(R.drawable.sparrow, "SPARROW", emptyFrame);

        //fifth step
        DARect[] nutFrame = new DARect[1];
        nutFrame[0] = new DARect(269, 773, 280*0.2, 256*0.2);
        CompileSceneItem item9 = new CompileSceneItem(R.drawable.nut, "NUT", nutFrame);
        CompileSceneItem item10 = new CompileSceneItem(R.drawable.cone, "CONE", emptyFrame);

        //sixth step
        DARect[] woodpeckerFrame = new DARect[1];
        woodpeckerFrame[0] = new DARect(320, 405, 342*0.4, 582*0.4);
        CompileSceneItem item11 = new CompileSceneItem(R.drawable.sparrow, "SPARROW", emptyFrame);
        CompileSceneItem item12 = new CompileSceneItem(R.drawable.woodpecker, "WOODPECKER", woodpeckerFrame);


        CompileSceneItem[][] items = {{item1, item2},
                {item3, item4},
                {item5, item6},
                {item7, item8},
                {item9, item10},
                {item11, item12}};
        @DrawableRes int mainImage = R.drawable.boywithgirl;
        CompileSceneExercise exercise = new CompileSceneExercise(text, items, mainImage);
        exercise.setNormalizatorSize(new Size(1080, 1284));
        return exercise;
    }


}
