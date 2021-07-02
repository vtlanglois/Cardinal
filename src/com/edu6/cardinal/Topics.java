package com.edu6.cardinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Topics extends JFrame {

    JButton topic_computerScience = new JButton("💻 CS");
    JButton topic_math = new JButton("🧮 Math");
    public Topics() {
        super("Cardinal - Topics");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        topic_computerScience.addActionListener(e -> {
            Subtopics t = new Subtopics();
            t.setVisible(true);
            //this.setVisible(false);
            dispose();
//            Manager.switchFrame(this, 1);

        });
        pane.add(topic_computerScience, BorderLayout.PAGE_END);
        pane.add(topic_math, BorderLayout.PAGE_START);
        add(pane);
        setVisible(true);


    }

    //set appearance of the frame
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
        Topics t = new Topics();
    }
}
