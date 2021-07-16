package com.edu6.cardinal.panels;

import com.edu6.cardinal.FrameManager;

import javax.swing.*;
import java.awt.*;

public class Subtopics extends JPanel {

    JButton computer_science = new JButton("Computer Science");
    JButton biology = new JButton("Biology");
    JButton chem = new JButton("Chemistry");
    JButton phys = new JButton("Physics");
    public Subtopics(String topic) {
        setLookAndFeel();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        computer_science.addActionListener(e -> {
            ResourceFinder rf = new ResourceFinder(topic);
            FrameManager.switchPanels(rf);
            FrameManager.addPanelToStack(this);
        });
        c.weightx = 0.25;
        c.weighty = 0.25;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 60;
        c.ipady = 10;
        add(computer_science, c);
        c.gridx = 0;
        c.gridy = 1;
        add(biology, c);
        c.gridx = 1;
        c.gridy = 0;
        add(chem, c);
        c.gridx = 1;
        c.gridy = 1;
        add(phys, c);

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