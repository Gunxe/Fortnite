package ch.bbcag.fortnite.model;

import android.graphics.Color;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class Bundles {
    private String name;
    private int price;
    private List<Item> items = new ArrayList<>();
    private String imageURL;
    private String background1;
    private String background2;

}
