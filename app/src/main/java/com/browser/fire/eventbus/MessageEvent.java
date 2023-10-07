package com.browser.fire.eventbus;

public class MessageEvent {
    public static final String CHOOSED_THEME = "CHOOSED_THEME";
    public static final String DELETED_THEME = "DELETED_THEME";
    public static final String DISMISS_FILTER_BOTTOM_SHEET = "DISMISS_FILTER_BOTTOM_SHEET";
    private String msg;
    public MessageEvent(String msg) {
        this.msg = msg;
    }

}
