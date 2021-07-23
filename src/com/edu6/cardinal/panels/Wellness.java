package com.edu6.cardinal.panels;

import javax.swing.*;
import java.awt.*;

public class Wellness extends JPanel {
    JButton talk = new JButton("Talk with a mental health assistant");
    JButton help = new JButton("Help students via chat");
    JButton read = new JButton("Wellbeing Resources");

    public Wellness() {
        //set up GUI
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.25;
        c.weighty = 0.25;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 60;
        c.ipady = 10;
        add(talk, c);
        c.gridx = 0;
        c.gridy = 1;
        add(help, c);
        c.gridx = 0;
        c.gridy = 2;
        add(read, c);
    }
}
