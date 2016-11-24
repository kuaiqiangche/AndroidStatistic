package com.cocoa.piccolo.piccolo.bean;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.bean.DeviceInfo
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 17:06
 */
public  class DeviceInfo {
    private String device_name;
    private String manufacture;//private String ;// Apple,
    private String os;// iOS,
    private String screen_width;// 375,
    private String screen_height;// 667,
    private String os_version;// iOS,
    private String uuid;// 4EADBF0F-4DF8-4E5F-876C-851071F7670B,
    private String carrier;// U4e2d\U56fd\U8054\U901a,
    private String device_model;// iPhone7,

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getScreen_width() {
        return screen_width;
    }

    public void setScreen_width(String screen_width) {
        this.screen_width = screen_width;
    }

    public String getScreen_height() {
        return screen_height;
    }

    public void setScreen_height(String screen_height) {
        this.screen_height = screen_height;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }


    @Override
    public String toString() {
        return "DeviceInfo{" +
                "device_name='" + device_name + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", os='" + os + '\'' +
                ", screen_width='" + screen_width + '\'' +
                ", screen_height='" + screen_height + '\'' +
                ", os_version='" + os_version + '\'' +
                ", uuid='" + uuid + '\'' +
                ", carrier='" + carrier + '\'' +
                ", device_model='" + device_model + '\'' +
                '}';
    }
}
