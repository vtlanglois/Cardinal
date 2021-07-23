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
    JPanel info = new JPanel(), buttons= new JPanel(), image= new JPanel();

    public ResourceCard(Resource resource, JSONObject jObj) {
        this.resource = resource;
        this.jObj = jObj;
        name = new JLabel(resource.getName());
        name.setFont(name.getFont().deriveFont(name.getFont().getStyle() | Font.BOLD));
        desc = new JLabel(resource.getDesc());
        author = new JLabel("By: " + resource.getAuthor());
        author.setFont(author.getFont().deriveFont(author.getFont().getStyle() | Font.BOLD));
        keywords = new JLabel(resource.getKeywords().toString());
        favorite = new JButton("⭐");
        favorite.addActionListener(e -> {
            try {
                //on click, add resource to favorties
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
        setLayout(new BorderLayout());
        //set up GUI
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        image.setLayout(new BoxLayout(image, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        image.add(new JLabel(new ImageIcon("src\\com\\edu6\\cardinal\\default.png")));
        image.add(author);
        info.add(name);
        info.add(keywords);
        info.add(desc);
        buttons.add(favorite);
        add(image, BorderLayout.LINE_START);
        add(info, BorderLayout.CENTER);
        add(buttons, BorderLayout.LINE_END);

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
