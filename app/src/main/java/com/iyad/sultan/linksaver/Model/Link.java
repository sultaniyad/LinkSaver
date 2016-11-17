package com.iyad.sultan.linksaver.Model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by sultan on 11/16/16.
 */

public class Link extends RealmObject {

    private String Title;
    private int Category;
    private String link;
    private boolean isImportant;
    private Date date;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
