package com.cocoa.piccolo.piccolo.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.CommEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 16:05
 */
public class CommEvent implements Serializable{

    public long occurred_time;  //发生时间
    private String user_id;     // 用户信息
    private String app_version;  // app版本
    private String net;        // 网络类型
    private String event_type;  // 事件类型
    private String event_name;   //事件名字 自定义预留


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public long getOccurred_time() {
        return occurred_time;
    }

    public void setOccurred_time() {
        this.occurred_time = System.currentTimeMillis();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }
}
