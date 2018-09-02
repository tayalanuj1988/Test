package com.anujtayal.test.dagger;

import android.app.Application;

import com.anujtayal.test.apis.ApiInterface;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String BASE_URL = "https://api.nytimes.com";


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 2 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {

        int CONNECTION_TIMEOUT = 40;
        int READ_TIMEOUT = 40;

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");


            client.addInterceptor(interceptor).build();

            return chain.proceed(requestBuilder.build());
        });

        return client.build();
    }

    @Provides
    @Singleton
    @Named(GlobalVariables.Globals.AUTHORISED)
    ApiInterface provideApiInterface(Gson gson, Cache cache) {

        int CONNECTION_TIMEOUT = 20;
        int READ_TIMEOUT = 20;

        Interceptor interceptor = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            return chain.proceed(requestBuilder.build());
        };

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiInterface.class);
    }
}
