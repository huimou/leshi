package com.wangjia.yijiale.event;

/**
 * Created by YiuChoi on 2016/4/7 0007.
 */
public class StatusBarEvent {
    private String id;
    private String name;
    private int i;

    public StatusBarEvent(String id, String name, int i) {
        this.id = id;
        this.name = name;
        this.i = i;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
