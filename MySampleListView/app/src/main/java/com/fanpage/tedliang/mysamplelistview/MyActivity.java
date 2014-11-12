package com.fanpage.tedliang.mysamplelistview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import dagger.ObjectGraph;
import rx.Subscription;
import rx.functions.Action1;


public class MyActivity extends Activity {
    Subscription mSubScribtion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);
        final MyListView listView = (MyListView) findViewById(R.id.listivew);
        mSubScribtion = listView.parseData("","",this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.getObservable().subscribe(new Action1<List<Contributor>>() {
                    @Override
                    public void call(List<Contributor> contributors) {
                        Log.d("Ted","size "+contributors.size());
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ted","onPause");
//        mSubScribtion.unsubscribe();
    }
}
