package com.box.sdk;

public interface EventListener {
    void onEvent(BoxEvent event);

    boolean onException(Throwable e);
}
