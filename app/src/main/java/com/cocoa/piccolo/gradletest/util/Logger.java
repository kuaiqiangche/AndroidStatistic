package com.cocoa.piccolo.gradletest.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cocoa.piccolo.gradletest.UploadService;
import com.cocoa.piccolo.gradletest.statistical.StatisticalEvent;

import java.util.ArrayList;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.Logger
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/14 14:48
 */
public class Logger {

    public static final int BUFFER_SIZE = 10;

    public static final ArrayList<Object> eventList = new ArrayList<>();


//    private String id;                                         // 【安卓唯一标识】（控件ID）
//    private String text;                                       // 如果是点击，那么这个字段是这个控件下所有文字
//    private String img;                                        // 图片地址
//    private String path;                                       // 【IOS唯一标识】（控件路径）
//    private String idx;                                        //  控件位置（可视化预留）
//    private String view_type;       // 控件类型                     //  控件类型 如"div"
//    public long occurred_time;      //发生时间                     //  时间戳（秒）
//    private String user_id;       // 用户信息                    //  用户手机号
//    private String app_build;     // app自定义版本                //  自定义版本号，如"2.2.1"
//    private String app_version;  // app版本                    //  版本号，如"14"
//    private String net;         // 网络类型                     //  网络 如2G,3G,4G,mobile,WIFI，LTE
//    private int event_type;    // 事件类型                       //  1点击；2进入；3退出；


    public static void onClicked(Context context, View view) {

//        Log.e("-----", view.getId() + "----");
//        Log.e("-----", (view instanceof TextView) + "---instanceof TextView-");
//        Log.e("-----", (view instanceof View) + "---instanceof View-");
//        Log.e("----", (context instanceof Activity) + "---instanceof Activity-");
//        if (view instanceof TextView) {
//            TextView textView = (TextView) view;
//            Log.e("-----", textView.getText() + "----");
//        }else if (view instanceof ImageView) {
//            ImageView imageView = (ImageView) view;
//        }
        try {
            StatisticalEvent clickEvent = new StatisticalEvent();
            clickEvent.setId(String.valueOf(view.getId()));
            clickEvent.setImg("img");
            clickEvent.setText("button text");
            clickEvent.setPath("path");
            clickEvent.setIdx("");
            clickEvent.setView_type(view.getClass().getSimpleName());
            clickEvent.setOccurred_time();
            clickEvent.setUser_id(getUserInfo(context));

            String appInfo[] = getAppInfo(context);
            clickEvent.setApp_build(appInfo[0]);
            clickEvent.setApp_version(appInfo[1]);

            clickEvent.setNet(NetworkUtils.getNetworkState(context));
            clickEvent.setEvent_type(StatisticalEvent.EVENT_TYPE_CLICK);

            checkAdd(context, clickEvent);
        } catch (Exception e) {

        }
    }

    public static void onItemClicked(Context context, View view) {

//        Log.e("-----", view.getId() + "----");
//        Log.e("-----", (view instanceof TextView) + "---instanceof TextView-");
//        Log.e("-----", (view instanceof View) + "---instanceof View-");
//        Log.e("----", (context instanceof Activity) + "---instanceof Activity-");
//        if (view instanceof TextView) {
//            TextView textView = (TextView) view;
//            Log.e("-----", textView.getText() + "----");
//        }else if (view instanceof ImageView) {
//            ImageView imageView = (ImageView) view;
//        }
        try {
            StatisticalEvent clickEvent = new StatisticalEvent();
            clickEvent.setId(String.valueOf(view.getId()));
            clickEvent.setImg("");
            clickEvent.setText("");
            clickEvent.setPath("");
            clickEvent.setIdx("");
            clickEvent.setView_type(view.getClass().getSimpleName());
            clickEvent.setOccurred_time();
            clickEvent.setUser_id(getUserInfo(context));

            String appInfo[] = getAppInfo(context);
            clickEvent.setApp_build(appInfo[0]);
            clickEvent.setApp_version(appInfo[1]);
            clickEvent.setNet(NetworkUtils.getNetworkState(context));
            clickEvent.setEvent_type(StatisticalEvent.EVENT_TYPE_CLICK);
//            clickEvent.setEvent_name("");
            checkAdd(context, clickEvent);
        } catch (Exception e) {

        }
    }

    public static void onCheckedChanged(Context context) {
        try {
            StatisticalEvent clickEvent = new StatisticalEvent();
            clickEvent.setId("id");
            clickEvent.setImg("");
            clickEvent.setText("");
            clickEvent.setPath("");
            clickEvent.setIdx("");
            clickEvent.setView_type("RadioButton");
            clickEvent.setOccurred_time();
            clickEvent.setUser_id(getUserInfo(context));

            String appInfo[] = getAppInfo(context);
            clickEvent.setApp_build(appInfo[0]);
            clickEvent.setApp_version(appInfo[1]);

            clickEvent.setNet(NetworkUtils.getNetworkState(context));
            clickEvent.setEvent_type(StatisticalEvent.EVENT_TYPE_CLICK);
//            clickEvent.setEvent_name("");
            checkAdd(context, clickEvent);
        } catch (Exception e) {

        }
    }

    public static void onResumed(Activity context) {

//        try {
//            Class c = context.getClass();
//            for(Field f:c.getFields()){
//                Log.e("-=-=--",f.getName());
//            }
////            Field field = c.getField("mTitle");
////            Object o = field.get(c);
////
////            Log.e("----", o.toString());
//
//        } catch (Exception e) {
//            Log.e("----", e.toString());
//            Log.e("----", e.toString());
//        }

        try {
            Log.e("----", context.getTitle() + "");

            String type = context.getClass().getName();

            StatisticalEvent lifecycleEvent = new StatisticalEvent();
            lifecycleEvent.setId("");
            lifecycleEvent.setImg("");
            lifecycleEvent.setText("");
            lifecycleEvent.setPath("");
            lifecycleEvent.setIdx("");
            lifecycleEvent.setView_type("Activity");
            lifecycleEvent.setOccurred_time();
            lifecycleEvent.setUser_id(getUserInfo(context));

            String appInfo[] = getAppInfo(context);
            lifecycleEvent.setApp_build(appInfo[0]);
            lifecycleEvent.setApp_version(appInfo[1]);
            lifecycleEvent.setNet(NetworkUtils.getNetworkState(context));
            lifecycleEvent.setEvent_type(StatisticalEvent.EVENT_TYPE_INTO);

            checkAdd(context, lifecycleEvent);
        } catch (Exception e) {

        }
    }

    public static void onPaused(Activity context) {
        try {
            StatisticalEvent lifecycleEvent = new StatisticalEvent();
            lifecycleEvent.setId("");
            lifecycleEvent.setImg("");
            lifecycleEvent.setText("activity NAME");
            lifecycleEvent.setPath("");
            lifecycleEvent.setIdx("");
            lifecycleEvent.setView_type("Activity");
            lifecycleEvent.setOccurred_time();
            lifecycleEvent.setUser_id(getUserInfo(context));

            String appInfo[] = getAppInfo(context);
            lifecycleEvent.setApp_build(appInfo[0]);
            lifecycleEvent.setApp_version(appInfo[1]);
            lifecycleEvent.setNet(NetworkUtils.getNetworkState(context));
            lifecycleEvent.setEvent_type(StatisticalEvent.EVENT_TYPE_EXIT);

            checkAdd(context, lifecycleEvent);
        } catch (Exception e) {

        }
    }


    public static void add(Context context, Object commEvent) {
        Log.e("-----", commEvent + "------");
        eventList.add(commEvent);
        Log.e("-----", eventList.size() + "------");
    }

    public static synchronized void checkAdd(Context context, Object commEvent) throws Exception {
        add(context, commEvent);
        ArrayList<Object> list = new ArrayList<>();
        list.addAll(eventList);
        eventList.clear();

        Intent intent = new Intent(context, UploadService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("list", list);
        context.getApplicationContext().startService(intent);

    }


    public static String[] getAppInfo(Context context) {
        return new String[]{"1.0", "1.0"};
    }
    public static String getUserInfo(Context context) {
        return "13656657386";
    }


}


//
//    File cacheFile = context.getCacheDir();
//    File file = new File(cacheFile, "cc.txt");
//
//file.createNewFile();
//        if (file.exists()) {
//        FileWriter fileOutputStream = new FileWriter(file);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileOutputStream);
//        PrintWriter printWriter = new PrintWriter(bufferedWriter);
//
//        printWriter.write(eventList.toString());
//        printWriter.flush();
//        printWriter.close();
//        for (int i = 0; i < BUFFER_SIZE; i++) {
//        eventList.remove(0);
//        }
//        Intent intent = new Intent(context, UploadService.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("fileName", file.toString());
//        context.getApplicationContext().startService(intent);
//        }