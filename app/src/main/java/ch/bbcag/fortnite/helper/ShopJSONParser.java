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
import ch.bbcag.fortnite.model.Item;

public class ShopJSONParser {
    public static List<Bundles> createBundleFromObject(JSONObject response) throws JSONException {
        List<Bundles> bundles = new ArrayList<Bundles>();
        JSONArray entries = response.getJSONObject("data").getJSONObject("featured").getJSONArray("entries");
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
            JSONObject colors =  bundleObject.getJSONObject("newDisplayAsset").getJSONArray("materialInstances").getJSONObject(0).getJSONObject("colors");
            bundle.setPrice(bundleObject.getInt("finalPrice"));
            bundle.setBackground1("#" + colors.getString("Background_Color_A"));
            bundle.setBackground1("#" + colors.getString("Background_Color_B"));

            //Items
            JSONArray ItemsArray = bundleObject.getJSONArray("items");

            List<Item> items = new ArrayList<>();
            for (int j = 0; j < ItemsArray.length(); j++) {
                Item item = new Item();
                JSONObject ItemJSONObject = ItemsArray.getJSONObject(j);
                JSONObject images = ItemJSONObject.getJSONObject("images");
                item.setName(ItemJSONObject.getString("name"));
                item.setIconImgURL(images.getString("icon"));
                item.setImageURL("featured");
                item.setDescription(ItemJSONObject.getString("description"));

                items.add(item);
            }
            bundle.setItems(items);
            bundles.add(bundle);
        }
        return bundles;
    }
}
