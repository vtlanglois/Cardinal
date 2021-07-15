package com.edu6.cardinal;

import java.util.ArrayList;
import java.util.List;

public class Resource {

    private String name, desc, author;
    private List<String> keywords = new ArrayList<>();

    public Resource(String name, String desc, String author, List<String> keywords) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean containsAll(List<String> searchedKeywords) {
        if (keywords.containsAll(searchedKeywords)) {
            return true;
        }
        return false;
    }
}
