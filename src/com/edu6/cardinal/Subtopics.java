package com.edu6.cardinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subtopics extends JPanel {

    JButton topic_programming = new JButton("Programming");
    JButton topic_AI = new JButton("AI");
    public Subtopics() {
//        super("Cardinal - CS Subtopics");
//        setSize(300, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel pane = new JPanel();
        topic_programming.addActionListener(e -> {
//            ButtonTest bt = new ButtonTest("AfterSubTopics");
            ResourceFinder rf = new ResourceFinder();
            FrameTest.switchPanels(rf);
        });
        add(topic_programming);
        add(topic_AI);


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

    public static void main(String[] args) {
        setLookAndFeel();
        Subtopics t = new Subtopics();
    }
}

