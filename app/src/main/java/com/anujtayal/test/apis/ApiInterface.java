package com.anujtayal.test.apis;

import com.anujtayal.test.dagger.GlobalVariables;
import com.anujtayal.test.ui.model.StoryResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//interface for API calls
public interface ApiInterface {

    @GET("/svc/topstories/v2/{section}.json")
    Observable<StoryResponse> getStories(@Path(value = "section", encoded = true) String section,@Query(GlobalVariables.ApiKeys.API_KEY) String params);
}
