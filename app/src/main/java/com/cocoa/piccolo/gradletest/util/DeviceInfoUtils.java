package com.cocoa.piccolo.gradletest.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.cocoa.piccolo.gradletest.statistical.DeviceInfo;


/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.DeviceInfoUtils
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/24 17:19
 */
public class DeviceInfoUtils {

//    public  class DeviceInfo {
//        private String device_name;
//        private String manufacture;//private String ;// Apple,
//        private String os;// iOS,
//        private String screen_width;// 375,
//        private String screen_height;// 667,
//        private String os_version;// iOS,
//        private String uuid;// 4EADBF0F-4DF8-4E5F-876C-851071F7670B,
//        private String carrier;// U4e2d\U56fd\U8054\U901a,
//        private String device_model;// iPhone7,
//    }


    public static DeviceInfo getInfo(Context context) {

        DeviceInfo deviceInfo = new DeviceInfo();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        deviceInfo.setScreen_width(String.valueOf(outMetrics.widthPixels));
        deviceInfo.setScreen_height(String.valueOf(outMetrics.heightPixels));

        deviceInfo.setDevice_model(Build.MODEL);
        deviceInfo.setOs("Android");
        deviceInfo.setOs_version(Build.VERSION.RELEASE);
        deviceInfo.setManufacture(Build.MANUFACTURER);
        deviceInfo.setDevice_name(Build.BRAND + "-" + Build.MODEL);
        deviceInfo.setCarrier(getSimOperatorInfo(context));
        deviceInfo.setUuid(tm.getDeviceId());

        return deviceInfo;
    }

    public static String getSimOperatorInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operatorString = telephonyManager.getSimOperator();
        if (operatorString == null) {
            return "未知";
        }
        if (operatorString.equals("46000") || operatorString.equals("46002")) {
            return "中国移动";
        } else if (operatorString.equals("46001")) {
            return "中国联通";
        } else if (operatorString.equals("46003")) {
            return "中国电信";
        }
        //error
        return "未知";
    }
}
