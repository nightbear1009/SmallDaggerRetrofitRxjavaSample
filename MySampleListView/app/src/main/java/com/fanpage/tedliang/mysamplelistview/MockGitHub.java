package com.fanpage.tedliang.mysamplelistview;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.Path;
import rx.Observable;

/**
 * Created by tedliang on 14/11/12.
 */
public class MockGitHub implements GitHubClient.GitHub {
    @Override
    public Observable<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo) {
        List<Contributor> con = new ArrayList<Contributor>();
        for(int i=0 ; i<10;i++){
            Contributor b = new Contributor();
            b.login = "i"+i;
            con.add(b);
        }
        return Observable.just(con);
    }
}
