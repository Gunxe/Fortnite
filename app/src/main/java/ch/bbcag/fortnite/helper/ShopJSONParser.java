package ch.bbcag.fortnite.helper;

import android.os.Bundle;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import ch.bbcag.fortnite.model.Bundles;

public class ShopJSONParser {
    public static List<Bundles> createBundleFromJsonString(String response) throws JSONException {
        List<Bundles> bundles = new ArrayList<Bundles>();

//        JSONObject jsonObject = response;
//        JSONObject data = jsonObject.getJSONObject("data");
//        JSONObject featured = data.getJSONObject("featured");
//        JSONArray entries = featured.getJSONArray("entries");

        JSONArray entries = new JSONObject(response).getJSONObject("data").getJSONObject("featured").getJSONArray("entries");

        for (int i = 0; i < entries.length(); i++) {
            Bundles bundle = new Bundles();
            JSONObject bundleObject = entries.getJSONObject(i);
            try {
                JSONObject bundleInfos = bundleObject.getJSONObject("bundle");
                bundle.setImageURL(bundleInfos.getString("image"));
                bundle.setName(bundleInfos.getString("name"));
            }catch (Exception e){
                JSONObject imageObject =  bundleObject.getJSONObject("newDisplayAsset").getJSONArray("materialInstances").getJSONObject(0).getJSONObject("images");
                bundle.setImageURL(imageObject.getString("OfferImage"));
            }
//            JSONObject newDisplayAsset = bundleObject.getJSONObject("newDisplayAsset");
//
//            JSONArray materialInstances = newDisplayAsset.getJSONArray("materialInstances");
//            JSONObject materialInstancesObject = materialInstances.getJSONObject(0);
            JSONObject colors =  bundleObject.getJSONObject("newDisplayAsset").getJSONArray("materialInstances").getJSONObject(0).getJSONObject("colors");
            bundle.setPrice(bundleObject.getInt("finalPrice"));
            bundle.setBackground1("#" + colors.getString("Background_Color_A"));
            bundle.setBackground1("#" + colors.getString("Background_Color_B"));
            bundles.add(bundle);
        }
        return bundles;
    }
}
