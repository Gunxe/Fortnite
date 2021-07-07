package ch.bbcag.fortnite.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.bbcag.fortnite.helper.NewsJSONParser;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private Context mContext;
    private List<NewsJSONParser> mData;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

    }
}
