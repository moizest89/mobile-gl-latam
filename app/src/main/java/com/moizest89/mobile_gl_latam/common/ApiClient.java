package com.moizest89.mobile_gl_latam.common;

import com.google.gson.GsonBuilder;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.protocols( Collections.singletonList(Protocol.HTTP_1_1) );
        return new Retrofit.Builder()
                .baseUrl( getBaseUrl() )
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                        .serializeNulls()
                                        .create()
                        )
                )
                .client( httpClient.build() )
                .build();
    }

    private static ApiClientInterface service;

    public static ApiClientInterface getService(){
        if (service == null) {
            service = retrofit().create( ApiClientInterface.class );
        }
        return service;
    }

    private static String getBaseUrl() {
        return Config.INSTANCE.getBASE_URL();
    }
}
