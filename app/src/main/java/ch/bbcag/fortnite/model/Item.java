package ch.bbcag.fortnite.model;

import lombok.Data;

public @Data class Item {
    private String name;
    private String iconImgURL;
    private String imageURL;
    private String description;
    private String itemId;
    private String rarity;
}
