package com.cocoa.piccolo.piccolo.bean;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.ClickEvent
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 16:03
 */
public class ClickEvent extends  CommEvent{


    private String id;
    private String text;
    private String img;

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
