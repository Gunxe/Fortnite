package ch.bbcag.fortnite.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.model.Bundles;
import ch.bbcag.fortnite.model.Item;

public class ItemJSONParser {
    public static Item createItemFromJSONObject (JSONObject response) throws JSONException {
        Item items = new Item();
        Item item = new Item();
        JSONObject ItemJSONObject = response.getJSONObject("data");
        JSONObject images = ItemJSONObject.getJSONObject("images");
        item.setName(ItemJSONObject.getString("name"));
        item.setIconImgURL(images.getString("icon"));
        item.setImageURL(images.getString("featured"));
        if(item.getImageURL() == "null"){
            item.setImageURL((images.getString("icon")));
        }
        item.setDescription(ItemJSONObject.getString("description"));
        item.setItemId(ItemJSONObject.getString("id"));
        return item;
    }
}
