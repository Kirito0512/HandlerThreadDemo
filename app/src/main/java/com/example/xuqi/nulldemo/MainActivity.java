package com.example.xuqi.nulldemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mList = new ArrayList<>();
    private LooperThread mLooperThread;
    private HandlerThread mHandlerThread;
    private Handler mHander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        initCustomLooperThread();
//        initHandlerThread();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 自定义LooperThread方式
                mLooperThread.mHandler.sendEmptyMessage(1);
                // HandlerThread方式
//                mHander.sendEmptyMessage(1);
            }
        });
    }

    /**
     * HandlerThread方式
     */
    private void initHandlerThread() {
        mHandlerThread = new HandlerThread("test");
        mHandlerThread.start();
        mHander = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("xuqi_test", "handlerthread test");
                return false;
            }
        });
    }

    /**
     * 自定义的LooperThread方式
     */
    private void initCustomLooperThread() {
        mLooperThread = new LooperThread();
        // 设为false是为了验证，Handler完全可以在loop执行之后再初始化
        mLooperThread.setInitHandler(false);
        mLooperThread.start();
        // setInitHandler设为true就无需在这里再初始化Handler，变成官网推荐方式
        mLooperThread.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                Log.d("xuqi_test", "looperthread test");
            }
        };
    }
}
