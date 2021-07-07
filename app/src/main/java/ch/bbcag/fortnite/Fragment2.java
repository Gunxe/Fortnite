package ch.bbcag.fortnite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import ch.bbcag.fortnite.helper.NewsJSONParser;
import ch.bbcag.fortnite.model.News;

public class Fragment2 extends Fragment {
    private ListView articles;
    private static String NEWS_URL = "https://fortnite-api.com/v2/news";

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        articles = getView().findViewById(R.id.articleList);
        addArticlesToList();
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

    private void addArticlesToList() {

        String url = "https://fortnite-api.com/v2/news";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            articles.NewsJSONParser.createArticleFromJsonString(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        ArrayAdapter<News> newsAdapter = new ArrayAdapter<News>(getView().getContext().getApplicationContext(), android.R.layout.simple_list_item_1);
        newsAdapter.addAll(NewsJSONParser.createArticleFromJsonString());
        articles.setAdapter(newsAdapter);
    }
}
