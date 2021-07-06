package ch.bbcag.fortnite.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class Bundles {
    private String name;
    private int price;
    private List<Item> items = new ArrayList<>();
    private String imageURL;
    private Color background1;
    private Color background2;

}
