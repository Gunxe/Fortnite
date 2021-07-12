package ch.bbcag.fortnite.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.Item;

public class ShopJSONParser {
    public static List<Bundles> createBundleFromObject(JSONObject response) throws JSONException {
        List<Bundles> bundles = new ArrayList<Bundles>();
        JSONArray featuredEntries = response.getJSONObject("data").getJSONObject("featured").getJSONArray("entries");
        JSONArray dailyEntries = response.getJSONObject("data").getJSONObject("daily").getJSONArray("entries");
        readEntries(featuredEntries, bundles);
        readEntries(dailyEntries, bundles);
        return bundles;
    }

    private static void readEntries(JSONArray entries, List<Bundles> bundles) throws JSONException{
        for (int i = 0; i < entries.length(); i++) {
            Bundles bundle = new Bundles();
            JSONObject bundleObject = entries.getJSONObject(i);

            JSONArray itemsArray = bundleObject.getJSONArray("items");

            List<Item> items = new ArrayList<>();
            for (int j = 0; j < itemsArray.length(); j++) {
                items.add(ItemJSONParser.createItemFromJSONObjectShop((JSONObject) itemsArray.get(j)));
            }

            try {
                JSONObject bundleInfos = bundleObject.getJSONObject("bundle");
                bundle.setImageURL(bundleInfos.getString("image"));
                bundle.setName(bundleInfos.getString("name"));
            }catch (JSONException e){
                try {
                    JSONObject imageObject = bundleObject.getJSONObject("newDisplayAsset").getJSONArray("materialInstances").getJSONObject(0).getJSONObject("images");
                    bundle.setImageURL(imageObject.getString("OfferImage"));
                }catch (JSONException exception){
                    bundle.setImageURL(items.get(0).getImageURL());
                }
            }
            bundle.setPrice(bundleObject.getInt("finalPrice"));


            bundle.setItems(items);
            bundles.add(bundle);
        }
    }
}
