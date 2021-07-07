package ch.bbcag.fortnite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.R;
import ch.bbcag.fortnite.bundle_details;
import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.Item;

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
        holder.preis.setText(String.valueOf(bundles.get(position).getPrice()));
        Picasso.get().load(bundles.get(position).getImageURL()).into(holder.iconImage);
        holder.iconImage.setBackgroundColor(Color.parseColor("#"+bundles.get(position).getBackground1()));
        holder.background2.setBackgroundColor(Color.parseColor("#"+bundles.get(position).getBackground2()));

        holder.shopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> items = new ArrayList<String>();
                Intent intent = new Intent(context, bundle_details.class);
                for (Item item : bundles.get(position).getItems()) {
                    items.add(item.getItemId());
                }
                intent.putStringArrayListExtra("itemIds",  items);
                intent.putExtra("price", String.valueOf(bundles.get(position).getPrice()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bundles.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        TextView preis;
        ImageView iconImage, background2;
        ConstraintLayout shopLayout;

        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            preis = itemView.findViewById(R.id.bundlePrice);
            iconImage = itemView.findViewById(R.id.SkinIcon);
            background2 = itemView.findViewById(R.id.background2);
            shopLayout = itemView.findViewById(R.id.showLayout);
        }
    }

}
