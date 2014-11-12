package com.fanpage.tedliang.mysamplelistview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import dagger.ObjectGraph;
import rx.Subscription;


public class MyActivity extends Activity {
    Subscription mSubScribtion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);
        MyListView listView = (MyListView) findViewById(R.id.listivew);
        mSubScribtion = listView.parseData("","",this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ted","onPause");
//        mSubScribtion.unsubscribe();
    }
}
