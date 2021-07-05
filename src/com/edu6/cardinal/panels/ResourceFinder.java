package com.edu6.cardinal.panels;

import com.edu6.cardinal.Resource;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceFinder extends JPanel implements ActionListener {
    List<ResourceCard> resourceCards = new ArrayList<>();
    List<KeywordSelector> keywordSelectors = new ArrayList<>();
    JPanel keywordPanel = new JPanel();
    JPanel resourcePanel = new JPanel();

    public ResourceFinder(String topic) {
        setLookAndFeel();
        //TODO I am rusty with JSON, and this is the first time im using them in an non-Android program. will double check if efficient -V
        //TODO: will need to rewrite JSON system at some point, especially when more data will be in each file -V
        //TODO: maybe make a JSONManager class to handle the file format? or swap to mongoDB?
        try {
            Object obj = new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\keywords.json"));
            JSONObject jObj = (JSONObject) obj;
            JSONArray jArr = (JSONArray) jObj.get(topic);
            //go thru jArr, make JSONObject into a KeywordSelector
            for(int i = 0; i<jArr.size(); i++) {
                JSONObject currentObj = (JSONObject) jArr.get(i);
                JSONArray keywordJSONArray = (JSONArray) currentObj.get("keywords");
                ArrayList<String> keywords = new ArrayList<>();
                //add keywords to a seperate ArrayList
                //TODO: see if theres a way to do this step in one-line
                for(int j = 0; j<keywordJSONArray.size();j++) {
                    keywords.add((String) keywordJSONArray.get(j));
                }
                String category = (String) currentObj.get("category");
                keywordSelectors.add(new KeywordSelector(category, keywords, this));
            }
            obj =  new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\resources.json"));
            jObj = (JSONObject) obj;
            jArr = (JSONArray) jObj.get("resources");
            //do the same, but with resourcecards
            for(int i = 0; i <jArr.size(); i++) {
                System.out.println(jArr.toJSONString());
                JSONObject currentObj = (JSONObject) jArr.get(i);
                JSONArray keywordJSONArray = (JSONArray) currentObj.get("keywords");
                List<String> keywords = new ArrayList<>();
                for(int j = 0; j<keywordJSONArray.size();j++) {
                    keywords.add((String) keywordJSONArray.get(j));
                }
                String name = (String) currentObj.get("name");
                String desc = (String) currentObj.get("desc");
                resourceCards.add(new ResourceCard(new Resource(name, desc, keywords)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JTextField textField = new JTextField();
        keywordPanel.setLayout(new BoxLayout(keywordPanel, BoxLayout.PAGE_AXIS));
        resourcePanel.setLayout(new BoxLayout(resourcePanel, BoxLayout.PAGE_AXIS));
        //add all KeywordSelectors to their panel
        for(KeywordSelector keywordSelector : keywordSelectors) {
            keywordPanel.add(keywordSelector);
        }
        //add all ResourceCards to their panel
        for(ResourceCard resourceCard : resourceCards) {
            resourcePanel.add(resourceCard);
        }
        //add objects
        add(textField, BorderLayout.NORTH);
        add(keywordPanel, BorderLayout.LINE_START);
        add(resourcePanel, BorderLayout.LINE_END);
        //TODO: make searching better, rn its a very rough draft
        textField.addActionListener(e -> {
            for (ResourceCard resourceCard : resourceCards) {
                if (resourceCard.containsText(textField.getText())) {
                    resourceCard.setVisible(true);
                } else {
                    resourceCard.setVisible(false);
                }
            }
        });
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("finding!");
        List<String> searchedKeywords = new ArrayList<>();

        //add all keywords selected in each keyword selector onto a sinlge list
        for(KeywordSelector keywordSelector : keywordSelectors) {
            searchedKeywords.addAll(keywordSelector.findSelectedKeywords());
        }

        System.out.println(searchedKeywords.toString());

        //check if each ResourceCard contains all selected keywords
        //if so, set it to be visible
        for (ResourceCard resourceCard : resourceCards) {
            if(resourceCard.getKeywords().containsAll(searchedKeywords)) {
                resourceCard.setVisible(true);
            } else {
                resourceCard.setVisible(false);
            }
        }

    }


    //TODO theres probably a better way to implement this, esp so that we dont have to pass the instance of ResourceFinder
    public class KeywordSelector extends JPanel implements ActionListener{

        private ArrayList<JCheckBox> keywordBoxes = new ArrayList<>();
        private JLabel category;
        private ArrayList<String> selectedKeywords = new ArrayList<>();
        private JButton close;
        public KeywordSelector(String category, ArrayList<String> keywords, ResourceFinder rf) {
            this.category = new JLabel(category);
            this.category.setFont(this.category.getFont().deriveFont(this.category.getFont().getStyle() | Font.BOLD));
            add(this.category);
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            close = new JButton("V");
            close.addActionListener(this);
            add(close);
            for(int i = 0; i<keywords.size(); i++) {
                JCheckBox box = new JCheckBox(keywords.get(i));
                box.addActionListener(rf);
                keywordBoxes.add(box);
                add(keywordBoxes.get(i));

            }

        }

        //search through each JCheckBox, see if it is selected
        //if so, add text to an ArrayList, return list
        public ArrayList<String> findSelectedKeywords() {
            selectedKeywords.clear();
            System.out.println("searching!");
            for(JCheckBox box : keywordBoxes) {
                if (box.isSelected()) {
                    selectedKeywords.add(box.getText());
                }
            }
            return selectedKeywords;
        }


        //check if dropdown close button has been pressed or not
        //determine if you want to show JCheckBoxes or not
        @Override
        public void actionPerformed(ActionEvent e) {
            if (close.getText().equals("V")) {
                close.setText("^");
                for (JCheckBox box : keywordBoxes) {
                    box.setVisible(false);
                }
            } else {
                close.setText("V");
                for (JCheckBox box : keywordBoxes) {
                    box.setVisible(true);
                }
            }
        }
    }

    public class ResourceCard extends JPanel {
        JLabel name, desc, keywords;
        Resource resource;
        public ResourceCard(Resource resource) {
            this.resource = resource;
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            name = new JLabel(resource.getName());
            name.setFont(name.getFont().deriveFont(name.getFont().getStyle() | Font.BOLD));
            desc = new JLabel(resource.getDesc());
            keywords = new JLabel(resource.getKeywords().toString());
            add(name);
            add(keywords);
            add(desc);
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

}
