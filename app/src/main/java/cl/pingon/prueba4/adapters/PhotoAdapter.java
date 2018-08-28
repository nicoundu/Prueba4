package cl.pingon.prueba4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cl.pingon.prueba4.R;
import cl.pingon.prueba4.models.Article;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.NatHolder> {


    private List<Article> articles = new ArrayList<>();

    @NonNull
    @Override
    public NatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
        return new NatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NatHolder holder, int position) {

        Article article = articles.get(position);
        ImageView photo = holder.natGeoPhoto;

        Picasso.get().load(article.getUrlToImage()).centerCrop().fit().into(photo);
        holder.title.setText(article.getTitle());


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void update(List<Article> articles) {
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    public static class NatHolder extends RecyclerView.ViewHolder {

        private ImageView natGeoPhoto;
        private TextView title;
        private int mHeight = 1500;


        public NatHolder(View itemView) {
            super(itemView);

            natGeoPhoto = itemView.findViewById(R.id.natGeoIv);
            title = itemView.findViewById(R.id.titleTv);

            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, mHeight);
            itemView.setLayoutParams(params);
        }
    }

}
