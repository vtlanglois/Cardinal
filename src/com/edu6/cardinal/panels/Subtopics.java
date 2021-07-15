package com.edu6.cardinal.panels;

import com.edu6.cardinal.FrameManager;

import javax.swing.*;

public class Subtopics extends JPanel {

    JButton topic_programming = new JButton("Programming");
    JButton topic_AI = new JButton("AI");
    public Subtopics(String topic) {
        setLookAndFeel();
        topic_programming.addActionListener(e -> {
            ResourceFinder rf = new ResourceFinder(topic);
            FrameManager.switchPanels(rf);
            FrameManager.addPanelToStack(this);
        });
        add(topic_programming);
        add(topic_AI);


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
        System.out.println("ruan");
    }

}