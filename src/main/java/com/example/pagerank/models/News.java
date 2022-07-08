package com.example.pagerank.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class News {
    private String title;
    private int views;
    private String author;
    private Date time;
    private String text;
    private double rank;

    public News(String title, int views, String author, String text){
        this.title = title;
        this.views = views;
        this.author = author;
        this.time = new Date();
        this.text = text;
    }
}
