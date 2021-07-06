package ch.bbcag.fortnite.helper;

import android.os.Bundle;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.bbcag.fortnite.model.Bundles;

public class ShopJSONParser {
    public static Bundles createBundleFromJsonString(String jsonString) throws JSONException {
        Bundles bundle = new Bundles();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray data = jsonObject.getJSONArray("data");
        JSONObject featured = data.getJSONObject(1);
        JSONArray entries = featured.getJSONArray("entries");
        for (int i = 0; i < entries.length(); i++) {
            JSONObject bundleObject = entries.getJSONObject(i);
            bundle.setName();
        }


        return null;
    }
}
