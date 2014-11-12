package com.fanpage.tedliang.mysamplelistview.module;

import com.fanpage.tedliang.mysamplelistview.GitHubClient;
import com.fanpage.tedliang.mysamplelistview.MockGitHub;
import com.fanpage.tedliang.mysamplelistview.MyListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tedliang on 14/11/12.
 */


/**
 * Created by tedliang on 14/11/12.
 */
@Module(
        injects = MyListView.class,overrides = true

)
public class testDevModule {

    @Provides
    @Singleton
    GitHubClient.GitHub provideApi(){
        return new MockGitHub();
    }
}
