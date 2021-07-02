package com.edu6.cardinal;

import java.util.ArrayList;
import java.util.List;

public class Resource {

    private String name, desc;
    private List<String> keywords = new ArrayList<>();

    public Resource(String name, String desc, List<String> keywords) {
        this.name = name;
        this.desc = desc;
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

    public boolean containsAll(List<String> searchedKeywords) {
        if (keywords.containsAll(searchedKeywords)) {
            return true;
        }
        return false;
    }
}
