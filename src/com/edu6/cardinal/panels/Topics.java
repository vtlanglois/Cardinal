package com.edu6.cardinal.panels;

import com.edu6.cardinal.FrameManager;
import com.edu6.cardinal.panels.Subtopics;

import javax.swing.*;
import java.awt.*;

public class Topics extends JPanel {

    JButton topic_computerScience = new JButton("💻 CS");
    JButton topic_math = new JButton("🧮 Math");
    public Topics() {
        topic_computerScience.addActionListener(e -> {
            Subtopics st = new Subtopics();
            FrameManager.switchPanels(st);
            FrameManager.addPanelToStack(this);
        });
        add(topic_computerScience, BorderLayout.PAGE_END);
        add(topic_math, BorderLayout.PAGE_START);


    }

}
