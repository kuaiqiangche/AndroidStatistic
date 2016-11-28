package com.cocoa.piccolo.piccolo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cocoa.piccolo.piccolo.bean.ClickEvent;
import com.cocoa.piccolo.piccolo.bean.LifecycleEvent;

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
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setOccurred_time();
            clickEvent.setUser_id("USERID");
            clickEvent.setApp_version("1.0");
            clickEvent.setNet("WIFI");
            clickEvent.setId(String.valueOf(view.getId()));
            clickEvent.setImg("img");
            clickEvent.setText("button text");
            clickEvent.setEvent_type("click");
            clickEvent.setIdx("idx");
            clickEvent.setEvent_name("");
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
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setOccurred_time();
            clickEvent.setUser_id("USERID");
            clickEvent.setApp_version("1.0");
            clickEvent.setNet("WIFI");
            clickEvent.setId(String.valueOf(view.getId()));
            clickEvent.setImg("img");
            clickEvent.setText("button text");
            clickEvent.setEvent_type("itemClick");
            clickEvent.setIdx("idx");
            clickEvent.setEvent_name("");
            checkAdd(context, clickEvent);
        } catch (Exception e) {

        }
    }

    public static void onCheckedChanged(Context context) {
        try {
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setOccurred_time();
            clickEvent.setUser_id("USERID");
            clickEvent.setApp_version("1.0");
            clickEvent.setNet("WIFI");
            clickEvent.setId("12312");
            clickEvent.setImg("img");
            clickEvent.setText("button text");
            clickEvent.setEvent_type("checked");
            clickEvent.setIdx("idx");
            clickEvent.setEvent_name("");
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

            LifecycleEvent lifecycleEvent = new LifecycleEvent();
            lifecycleEvent.setOccurred_time();
            lifecycleEvent.setUser_id("USERID");
            lifecycleEvent.setApp_version("1.0");
            lifecycleEvent.setNet("WIFI");
            lifecycleEvent.setPath("123");
            lifecycleEvent.setEvent_type("onResumed");
            lifecycleEvent.setText("text");
            lifecycleEvent.setPath(context.getClass().getName());
            lifecycleEvent.setEvent_name("");
            checkAdd(context, lifecycleEvent);
        } catch (Exception e) {

        }
    }

    public static void onPaused(Activity context) {
        try {
            LifecycleEvent lifecycleEvent = new LifecycleEvent();
            lifecycleEvent.setOccurred_time();
            lifecycleEvent.setUser_id("USERID");
            lifecycleEvent.setApp_version("1.0");
            lifecycleEvent.setNet("WIFI");
            lifecycleEvent.setPath("123");
            lifecycleEvent.setEvent_type("onPaused");
            lifecycleEvent.setText("text");
            lifecycleEvent.setPath(context.getClass().getName());
            lifecycleEvent.setEvent_name("");
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