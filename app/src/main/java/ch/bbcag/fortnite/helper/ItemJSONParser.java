package ch.bbcag.fortnite.helper;

import org.json.JSONException;
import org.json.JSONObject;

import ch.bbcag.fortnite.model.Item;

public class ItemJSONParser {
    public static Item createItemFromJSONObject (JSONObject response) throws JSONException {
        return readItem(response.getJSONObject("data"));
    }

    public static Item createItemFromJSONObjectShop (JSONObject response) throws JSONException {
        return readItem(response);
    }

    private static Item readItem(JSONObject ItemJSONObject) throws JSONException{
        Item item = new Item();
        JSONObject images = ItemJSONObject.getJSONObject("images");
        JSONObject rarity = ItemJSONObject.getJSONObject("rarity");
        item.setName(ItemJSONObject.getString("name"));
        item.setIconImgURL(images.getString("icon"));
        item.setImageURL(images.getString("featured"));
        if(item.getImageURL() == "null"){
            item.setImageURL((images.getString("icon")));
        }
        item.setDescription(ItemJSONObject.getString("description"));
        item.setItemId(ItemJSONObject.getString("id"));
        item.setRarity(rarity.getString("value"));
        return item;
    }
}
