package com.cocoa.piccolo.piccolo.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.LifecycleEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 17:06
 */

public  class LifecycleEvent extends CommEvent implements Serializable {

    private String text;   //预留
    private String path;   // 页面的名字

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}