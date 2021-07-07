package ch.bbcag.fortnite.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ch.bbcag.fortnite.model.Bundles;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    List<Bundles> bundles;
    Context context;

    public ShopAdapter(Context ct, List<Bundles> bundles){
        context = ct;
        this.bundles = bundles;
    }

    @NonNull
    @NotNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

}
