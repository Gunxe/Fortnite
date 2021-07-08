package ch.bbcag.fortnite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ch.bbcag.fortnite.R;
import ch.bbcag.fortnite.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewHolder> {
    Context ct;
    List<News> articles;

    public NewsAdapter (Context ct, List<News> articles) {
        this.ct = ct;
        this.articles = articles;
    }

    @NonNull
    @NotNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.news_row, parent, false);
        return new newsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull newsViewHolder holder, int position) {
        holder.tabTitle.setText(articles.get(position).getTabTitle());
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getBody());
        Picasso.get().load(articles.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class newsViewHolder extends RecyclerView.ViewHolder {

        TextView tabTitle, title, description;
        ImageView image;

        public newsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tabTitle = itemView.findViewById(R.id.tabTitle_news);
            title = itemView.findViewById(R.id.title_news);
            description = itemView.findViewById(R.id.description_news);
            image = itemView.findViewById(R.id.image_news);
        }
    }
}
