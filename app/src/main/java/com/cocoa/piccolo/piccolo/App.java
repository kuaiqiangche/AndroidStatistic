package com.cocoa.piccolo.piccolo;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.App
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/22 14:41
 */
public class App extends Application {


    public static List<String>  list  = new ArrayList<>();



    @Override
    public void onCreate() {
        super.onCreate();

//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable throwable) {
//                Log.e("------",list.size()+"---");
//                throw  new NullPointerException("11");
//            }
//        });



    }
}
