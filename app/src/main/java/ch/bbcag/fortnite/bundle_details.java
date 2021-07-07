package ch.bbcag.fortnite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.helper.ItemJSONParser;
import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.Item;

public class bundle_details extends AppCompatActivity {
    ImageView skinImage;
    TextView descriptionText, priceText;
    ArrayList<Item> items = new ArrayList<Item>();
    ProgressBar progressBar;
    ConstraintLayout container;
    String API_URL = "https://fortnite-api.com/v2/cosmetics/br/";
    Bundles bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_details);

        skinImage = findViewById(R.id.SkinIcon);
        descriptionText = findViewById(R.id.skinDescription);
        priceText = findViewById(R.id.bundle_price_text);
        progressBar = findViewById(R.id.loading_bundle_item);
        container = findViewById(R.id.container_test);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getItems();

    }

    private void getItems() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        itemRekursion(getIntent().getStringArrayListExtra("itemIds"), 0, items, queue);

    }

    private int itemRekursion(ArrayList<String> itemIds, int index, ArrayList<Item> arrayList, RequestQueue queue ){
        if (index == itemIds.size()){
            setData();
            progressBar.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
            return 0;
        }else {

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, API_URL + itemIds.get(index), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        arrayList.add(ItemJSONParser.createItemFromJSONObject(response));
                        itemRekursion(itemIds, index + 1, arrayList,queue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    generateAlertDialog();
                }
            });
            queue.add(objectRequest);
            return 0;
        }
    }

    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialogBuilder.setMessage("Die Badidetails konnten nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void setData(){
        priceText.setText(getIntent().getStringExtra("price"));
        descriptionText.setText(items.get(0).getDescription());
        Picasso.get().load(items.get(0).getImageURL()).into((ImageView) findViewById(R.id.skinPicture));

    }
}