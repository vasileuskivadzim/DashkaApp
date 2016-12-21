package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;

import java.util.Dictionary;

/**
 * Created by pandasystems on 12/3/16.
 */
public class CompileSceneExercise {
    public CompileSceneItem[][] sceneItems;
    public @DrawableRes int mainScene;

    public CompileSceneExercise(CompileSceneItem[][] items, @DrawableRes int mainScene) {
        this.sceneItems = items;
        this.mainScene = mainScene;
    }



}
