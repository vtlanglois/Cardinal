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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceFinder extends JPanel implements ActionListener {
    List<ResourceCard> resourceCards = new ArrayList<>();
    List<KeywordSelector> keywordSelectors = new ArrayList<>();
    JPanel keywordPanel = new JPanel();
    JPanel resourcePanel = new JPanel();
    JPanel resourcePanelScroller = new JPanel();
    JSONObject currentObj;
    public ResourceFinder(String topic) {
        setLookAndFeel();
        //TODO: maybe make a JSONManager class to handle the file format? or swap to mongoDB?
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\json\\topics.json"));
            currentObj = (JSONObject) obj.get(topic);
            generateKeywordSelectors();
            generateResourceCards();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
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
        add(keywordPanel, BorderLayout.LINE_START);
        add(resourcePanel, BorderLayout.LINE_END);
        //TODO: make searching better, rn its a very rough draft
    }

    private void generateResourceCards() {
        JSONArray jArr = (JSONArray) currentObj.get("resources");
        //go thru jArr, make each JSONObject into a ResourceCard
        for(int i = 0; i<jArr.size(); i++) {
            JSONObject currentResourceCard = (JSONObject) jArr.get(i);
            JSONArray keywordJSONArray = (JSONArray) currentResourceCard.get("keywords");
            String name = (String) currentResourceCard.get("name");
            String desc = (String) currentResourceCard.get("desc");
            String author = (String) currentResourceCard.get("author");
            ArrayList<String> keywords = new ArrayList<>();
            for(Object keyword : keywordJSONArray) {
                keywords.add((String) keyword);
            }
            resourceCards.add(new ResourceCard(new Resource(name, desc, author, keywords), currentResourceCard));
        }
    }

    private void generateKeywordSelectors() {
        JSONArray jArr = (JSONArray) currentObj.get("keywords");
        //go thru jArr, make each JSONObject into a KeywordSelector
        for(int i = 0; i<jArr.size(); i++) {
            JSONObject currentKeywordSelector = (JSONObject) jArr.get(i);
            JSONArray keywordJSONArray = (JSONArray) currentKeywordSelector.get("keywords");
            String category = (String) currentKeywordSelector.get("category");
            ArrayList<String> keywords = new ArrayList<>();
            for(Object keyword : keywordJSONArray) {
                keywords.add((String) keyword);
            }
            keywordSelectors.add(new KeywordSelector(category, keywords, this));
        }

    }

    private void setLookAndFeel() {
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
    class KeywordSelector extends JPanel implements ActionListener{

        private ArrayList<JCheckBox> keywordBoxes = new ArrayList<>();
        private JLabel category;
        private ArrayList<String> selectedKeywords = new ArrayList<>();
        private JButton close;
        public KeywordSelector(String category, ArrayList<String> keywords, ResourceFinder rf) {
            this.category = new JLabel(category);
            this.category.setFont(this.category.getFont().deriveFont(this.category.getFont().getStyle() | Font.BOLD));
            add(this.category);
            setBorder(BorderFactory.createLineBorder(Color.black));
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



}
