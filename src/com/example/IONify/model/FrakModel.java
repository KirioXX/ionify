package com.example.IONify.model;

/**
 * Created by ben on 26.06.14.
 */
public class FrakModel {
    private String title;
    private String content;

    public FrakModel(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
