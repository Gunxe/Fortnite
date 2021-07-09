package ch.bbcag.fortnite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.R;
import ch.bbcag.fortnite.BundleDetails;
import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.Item;
import lombok.Value;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<Bundles> bundles;
    private Context context;

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
        if(bundles.get(position).getName() != null){
            holder.name.setText(bundles.get(position).getName());
            holder.name.setTextSize(14F);
        }else {
            holder.name.setText(bundles.get(position).getItems().get(0).getName());
            holder.name.setTextSize(17F);
        }

        Glide.with(context).load(bundles.get(position).getImageURL()).transform(new CenterCrop()).into(holder.iconImage);

        switch (bundles.get(position).getItems().get(0).getRarity())
        {
            case "common":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.common));
                break;
            case "uncommon":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.uncommon));
                break;
            case "rare":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.rare));
                break;
            case "epic":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.epic));
                break;
            case "legendary":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.legendary));
                break;
            case "marvel":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.marvel));
                break;
            case "dc":
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.dc));
                break;
            default:
                holder.shopLayout.setBackgroundColor(context.getResources().getColor(R.color.defaultRarity));
        }
        holder.iconImage.setBackgroundColor(context.getResources().getColor(R.color.balck_transparent));

        holder.shopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> items = new ArrayList<String>();
                Intent intent = new Intent(context, BundleDetails.class);
                for (Item item : bundles.get(position).getItems()) {
                    items.add(item.getItemId());
                }
                intent.putStringArrayListExtra("itemIds",  items);
                intent.putExtra("price", String.valueOf(bundles.get(position).getPrice()));
                if (bundles.get(position).getName() != null){
                    intent.putExtra("bundleName", String.valueOf(bundles.get(position).getName()));
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bundles.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImage, background2;
        private ConstraintLayout shopLayout;
        private TextView name;

        public ShopViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.SkinIcon);
            background2 = itemView.findViewById(R.id.background2);
            shopLayout = itemView.findViewById(R.id.showLayout);
            name = itemView.findViewById(R.id.textView_shop);
        }
    }
}
