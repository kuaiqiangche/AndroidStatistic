package com.cocoa.piccolo.piccolo.bean;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.CommEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 16:05
 */
public class CommEvent {

    public long time;
    private String user;
    private String app_version;
    private String net;

    public long getTime() {
        return time;
    }

    public void setTime() {
        this.time = System.currentTimeMillis();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
}
