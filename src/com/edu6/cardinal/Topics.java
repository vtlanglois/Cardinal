package com.edu6.cardinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Topics extends JPanel {

    JButton topic_computerScience = new JButton("💻 CS");
    JButton topic_math = new JButton("🧮 Math");
    public Topics() {
//        super("Cardinal - Topics");
//        setSize(300, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel pane = new JPanel();
        topic_computerScience.addActionListener(e -> {
            Subtopics st = new Subtopics();
            FrameTest.switchPanels(st);
            st = null;
        });
        add(topic_computerScience, BorderLayout.PAGE_END);
        add(topic_math, BorderLayout.PAGE_START);


    }



//    public static void main(String[] args) {
//        setLookAndFeel();
//        Topics t = new Topics();
//    }
}
