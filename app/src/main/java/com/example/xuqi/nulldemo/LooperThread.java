package com.example.xuqi.nulldemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by xuqi on 18/5/28.
 */

class LooperThread extends Thread {
    public Handler mHandler;
    private boolean initHandler;

    public void run() {
        Looper.prepare();
        //需要在线程进入死循环之前，创建一个Handler实例供外界线程给自己发消息
        if (initHandler) {
            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    //Handler 对象在这个线程构建，那么handleMessage的方法就在这个线程执行
                    Log.d("xuqi_test", "handleMessage");
                }
            };
        }
        Looper.loop();
        /**
         * loop后面的代码不执行
         */
//        mHandler = new Handler() {
//            public void handleMessage(Message msg) {
//                Log.d("xuqi_test", "handleMessage");
//            }
//        };
    }

    public void setInitHandler(boolean mInitHandler) {
        this.initHandler = mInitHandler;
    }
}
