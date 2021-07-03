package com.edu6.cardinal;

import javax.swing.*;

public class FrameTest {
    public static JPanel currentPanel;
    public  static JFrame frame;
    public FrameTest(JPanel panel) {
        setLookAndFeel();
        frame = new JFrame("FrameTest");
        this.currentPanel = panel;
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(currentPanel);
        frame.setVisible(true);
    }

    public static void switchPanels(JPanel newPanel) {
        frame.getContentPane().removeAll();
        currentPanel = newPanel;
        frame.getContentPane().add(currentPanel);
        frame.revalidate();
        frame.repaint();

    }

    public static void main(String[] args) {
//        ButtonTest bt = new ButtonTest("1");
        Topics bt = new Topics();
        FrameTest ft = new FrameTest(bt);
    }

    //set appearance of the frame
    private  void setLookAndFeel() {
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
