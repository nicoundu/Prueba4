package cl.pingon.prueba4.network;

import cl.pingon.prueba4.models.Wrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NatGeoPhoto {

    @GET("everything")
    Call<Wrapper> natGeoPics(@Query("apiKey") String api, @Query("sources") String sources, @Query("page") int page);

}
