package com.cocoa.piccolo.piccolo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.UploadService
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/22 15:47
 */
public class UploadService extends Service {

    public static final int SIZE = 50;
    public static final Object o = new Object();

    private static final int NEW_INTENT_COMMING = 0;
    private Handler handler;
    DatabaseHelper databaseHelper;




    private final class WorkThreadHanlder extends Handler {
        // 使用指定Looper创建Handler
        public WorkThreadHanlder(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == NEW_INTENT_COMMING) {
                Log.d("SingleService", Thread.currentThread().getName());

                ArrayList<String> list = (ArrayList<String>) msg.obj;

                prepareUpload(list);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Object o = intent.getSerializableExtra("list");
            if (o != null) {
                ArrayList<String> list = (ArrayList<String>) o;
                Message msg = handler.obtainMessage();
                msg.obj = list;
                msg.what = NEW_INTENT_COMMING;
                msg.sendToTarget();
            }
        }
        return START_STICKY;
    }

    File file;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            File cacheFile = getCacheDir();
            file = new File(cacheFile, "event.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
        }
        HandlerThread thread = new HandlerThread("work thread", Process.THREAD_PRIORITY_BACKGROUND);
        // thread启动之后才能调用getLooper()方法获取thread中的Looper对象
        thread.start();
        // 使用子线程的Looper创建handler, 该handler绑定在子线程的消息队列上
        handler = new WorkThreadHanlder(thread.getLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void prepareUpload(List<String> list) {

        synchronized (o) {
            String cachaeStr = read();
            if (!TextUtils.isEmpty(cachaeStr)) {
                // get cache list
                List<String> cacheList = JSON.parseArray(cachaeStr, String.class);

                if (cacheList != null) {
                    cacheList.addAll(list);
                } else {
                    cacheList = new ArrayList<>();
                }

                if (cacheList.size() > SIZE) {
                    List<String> uploadList = new ArrayList<>();
                    for (int i = 0; i < SIZE; i++) {
                        String msg = cacheList.get(0);
                        uploadList.add(msg);
                        cacheList.remove(0);
                    }
                    // start upload
                    Log.e("----","start upload");
                }
                write(JSON.toJSONString(cacheList));
            } else {
                //首次缓存数据
                write(JSON.toJSONString(list));
            }
        }
    }

    public void write(String s) {
        Log.e("--write--",s);
        try {
            FileWriter fileOutputStream = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutputStream);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.write(s);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
        }
    }

    public String read() {
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
        } catch (Exception e) {
        }
        Log.e("--read--",sb.toString());
        return sb.toString();
    }

}

//    long c1 = System.currentTimeMillis();
//for (String e : list) {
//        Event event = new Event();
//        event.setJsonMsg(e);
//        long c = System.currentTimeMillis();
//        databaseHelper.insertEvent(databaseHelper, event);
//        Log.e("----", "insert db  lost time " + (System.currentTimeMillis() - c) + "-----");
//        }
//        Log.e("----", "insert db total lost time " + (System.currentTimeMillis() - c1) + "-----");
//
//
//        long c = System.currentTimeMillis();
//        int count = databaseHelper.getDBCount(databaseHelper);
//        Log.e("----", "get count db  lost time  " + (System.currentTimeMillis() - c) + "-----");