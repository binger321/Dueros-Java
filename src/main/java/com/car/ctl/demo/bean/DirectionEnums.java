package com.car.ctl.demo.bean;

import com.intellij.ui.RightAlignedLabelUI;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/3/11
 * Time: 下午1:02
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public enum  DirectionEnums{
    LEFT("left", "左"),
    RIGHT("right", "右"),
    FORWARD("forward", "前"),
    AFTERWARD("afterward", "后"),
    LEFT_FORWARD("left_forward", "左前"),
    RIGHT_FORWARD("right_forward", "右前"),
    LEFT_AFTERWARD("left_afterward", "左后"),
    RIGHT_AFTERWARD("right_forward", "右后");

    private String order;
    private String orderWord;

    DirectionEnums(String order, String orderWord) {
        this.order = order;
        this.orderWord = orderWord;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderWord() {
        return orderWord;
    }

    public void setOrderWord(String orderWord) {
        this.orderWord = orderWord;
    }
}
