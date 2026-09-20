package com.trinetra.personnel.repository;

import com.trinetra.personnel.models.PredictionResponse;
import com.trinetra.personnel.models.WellnessCheckInRequest;
import com.trinetra.personnel.network.ApiClient;
import com.trinetra.personnel.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WellnessRepository {

    private final ApiService apiService;

    public WellnessRepository() {
        apiService = ApiClient.getApiService();
    }

    public void submitCheckIn(
            WellnessCheckInRequest request,
            Callback<PredictionResponse> callback
    ) {
        Call<PredictionResponse> call =
                apiService.submitCheckIn(request);

        call.enqueue(callback);
    }
}