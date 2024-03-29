package ch.bbcag.fortnite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.adapter.ShopAdapter;
import ch.bbcag.fortnite.helper.ShopJSONParser;
import ch.bbcag.fortnite.model.Bundles;

public class ShopFragment extends Fragment {
    private List<Bundles> bundles = new ArrayList<Bundles>();
    private static final String SHOP_URL = "https://fortnite-api.com/v2/shop/br/combined";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private boolean alreadyStarted = false;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        System.out.println("ner Fige oncreate");
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onStart() {
        System.out.println("ner Fige onstart");
        super.onStart();
        if (!alreadyStarted){
            recyclerView = getView().findViewById(R.id.reclyclerView);
            progressBar = getView().findViewById(R.id.loading_bundle_details_progress);
            getBundles();

            ShopAdapter shopAdapter = new ShopAdapter(getContext(), bundles );
            recyclerView.setAdapter(shopAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            alreadyStarted = true;
        }
    }

    private void getBundles() {
        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, SHOP_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    bundles.addAll(ShopJSONParser.createBundleFromObject(response));
                    progressBar.setVisibility(View.GONE);
                    recyclerView.getAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                generateAlertDialog();
            }
        });
        queue.add(objectRequest);
    }

    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        dialogBuilder.setMessage("Die Seite konnte nicht geladen werden, überprüfe deine Internetverbindung und versuche es erneut.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
