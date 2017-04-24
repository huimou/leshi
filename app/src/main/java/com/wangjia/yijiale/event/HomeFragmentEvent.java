package com.wangjia.yijiale.event;

/**
 * Created by YiuChoi on 2016/4/7 0007.
 */
public class HomeFragmentEvent {
    private int id;
    private String name;

    public HomeFragmentEvent( String name, int id) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
