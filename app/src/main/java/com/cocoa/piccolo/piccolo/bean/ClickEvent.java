package com.cocoa.piccolo.piccolo.bean;

import java.io.Serializable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.ClickEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 16:03
 */
public class ClickEvent extends  CommEvent implements Serializable {


    private String id;
    private String text;
    private String img;
    private String path;
    private String idx;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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


}
