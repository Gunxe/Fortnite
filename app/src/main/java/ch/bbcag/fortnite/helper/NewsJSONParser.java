package ch.bbcag.fortnite.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.model.News;

public class  NewsJSONParser {
    public static List<News> createArticleFromJsonString(JSONObject response) throws JSONException {
        List<News> articles = new ArrayList<News>();

        JSONArray motds = response.getJSONObject("data").getJSONObject("br").getJSONArray("motds");

        for (int i = 0; i < motds.length(); i++) {
            System.out.println("potato");
            News article = new News();
            JSONObject articleObject = motds.getJSONObject(i);
            try {
                article.setTabTitle(articleObject.getString("tabTitle"));
                if(article.getTabTitle() == "null") {
                    article.setTabTitle(articleObject.getString("title"));
                }
                article.setTitle(articleObject.getString("title"));
                article.setBody(articleObject.getString("body"));
                article.setImage(articleObject.getString("image"));
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            articles.add(article);
        }
        return articles;
    }
}
