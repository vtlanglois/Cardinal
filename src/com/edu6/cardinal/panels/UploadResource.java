package com.edu6.cardinal.panels;

import com.edu6.cardinal.FrameManager;
import com.edu6.cardinal.HintTextFieldUI;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class UploadResource extends JPanel {

    JTextField resourceName = new JTextField(), resourceAuthor = new JTextField(), resourceDesc = new JTextField(), resourceKeywords = new JTextField();
    String[] topicsList = {"Computer Science", "Math"};
    JComboBox topics = new JComboBox(topicsList);
    JButton upload = new JButton("Upload");
    public UploadResource() {
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        resourceName.setToolTipText("Name");
//        resourceAuthor.setToolTipText("Author");
//        resourceDesc.setToolTipText("Desc");
//        resourceKeywords.setToolTipText("Keywords");
//        setLayout(new GridLayout(0,2));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        add(new JLabel("Title: "));
//        add(resourceName);
//        add(new JLabel("Author: "));
//        add(resourceAuthor);
//        add(new JLabel("Desc: "));
//        add(resourceDesc);
//        add(new JLabel("Keywords: "));
//        add(resourceKeywords);
//        add(topics);
//        add(upload);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Title: "), c);
        c.gridx = 1;
        add(resourceName, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Author: "), c);
        c.gridx = 1;
        add(resourceAuthor, c);
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Desc: "), c);
        c.gridx = 1;
        add(resourceDesc, c);
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Keywords: "), c);
        c.gridx = 1;
        add(resourceKeywords, c);
        c.gridx = 0;
        c.gridy = 4;
        add(new JLabel("Topic: "));
        c.gridx = 1;
        add(topics, c);
        c.gridx = 0;
        c.gridy = 5;
        add(upload, c);
        upload.addActionListener(e -> {
            try {
                JSONArray jArr = (JSONArray) new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\json\\uploads.json"));
                JSONObject newUpload = new JSONObject();
                newUpload.put("name", resourceName.getText());
                newUpload.put("author", resourceAuthor.getText());
                newUpload.put("desc", resourceDesc.getText());
                newUpload.put("topic", (String) topics.getSelectedItem());
                JSONArray keywords = new JSONArray();
                keywords.addAll(Arrays.asList(resourceKeywords.getText().split(", ")));
                newUpload.put("keywords", keywords);
                jArr.add(newUpload);
                System.out.println(jArr.toJSONString());
                FileWriter fileWriter = new FileWriter("src\\com\\edu6\\cardinal\\json\\uploads.json");
                fileWriter.write(jArr.toJSONString());
                fileWriter.flush();
                fileWriter.close();

                //send back to previous panel
                Topics st = new Topics();
                FrameManager.switchPanels(st);
                FrameManager.removeFromBackStack();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });
    }
}
