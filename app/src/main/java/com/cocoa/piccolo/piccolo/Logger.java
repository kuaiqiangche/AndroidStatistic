package com.cocoa.piccolo.piccolo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
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

    public static final ArrayList<String> eventList = new ArrayList<>();


    public static void loged(Context context, View view) {

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

        ClickEvent clickEvent = new ClickEvent();
        clickEvent.setTime();
        clickEvent.setUser("USERID");
        clickEvent.setApp_version("1.0");
        clickEvent.setNet("WIFI");
        clickEvent.setId(String.valueOf(view.getId()));
        clickEvent.setImg("img");
        clickEvent.setText("button text");
        add(context, JSON.toJSONString(clickEvent));
    }

    public static void onCheckedChanged(Context context) {
        add(context, "onCheckedChanged");
    }

    public static void onResumed(Context context) {
        LifecycleEvent lifecycleEvent = new LifecycleEvent();
        lifecycleEvent.setTime();
        lifecycleEvent.setUser("USERID");
        lifecycleEvent.setApp_version("1.0");
        lifecycleEvent.setNet("WIFI");
        lifecycleEvent.setName("123");
        lifecycleEvent.setLifecycle("onResumed");
        add(context, JSON.toJSONString(lifecycleEvent));
    }

    public static void onPaused(Context context) {
        LifecycleEvent lifecycleEvent = new LifecycleEvent();
        lifecycleEvent.setTime();
        lifecycleEvent.setUser("USERID");
        lifecycleEvent.setApp_version("1.0");
        lifecycleEvent.setNet("WIFI");
        lifecycleEvent.setName("123");
        lifecycleEvent.setLifecycle("onPaused");
        checkAdd(context, JSON.toJSONString(lifecycleEvent));
    }


    public static void add(Context context, String msg) {
        Log.e("-----", msg + "------");
        eventList.add(msg);
        Log.e("-----", eventList.size() + "------");
    }

    public static synchronized void checkAdd(Context context, String msg) {
        add(context, msg);

        try {
            ArrayList<String> list = new ArrayList<>();
            list.addAll(eventList);
            eventList.clear();

            Intent intent = new Intent(context, UploadService.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("list", list);
            context.getApplicationContext().startService(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }


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