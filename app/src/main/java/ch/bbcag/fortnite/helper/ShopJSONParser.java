package ch.bbcag.fortnite.helper;

import android.os.Bundle;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.namespace.QName;

import ch.bbcag.fortnite.model.Bundles;

public class ShopJSONParser {
    public static Bundles createBundleFromJsonString(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject featured = data.getJSONObject("featured");
        JSONArray entries = featured.getJSONArray("entries");
        for (int i = 0; i < entries.length(); i++) {
            Bundles bundle = new Bundles();
            JSONObject bundleObject = entries.getJSONObject(i);
            JSONObject bundleInfos = bundleObject.getJSONObject("bundle");
            JSONObject newDisplayAsset = bundleObject.getJSONObject("newDisplayAsset");
            JSONArray materialInstances = newDisplayAsset.getJSONArray("materialInstances");
            JSONObject materialInstancesObject = materialInstances.getJSONObject(0);
            JSONObject colors = materialInstancesObject.getJSONObject("colors");

            bundle.setName(bundleInfos.getString("name"));
            bundle.setPrice(bundleObject.getInt("finalPrice"));
            bundle.setImageURL(bundleInfos.getString("image"));
            bundle.setBackground1("#" + colors.getString("Background_Color_A"));
            bundle.setBackground1("#" + colors.getString("Background_Color_B"));
        }


        return null;
    }
}
