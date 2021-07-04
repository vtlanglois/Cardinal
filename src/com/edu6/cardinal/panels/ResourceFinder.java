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
    String[][] items = {{"Block Coding", "Python", "JavaScript", "Java", "C", "C++"},
                        {"Elementary", "Middle", "High"} };
    ArrayList<String> dummy = new ArrayList<>();
    JLabel label3 = new JLabel("Resources");
    ArrayList<Resource> resources = new ArrayList<>();
    List<KeywordSelector> keywordSelectors = new ArrayList<>();
    JPanel keywordPanel = new JPanel();


    public ResourceFinder() {
        setLookAndFeel();
        //TODO I am rusty with JSON, and this is the first time im using them in an non-Android program. will double check if efficient -V
        try {
            Object obj = new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\jsontest.json"));
            JSONObject jObj = (JSONObject) obj;
            JSONArray jArr = (JSONArray) jObj.get("computerScience");
            for(int i = 0; i<jArr.size(); i++) {
                JSONObject currentObj = (JSONObject) jArr.get(i);
                JSONArray keywordJSONArray = (JSONArray) currentObj.get("keywords");
                ArrayList<String> keywords = new ArrayList<>();
                for(int j = 0; j<keywordJSONArray.size();j++) {
                    keywords.add((String) keywordJSONArray.get(j));
                }
                String category = (String) currentObj.get("category");
                keywordSelectors.add(new KeywordSelector(category, keywords, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        keywordPanel.setLayout(new BoxLayout(keywordPanel, BoxLayout.PAGE_AXIS));
        for(KeywordSelector keywordSelector : keywordSelectors) {
            keywordPanel.add(keywordSelector);
        }
        //TODO: Rework code
        for (int i = 0; i<3; i++) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Python");
            if(i==2) {
                strings.add("High");
            }
            if(i==1) {
                strings.add("Free");
            }
            Resource r = new Resource(i+"A", i+"B", strings);
            resources.add(r);
            label3.setText(label3.getText()+ ", " + resources.get(i).getName());
        }
        add(keywordPanel, BorderLayout.LINE_START);
        add(label3, BorderLayout.LINE_END);

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
        AbstractButton absButton = (AbstractButton) e.getSource();
        List<String> searchedKeywords = new ArrayList<>();
        label3.setText("");

        for(KeywordSelector keywordSelector : keywordSelectors) {
            searchedKeywords.addAll(keywordSelector.findSelectedKeywords());
        }

        System.out.println(searchedKeywords.toString());

        for (Resource resource : resources) {
            if (resource.containsAll(searchedKeywords)) {
                label3.setText(label3.getText() + ", " + resource.getName());
            }
        }
    }


    //TODO theres probably a better way to implement this, esp so that we dont have to pass the instance of ResourceFinder
    public class KeywordSelector extends JPanel {

        private ArrayList<JCheckBox> keywordBoxes = new ArrayList<>();
        private JLabel category;
        private ArrayList<String> selectedKeywords = new ArrayList<>();
        public KeywordSelector(String category, ArrayList<String> keywords, ResourceFinder rf) {
            this.category = new JLabel(category);
            this.category.setFont(this.category.getFont().deriveFont(this.category.getFont().getStyle() | Font.BOLD));
            add(this.category);
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for(int i = 0; i<keywords.size(); i++) {
                JCheckBox box = new JCheckBox(keywords.get(i));
                box.addActionListener(rf);
                keywordBoxes.add(box);
                add(keywordBoxes.get(i));

            }

        }

        public ArrayList<JCheckBox> getKeywordBoxes() {
            return keywordBoxes;
        }

        public void setKeywordBoxes(ArrayList<JCheckBox> keywordBoxes) {
            this.keywordBoxes = keywordBoxes;
        }

        public ArrayList<String> getSelectedKeywords() {
            return selectedKeywords;
        }

        public void setSelectedKeywords(ArrayList<String> selectedKeywords) {
            this.selectedKeywords = selectedKeywords;
        }

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


    }

}
