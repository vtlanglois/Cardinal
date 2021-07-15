package com.edu6.cardinal.panels;

import com.edu6.cardinal.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResourceCard extends JPanel {
    JSONObject jObj;
    JLabel name, desc, author, keywords;
    Resource resource;
    JButton favorite;
    public ResourceCard(Resource resource, JSONObject jObj) {
        this.resource = resource;
        this.jObj = jObj;
        name = new JLabel(resource.getName());
        name.setFont(name.getFont().deriveFont(name.getFont().getStyle() | Font.BOLD));
        desc = new JLabel(resource.getDesc());
        author = new JLabel("By: " + resource.getAuthor());
        keywords = new JLabel(resource.getKeywords().toString());
        favorite = new JButton("⭐");
        favorite.addActionListener(e -> {
            try {
                JSONArray jArr = (JSONArray) new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\json\\saved.json"));
                jArr.add(this.jObj);
                System.out.println(jArr.toJSONString());
                FileWriter fileWriter = new FileWriter("src\\com\\edu6\\cardinal\\json\\saved.json");
                fileWriter.write(jArr.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
////            GridBagConstraints c = new GridBagConstraints();
////            c.gridx = 0;
////            c.gridy = 0;
//            add(name, c);
////            c.gridy = 1;
//            add(keywords,c);
//            c.gridy = 2;
//            add(desc,c);
//            c.gridx=0;
//            c.gridy=2;
//            add(favorite,c);
        add(name);
        add(author);
        add(keywords);
        add(desc);
        add(favorite);
    }

    public List<String> getKeywords() {
        return resource.getKeywords();
    }

    //check if either the name or desc of the resource contains the inputted text
    //String -> boolean
    public boolean containsText(String text) {
        return resource.getName().contains(text) || resource.getDesc().contains(text);
    }
}
