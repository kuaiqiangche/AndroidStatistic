package com.cocoa.piccolo.piccolo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

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

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

                Logger.onResumed(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.onPaused(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });



//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable throwable) {
//                Log.e("------",list.size()+"---");
//                throw  new NullPointerException("11");
//            }
//        });



    }
}
