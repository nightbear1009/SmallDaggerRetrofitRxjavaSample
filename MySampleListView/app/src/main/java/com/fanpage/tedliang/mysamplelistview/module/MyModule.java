package com.fanpage.tedliang.mysamplelistview.module;

import com.fanpage.tedliang.mysamplelistview.GitHubClient;
import com.fanpage.tedliang.mysamplelistview.MyListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by tedliang on 14/11/12.
 */
@Module(
        injects = MyListView.class
)
public class MyModule {

    @Provides @Singleton
    GitHubClient.GitHub provideApi(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GitHubClient.API_URL)
                .build();
        return restAdapter.create(GitHubClient.GitHub.class);
    }
}
