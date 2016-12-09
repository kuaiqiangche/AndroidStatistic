package com.cocoa.piccolo.gradletest.statistical;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.StatisticalEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 16:05
 */
public class StatisticalEvent implements Serializable {

    public static final int EVENT_TYPE_CLICK = 1;
    public static final int EVENT_TYPE_INTO = 2;
    public static final int EVENT_TYPE_EXIT = 3;

    private String id;                                         // 【安卓唯一标识】（控件ID）
    private String text;                                       // 如果是点击，那么这个字段是这个控件下所有文字
    private String img;                                        // 图片地址
    private String path;                                       // 【IOS唯一标识】（控件路径）
    private String idx;                                        //  控件位置（可视化预留）
    private String view_type;       // 控件类型                     //  控件类型 如"div"
    public long occurred_time;      //发生时间                     //  时间戳（秒）
    private String user_id;       // 用户信息                    //  用户手机号
    private String app_build;     // app自定义版本                //  自定义版本号，如"2.2.1"
    private String app_version;  // app版本                    //  版本号，如"14"
    private String net;         // 网络类型                     //  网络 如2G,3G,4G,mobile,WIFI，LTE
    private int event_type;    // 事件类型                       //  1点击；2进入；3退出；


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
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

    public String getApp_build() {
        return app_build;
    }

    public void setApp_build(String app_build) {
        this.app_build = app_build;
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

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }
}
