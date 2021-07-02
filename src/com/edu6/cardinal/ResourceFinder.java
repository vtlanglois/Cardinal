package com.edu6.cardinal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ResourceFinder extends JFrame implements ActionListener {
    String[] items = {"Block Coding", "Python", "JavaScript", "Java", "C", "C++"};
    ArrayList<JCheckBox> boxes = new ArrayList<>();
    String[] items2 = {"Programming", "Machine Learning", "Artificial Intelligence"};
    ArrayList<JCheckBox> boxes2 = new ArrayList<>();
    JLabel label = new JLabel("Items");
    JLabel label2 = new JLabel("Items2");
    JLabel label3 = new JLabel("Resources");
    ArrayList<Resource> resources = new ArrayList<>();

    public ResourceFinder() {
        super("Cardinal - CS Subtopics");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.add(label2);
        panel.add(label);

        for(int i =0; i<items.length; i++) {
            JCheckBox box = new JCheckBox(items[i]);
            box.addActionListener(this);
            boxes.add(box);
            panel.add(boxes.get(i));
        }
        for(int i =0; i<items2.length; i++) {
            JCheckBox box = new JCheckBox(items2[i]);
            box.addActionListener(this);
            boxes2.add(box);
            panel2.add(boxes2.get(i));
        }
        for (int i = 0; i<3; i++) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Python");
            if(i==2) {
                strings.add("Programming");
            }
            Resource r = new Resource(i+"A", i+"B", strings);
            resources.add(r);
            label3.setText(label3.getText()+ ", " + resources.get(i).getName());
        }
        panel.add(panel2);
        add(panel);
        JPanel jp3 = new JPanel();
        jp3.add(label3);
        panel2.add(jp3);
        setVisible(true);
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
        AbstractButton absButton = (AbstractButton) e.getSource();
        List<String> searchedKeywords = new ArrayList<>();
        label3.setText("");
        for (JCheckBox box: boxes) {
            if (box.isSelected()) {
                searchedKeywords.add(box.getText());
            }
        }

        for (JCheckBox box: boxes2) {
            if (box.isSelected()) {
                searchedKeywords.add(box.getText());
            }
        }
        for (Resource resource : resources) {
            if (resource.containsAll(searchedKeywords)) {
                label3.setText(label3.getText() + ", " + resource.getName());
            }
        }
        label.setText(searchedKeywords.toString());
    }

    public static void main(String[] args) {
        ResourceFinder rf = new ResourceFinder();
    }



}
