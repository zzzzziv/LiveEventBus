package com.jeremyliao.lebapp.event;

import com.ziv.liveeventbus.core.LiveEvent;

/**
 * Created by liaohailiang on 2020-05-29.
 */
public class DemoEvent implements LiveEvent {
    public final String content;

    public DemoEvent(String content) {
        this.content = content;
    }
}
