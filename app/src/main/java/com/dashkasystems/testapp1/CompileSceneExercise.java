package com.dashkasystems.testapp1;

import android.graphics.Point;
import android.support.annotation.DrawableRes;
import android.util.Size;

import java.util.Dictionary;



class CompileSceneExercise {
    public CompileSceneItem[][] sceneItems;
    public @DrawableRes int mainScene;

    private Size mainSceneSize;
    private Size normalizatorSize;

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


    public CompileSceneExercise(CompileSceneItem[][] items, @DrawableRes int mainScene) {
        this.sceneItems = items;
        this.mainScene = mainScene;
    }



}
