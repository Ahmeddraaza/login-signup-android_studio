package com.example.crypto_app.apis;

import com.example.crypto_app.marketmodel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface apiinterface {

    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
    Call<marketmodel> getMarketData();
}
