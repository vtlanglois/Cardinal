package com.edu6.cardinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ButtonTest extends JPanel implements ActionListener {

    String title;
    JButton button;
    public ButtonTest(String title) {
        this.title = title;
        button = new JButton(this.title);
        add(button);
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        ButtonTest newTest = new ButtonTest(r.nextInt(1000)+"");
        FrameTest.switchPanels(newTest);
        System.out.println("CLicked!" + title);
    }
}
