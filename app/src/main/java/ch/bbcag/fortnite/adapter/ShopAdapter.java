package ch.bbcag.fortnite.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ch.bbcag.fortnite.R;
import ch.bbcag.fortnite.bundle_details;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.shop_row, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopViewHolder holder, int position) {
        holder.preis.setText(""+bundles.get(position).getPrice());
        Picasso.get().load(bundles.get(position).getImageURL()).into(holder.iconImage);
        holder.shopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, bundle_details.class);
                intent.putExtra("bundle", (Parcelable) bundles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bundles.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        TextView preis;
        ImageView iconImage;
        ConstraintLayout shopLayout;

        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            preis = itemView.findViewById(R.id.bundlePrice);
            iconImage = itemView.findViewById(R.id.SkinIcon);
            shopLayout = itemView.findViewById(R.id.showLayout);
        }
    }

}
