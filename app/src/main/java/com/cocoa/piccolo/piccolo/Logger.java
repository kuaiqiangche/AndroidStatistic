package com.cocoa.piccolo.piccolo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static final List<String>  eventList = new ArrayList<>(BUFFER_SIZE+10);


    public static void loged(Context context, View view) {
        add(context,view.toString());
        Log.e("----", view.toString());
    }

    public static void onCheckedChanged(Context context) {
        add(context,"onCheckedChanged");
        Log.e("----", "--onCheckedChanged--");
    }

//    public static void onCreated(Context context) {
//        eventList.add("onCreated");
//        Log.e("----", "--onCreate--");
//    }
//
//    public static void onStoped(Context context) {
//        eventList.add("onStoped");
//        Log.e("----", "--onStop--");
//    }
//    public static void onPaused(Context context) {
//        eventList.add("onPaused");
//        Log.e("----", "--onPause--");
//    }


    public static synchronized void add(Context context, String msg){
        eventList.add(msg);
        if(eventList.size() >= BUFFER_SIZE ){
            //创建
           File cacheFile  = context.getCacheDir();
           File file  = new File(cacheFile,"cc.txt");
            try {
                file.createNewFile();
                if(file.exists()){
                    FileWriter fileOutputStream  = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileOutputStream);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);

                    printWriter.write(eventList.toString());
                    printWriter.flush();
                    printWriter.close();
                    eventList.clear();

                    Intent intent = new Intent(context,UploadService.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("fileName",file.toString());
                    context.startService(intent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
