package com.cocoa.piccolo.piccolo.bean;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.LifecycleEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 17:06
 */

public  class LifecycleEvent extends CommEvent {

    private String name;  // 页面的名字
    private String lifecycle; // 页面的生命周期， onResume onPause


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }
}