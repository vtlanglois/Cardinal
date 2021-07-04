package com.edu6.cardinal.panels;

import com.edu6.cardinal.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ResourceFinder extends JPanel implements ActionListener {
    String[][] items = {{"Block Coding", "Python", "JavaScript", "Java", "C", "C++"},
                        {"Elementary", "Middle", "High"} };
    JLabel label3 = new JLabel("Resources");
    ArrayList<Resource> resources = new ArrayList<>();
    List<KeywordSelector> keywordSelectors = new ArrayList<>();

    public ResourceFinder() {
        setLookAndFeel();
        KeywordSelector key1 = new KeywordSelector("Languages", items[0], this);
        KeywordSelector key2 = new KeywordSelector("Age Range", items[1], this);
        keywordSelectors.add(key1);
        keywordSelectors.add(key2);
        for (int i = 0; i<3; i++) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Python");
            if(i==2) {
                strings.add("High");
            }
            Resource r = new Resource(i+"A", i+"B", strings);
            resources.add(r);
            label3.setText(label3.getText()+ ", " + resources.get(i).getName());
        }
        key1.add(key2);
        add(key1);
        JPanel jp3 = new JPanel();
        jp3.add(label3);
        key2.add(jp3);
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
        public KeywordSelector(String category, String[] keywords, ResourceFinder rf) {
            this.category = new JLabel(category);
            this.category.setFont(this.category.getFont().deriveFont(this.category.getFont().getStyle() | Font.BOLD));
            add(this.category);
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for(int i = 0; i<keywords.length; i++) {
                JCheckBox box = new JCheckBox(keywords[i]);
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
