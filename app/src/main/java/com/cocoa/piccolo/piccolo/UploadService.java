package com.cocoa.piccolo.piccolo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.UploadService
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/22 15:47
 */
public class UploadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String fileName = intent.getStringExtra("fileName");

        Log.e("----","start upload "+fileName);
//        int i = 1;
//        while(i!=1000){
//            i++;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.e("-----",i+"---------------");
//        }
        return START_STICKY;
    }
}
