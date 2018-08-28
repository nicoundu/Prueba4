package cl.pingon.prueba4.views.main;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

import java.util.List;

import cl.pingon.prueba4.R;
import cl.pingon.prueba4.adapters.PhotoAdapter;
import cl.pingon.prueba4.models.Article;
import cl.pingon.prueba4.background.GetPhoto;

public class PhotoFragment extends Fragment {

    private PhotoAdapter adapter;


    public PhotoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.photoRv);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        adapter = new PhotoAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int last = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int count = adapter.getItemCount();
                int limit = count - 10;

                if (last == limit) {
                    int pages = (count == 20) ? count / 10 : limit / 10;
                    new BackgroundPhoto().execute(pages);
                }
            }
        });

        new BackgroundPhoto().execute(9);

    }

    private class BackgroundPhoto extends GetPhoto {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            if (adapter.getItemCount() == 0) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

        }


        @Override
        protected void onPostExecute(List<Article> articles) {
            adapter.update(articles);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }
}
