package com.relmaps.magalaxy.mouse;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HoverEvent extends ClickListener {

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        event.getListenerActor().setUserObject(true);
        //super.enter(event, x, y, pointer, fromActor);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        event.getListenerActor().setUserObject(false);
        //super.exit(event, x, y, pointer, toActor);
    }
}
