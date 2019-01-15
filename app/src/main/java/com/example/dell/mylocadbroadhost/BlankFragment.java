package com.example.dell.mylocadbroadhost;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BlankFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView mTvContent;
    private Context mContext;
    private LocalBroadcastManager manager;


    public BlankFragment() {
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        manager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter file = new IntentFilter("www.yufeilong.com");
        manager.registerReceiver(receiver, file);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("www.yufeilong.com")) {
                String name = intent.getStringExtra("name");
                mTvContent.setText(name);
            }
        }
    };

    private void initView(View view) {
        mContext = getActivity();
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
