package com.edu6.cardinal.panels;

import com.edu6.cardinal.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SavedResources extends JPanel {

    ArrayList<ResourceCard> resourceCards = new ArrayList<>();
    JPanel resourcePanel = new JPanel();
    public SavedResources() {
        try {
            //get all saved resources from database
            JSONArray jArr = (JSONArray) new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\json\\saved.json"));
            for(int i = 0; i<jArr.size(); i++) {
                JSONObject currentResourceCard = (JSONObject) jArr.get(i);
                JSONArray keywordJSONArray = (JSONArray) currentResourceCard.get("keywords");
                //using JSONObject, create a new Resource Card
                String name = (String) currentResourceCard.get("name");
                String desc = (String) currentResourceCard.get("desc");
                String author = (String) currentResourceCard.get("author");
                ArrayList<String> keywords = new ArrayList<>();
                for(Object keyword : keywordJSONArray) {
                    keywords.add((String) keyword);
                }
                resourceCards.add(new ResourceCard(new Resource(name, desc, author, keywords), currentResourceCard));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        resourcePanel.setLayout(new BoxLayout(resourcePanel, BoxLayout.PAGE_AXIS));
        //add all ResourceCards to their panel
        for(ResourceCard resourceCard : resourceCards) {
            resourceCard.favorite.setEnabled(false);
            resourcePanel.add(resourceCard);
        }
        setLayout(new BorderLayout());
        add(new JScrollPane(resourcePanel), BorderLayout.CENTER);
    }
}
