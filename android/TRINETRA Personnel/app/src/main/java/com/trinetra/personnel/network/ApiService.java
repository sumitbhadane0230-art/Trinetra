package com.trinetra.personnel.network;

import com.trinetra.personnel.models.WellnessCheckInRequest;
import com.trinetra.personnel.models.PredictionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("personnel/check-in")
    Call<PredictionResponse> submitCheckIn(
            @Body WellnessCheckInRequest request
    );
}