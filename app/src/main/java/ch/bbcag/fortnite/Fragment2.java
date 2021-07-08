package ch.bbcag.fortnite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.adapter.NewsAdapter;
import ch.bbcag.fortnite.helper.NewsJSONParser;
import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.News;

public class Fragment2 extends Fragment {
    List<News> articles = new ArrayList<News>();
    private static final String NEWS_URL = "https://fortnite-api.com/v2/news";
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar = getView().findViewById(R.id.loading_news_progress);
        recyclerView = getView().findViewById(R.id.recyclerView_news);
        getNews();

        NewsAdapter newsAdapter = new NewsAdapter(getContext(), articles);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getNews() {
        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, NEWS_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    articles.addAll(NewsJSONParser.createArticleFromJsonString(response));
                    progressBar.setVisibility(View.GONE);
                    recyclerView.getAdapter().notifyDataSetChanged();
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
    }

    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        dialogBuilder.setMessage("Die News konnten nicht geladen werden, überprüfe deine Internetverbindung und versuche es erneut.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
