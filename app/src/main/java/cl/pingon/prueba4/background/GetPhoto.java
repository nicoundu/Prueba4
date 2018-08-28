package cl.pingon.prueba4.background;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.pingon.prueba4.models.Article;
import cl.pingon.prueba4.models.Wrapper;
import cl.pingon.prueba4.network.Interceptor;
import cl.pingon.prueba4.network.NatGeoPhoto;
import retrofit2.Call;
import retrofit2.Response;

public class GetPhoto extends AsyncTask<Integer, Void, List<Article>> {

    @Override
    protected List<Article> doInBackground(Integer... params) {

        NatGeoPhoto natGeoPhoto = new Interceptor().getPhoto();
        int page = params[0];
        Call<Wrapper> photo = natGeoPhoto.natGeoPics("db847ff81a76402197974365fbdbbf49", "national-geographic", page);
        List<Article> articles = new ArrayList<>();
        try {
            Response<Wrapper> response = photo.execute();
            if (200 == response.code() && response.isSuccessful()) {
                Wrapper wrapper = response.body();
                if (wrapper != null) {
                    List<Article> list = wrapper.getArticles();
                    if (list != null && list.size() > 0) {
                        articles.addAll(list);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
