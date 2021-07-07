package ch.bbcag.fortnite.model;

import android.media.Image;

import lombok.Data;

public @Data class News {
    private String tabTitle;
    private String title;
    private String body;
    private String image;
}
