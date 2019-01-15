package com.example.dell.mylocadbroadhost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFlContentOne;
    private FrameLayout mFlContentTwo;
    private Button mBtnSend;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        BlankFragment fragment = BlankFragment.newInstance("", "");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        beginTransaction.add(R.id.fl_content_one, fragment);
        beginTransaction.commit();

        BlankFragment2 fragment2 = BlankFragment2.newInstance("", "");
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fl_content_two, fragment2);
        transaction.commit();

    }

    private void initView() {
        mContext = this;
        mFlContentOne = (FrameLayout) findViewById(R.id.fl_content_one);
        mFlContentTwo = (FrameLayout) findViewById(R.id.fl_content_two);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                sendLoadBroad();
                break;
        }
    }

    private void sendLoadBroad() {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(mContext);
        Intent intent = new Intent();
        intent.setAction("www.yufeilong.com");
        intent.putExtra("name", "席梦洁");
        lbm.sendBroadcast(intent);
    }
}
