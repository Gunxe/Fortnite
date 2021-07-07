package ch.bbcag.fortnite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ch.bbcag.fortnite.model.Bundles;

public class bundle_details extends AppCompatActivity {
    ImageView skinImage;
    TextView descriptionText, priceText;

    Bundles bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_details);

        skinImage = findViewById(R.id.SkinIcon);
        descriptionText = findViewById(R.id.skinDescription);
        priceText = findViewById(R.id.bundle_price_text);
    }

    private void getData(){
        if(getIntent().hasExtra("bundle")){
            bundle = getIntent().getParcelableExtra("bundle");
        }
    }

    private void setData(){
        priceText.setText(bundle.getPrice());
        descriptionText.setText(bundle.getItems().get(0).getDescription());
        Picasso.get().load(bundle.getImageURL()).into(skinImage);
    }
}