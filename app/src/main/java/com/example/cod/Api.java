package com.example.cod;

import java.time.OffsetDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://eu1.cloud.thethings.network/api/v3/as/applications/esp32-unirostock/devices/heltec-lora-esp32-1/";


    @GET("packages/storage/uplink_message")
    Call<List<Result>> getCensorData(@Query("limit") int limit, @Query("after") OffsetDateTime after, @Query("field_mask") String field_mask);
}
