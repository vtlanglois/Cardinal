package com.edu6.cardinal;

import javax.swing.*;
import java.util.Hashtable;

public class Manager {
    public static Hashtable<Integer, JFrame> frameHashTable = new Hashtable<>();

    public Manager() {
        frameHashTable.put(1, new Subtopics());
        frameHashTable.put(0, new Topics());
    }
    public static void switchFrame(JFrame currentFrame, int nextFrame) {
        currentFrame.setVisible(false);
        frameHashTable.get(nextFrame).setVisible(true);
    }

    public static void main(String[] args) {
        Manager m = new Manager();
    }
}
