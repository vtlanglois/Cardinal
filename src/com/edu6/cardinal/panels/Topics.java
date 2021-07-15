package com.edu6.cardinal.panels;

import com.edu6.cardinal.FrameManager;
import com.edu6.cardinal.Resource;
import com.edu6.cardinal.panels.Subtopics;

import javax.swing.*;
import java.awt.*;

public class Topics extends JPanel {

    JButton topic_computerScience = new JButton("💻 CS");
    JButton topic_math = new JButton("🧮 Math");
    JButton upload = new JButton("⬆Upload");
    JButton favorites = new JButton("Favorites");
    JButton personalResources = new JButton("Your Resources");
    public Topics() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        topic_computerScience.addActionListener(e -> {
            Subtopics st = new Subtopics("computer_science");
            FrameManager.switchPanels(st);
            FrameManager.addPanelToStack(this);
        });

        topic_math.addActionListener(e -> {
            ResourceFinder rf = new ResourceFinder("math");
            FrameManager.switchPanels(rf);
            FrameManager.addPanelToStack(this);
        });
        upload.addActionListener(e -> {
            UploadResource uploadResource = new UploadResource();
            FrameManager.switchPanels(uploadResource);
            FrameManager.addPanelToStack(this);

        });

        favorites.addActionListener(e -> {
            SavedResources savedResources = new SavedResources();
            FrameManager.switchPanels(savedResources);
            FrameManager.addPanelToStack(this);

        });

        personalResources.addActionListener(e -> {
            YourResources yourResources = new YourResources();
            FrameManager.switchPanels(yourResources);
            FrameManager.addPanelToStack(this);
        });
        c.weightx = 0.25;
        c.weighty = 0.25;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 60;
        c.ipady = 10;
        add(topic_computerScience, c);
        c.gridx = 2;
        c.gridy = 0;
        add(topic_math, c);
        c.gridx = 0;
        c.gridy = 1;
        add(upload, c);
        c.gridx = 2;
        add(favorites, c);
        c.gridx = 3;
        add(personalResources, c);


    }

}
