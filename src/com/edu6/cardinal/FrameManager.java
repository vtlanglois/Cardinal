package com.edu6.cardinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EmptyStackException;
import java.util.Stack;

public class FrameManager {
    public  static JFrame frame; //the frame for the whole application
    public static JPanel currentPanel; //the JPanel to be swapped around
    public static Stack<JPanel> previousPanels = new Stack<>(); //stores previous panels for back navigation
    static JButton panels = new JButton("No panels"); //for testing purposes
    public FrameManager(JPanel panel) {
        //set up frame
        setLookAndFeel();
        frame = new JFrame("Cardinal");
        this.currentPanel = panel;
        frame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(currentPanel);
        //the following are for testing purposes
        frame.add(panels, BorderLayout.PAGE_START);
        panels.addActionListener(e -> {
            try {
                switchPanels(previousPanels.pop());
                panels.setText(previousPanels.size() + "");
            } catch (EmptyStackException a){
                System.out.println("nope");
            }
        });
        frame.setVisible(true);
        System.out.println("ready!");
    }

    //switch the current panel with a new panel
    //JPanel -> Void
    public static void switchPanels(JPanel newPanel) {
        frame.getContentPane().remove(currentPanel);
        currentPanel = newPanel;
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();
        setLookAndFeel();

    }

    //add a previous panel to the stack for later use
    //JPanel -> Void
    public static void addPanelToStack(JPanel panel) {
        previousPanels.add(panel);
        panels.setText(previousPanels.size()+"");

    }


    //set appearance of the frame
    //TODO: figure out why its not working for any panel after first;
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
}
