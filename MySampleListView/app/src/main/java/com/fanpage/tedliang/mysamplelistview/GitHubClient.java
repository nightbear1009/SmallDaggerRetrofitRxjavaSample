package com.fanpage.tedliang.mysamplelistview;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public class GitHubClient {
  public static final String API_URL = "https://api.github.com";
  public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributors(
        @Path("owner") String owner,
        @Path("repo") String repo
    );
  }

}