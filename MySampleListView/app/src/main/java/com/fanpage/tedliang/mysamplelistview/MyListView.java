package com.fanpage.tedliang.mysamplelistview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fanpage.tedliang.mysamplelistview.module.Modules;

import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by tedliang on 14/11/12.
 */
public class MyListView extends ListView{

    @Inject
    GitHubClient.GitHub github;
    Observable<List<Contributor>> mObservable = PublishSubject.create();
    public MyListView(Context context) {
        super(context);
        init();

    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.L)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ObjectGraph graph = ObjectGraph.create(Modules.list());
        graph.inject(this);
    }

    public Subscription parseData(String owner,String repo, final Activity activity){
        mObservable = github.contributors("square", "retrofit");

        activity.startActivity(new Intent(activity,NextActivity.class));
        Subscription subscription = mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contributor>>() {
                    @Override
                    public void call(List<Contributor> contributors) {
                        Log.d("Ted","dataBack");
                        setAdapter(new MyGithubViewAdapter(contributors));
                        new AlertDialog.Builder(activity)
                                .setMessage("dialog").create().show();
                    }
                });

        return  subscription;
    }

    public Observable<List<Contributor>> getObservable(){
        return mObservable;
    }

    public class MyGithubViewAdapter extends BaseAdapter {

        private List<Contributor> mData;
        public MyGithubViewAdapter(List<Contributor> contributors) {
            mData = contributors;
        }

        @Override
        public int getCount() {

            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textview = new TextView(viewGroup.getContext());
            textview.setText(mData.get(i).login);
            return textview;
        }
    }
}
