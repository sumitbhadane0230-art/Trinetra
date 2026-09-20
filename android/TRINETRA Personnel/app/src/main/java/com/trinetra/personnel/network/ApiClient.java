package com.trinetra.personnel.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

    private static Retrofit retrofit;
    private static ApiService apiService;

    private ApiClient() {
        // Prevent instantiation
    }

    public static ApiService getApiService() {

        if (apiService == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    )
                    .build();

            apiService = retrofit.create(
                    ApiService.class
            );
        }

        return apiService;
    }
}