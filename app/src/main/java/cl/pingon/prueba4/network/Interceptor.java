package cl.pingon.prueba4.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interceptor {

    private static final String BASE_URL = "https://newsapi.org/v2/";

    public NatGeoPhoto getPhoto() {

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NatGeoPhoto photoRequest = interceptor.create(NatGeoPhoto.class);

        return photoRequest;

    }
}

