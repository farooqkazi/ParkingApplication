package com.example.kazi.parkingapplication.Services;

import com.example.kazi.parkingapplication.Model.ParkingModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Kazi on 23/Apr/17.
 */

public interface API {
    @GET(Constants.PARK_URL)
    Observable<List<ParkingModel>>getParkingInfo();

    @GET("{id}")
    Observable<ParkingModel> getInfo(@Path("id") Integer id);

    @POST("{id}/reserve")
    Observable<ParkingModel> reserve(@Path("id") Integer id);
}
