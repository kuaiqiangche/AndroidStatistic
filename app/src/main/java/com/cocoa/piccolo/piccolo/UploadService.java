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
import com.cocoa.piccolo.piccolo.bean.CommEvent;
import com.cocoa.piccolo.piccolo.bean.DeviceInfo;
import com.cocoa.piccolo.piccolo.bean.UploadCommon;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.UploadService
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/22 15:47
 */
public class UploadService extends Service {

    public static final Object o = new Object();

    private static final int NEW_INTENT_COMMING = 0;
    private Handler handler;

    private File eventCacheFile;
    private File cacheFile = null;
    private DeviceInfo deviceInfo;

    private final class WorkThreadHanlder extends Handler {
        // 使用指定Looper创建Handler
        public WorkThreadHanlder(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == NEW_INTENT_COMMING) {
                Log.d("SingleService", Thread.currentThread().getName());

                ArrayList<Object> list = (ArrayList<Object>) msg.obj;

                prepareUpload(list);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Object o = intent.getSerializableExtra("list");
            if (o != null) {
                ArrayList<CommEvent> list = (ArrayList<CommEvent>) o;
                Message msg = handler.obtainMessage();
                msg.obj = list;
                msg.what = NEW_INTENT_COMMING;
                msg.sendToTarget();
            }
        }
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        deviceInfo = DeviceInfoUtils.getInfo(this);

        try {
            cacheFile = getCacheDir();
            eventCacheFile = new File(cacheFile, "event_cache.txt");
            if (!eventCacheFile.exists()) {
                eventCacheFile.createNewFile();
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


    public void prepareUpload(List<Object> list) {

        if (eventCacheFile == null || !eventCacheFile.exists()) {
            return;
        }

        synchronized (o) {
            String cachaeStr = read(eventCacheFile);
            if (!TextUtils.isEmpty(cachaeStr)) {
                // get cache list
                List<Object> cacheList = JSON.parseArray(cachaeStr, Object.class);

                if (cacheList != null) {
                    cacheList.addAll(list);
                } else {
                    cacheList = new ArrayList<>();
                }

                if (cacheList.size() >= Logger.BUFFER_SIZE) {
                    List<Object> uploadList = new ArrayList<>();
                    for (int i = 0; i < Logger.BUFFER_SIZE; i++) {
                        Object msg = cacheList.get(0);
                        uploadList.add(msg);
                        cacheList.remove(0);
                    }

                    // 封装上传的数据格式 大致格式//  {content:[{点击事件1},{点击事件2},{页面进入事件1},{common: 公共信息}]}
                    UploadCommon common = new UploadCommon();
                    common.setCommon(deviceInfo);
                    uploadList.add(common);

                    // test

                    String uploadListStr = JSON.toJSONString(uploadList);

                    Log.e("uploadListStr",uploadListStr);


                    String uploadCacheName = "event_cache" + System.currentTimeMillis();

                    final File tempCacheFile = new File(cacheFile, uploadCacheName + ".txt");

                    if (!tempCacheFile.exists()) {
                        try {
                            boolean result = tempCacheFile.createNewFile();
                            if (result) {  // create file success
                                write(tempCacheFile,uploadListStr);
                            }
                        } catch (IOException e) {
                            Log.e("--tempCacheFile--", e.toString());
                        }
                    }


                    Log.e("----", "start upload"+tempCacheFile.toString());


                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    OkHttpUtils
                            .post()
                            .url("http://ai.kuaiqiangche.com/api/mpimport")
                            .addParams("content", uploadListStr)
                            .build()
                            .execute(new Callback() {
                                @Override
                                public String parseNetworkResponse(Response response, int id) throws Exception {
                                    if(response==null){
                                        return null;
                                    }
                                    if (response.code() == HttpURLConnection.HTTP_OK) {
                                        return response.body().string();
                                    } else {
                                        return null;
                                    }
                                }

                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("----", e.toString() + "");
                                }

                                @Override
                                public void onResponse(Object response, int id) {

                                    if(response==null){
                                        return;
                                    }
                                    Log.e("----", "end upload"+response.toString() +tempCacheFile.toString() + "--delete");
                                    try {
                                        if (response != null) {
                                            if (!TextUtils.isEmpty(response.toString())) {
                                                JSONObject jsonObject = new JSONObject(response.toString());
                                                int code = jsonObject.optInt("code");
                                                if (code == 0) {
                                                    // when upload success, delete cache file
                                                    tempCacheFile.delete();
                                                    Log.e("----", tempCacheFile.toString() + "--delete");
                                                }

                                            }
                                        }
                                    } catch (Exception e) {
                                        Log.e("----","---delete upload cache file --"+ e.toString() );
                                    }
                                }
                            });
                }
                write(eventCacheFile, JSON.toJSONString(cacheList));
            } else {
                //首次缓存数据
                write(eventCacheFile, JSON.toJSONString(list));
            }
        }
    }

    public void write(File file, String s) {
        Log.e("--write--", s);
        FileWriter fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;

        try {
            fileOutputStream = new FileWriter(file, false);  // not append
            bufferedWriter = new BufferedWriter(fileOutputStream);
            printWriter = new PrintWriter(bufferedWriter);

            printWriter.write(s);
            printWriter.flush();

        } catch (Exception e) {
            Log.e("--read--", e.toString());
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {

            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {

            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public String read(File file) {
        StringBuffer sb = new StringBuffer();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }

        } catch (Exception e) {
            Log.e("--read--", e.toString());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {

            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {

            }

        }
        Log.e("--read--", sb.toString());
        return sb.toString();
    }

}

//    long c1 = System.currentTimeMillis();
//    for (String e : list) {
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