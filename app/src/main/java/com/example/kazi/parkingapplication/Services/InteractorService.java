package com.example.kazi.parkingapplication.Services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kazi on 23/Apr/17.
 */

public class InteractorService {
    public static API getConnection(){

        Retrofit retrofit = null;
        OkHttpClient okHttpClient = null;

        /**
         * Used to print the log statements of the parsed json data in the logcat
         */

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * Add HttpLoginInterceptor to okhttp
         */
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    /**
                     * used to parse json to pojos
                     */
                    .addConverterFactory(GsonConverterFactory.create())
                    /**
                     * Display data received to RecyclerView
                     */
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    /**
                     * Add Okhttp as a friend
                     */
                    .client(okHttpClient)
                    .build();
        }

        return retrofit.create(API.class);
    }
}

