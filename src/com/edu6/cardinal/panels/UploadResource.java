package com.edu6.cardinal.panels;

import com.edu6.cardinal.HintTextFieldUI;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UploadResource extends JPanel {

    JTextField resourceName = new JTextField(), resourceAuthor = new JTextField();
    JButton upload = new JButton("Upload");
    public UploadResource() {
        resourceName.setToolTipText("Name");
        resourceName.addActionListener(e -> {
            System.out.println("name!");
        });
        resourceAuthor.setToolTipText("Author");
        resourceName.setEditable(true);
        upload.addActionListener(e -> {
            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("src\\com\\edu6\\cardinal\\json\\uploads.json"));
                JSONArray jArr = (JSONArray) obj.get("uploads");
                JSONObject newUpload = new JSONObject();
                newUpload.put("name", resourceName.getText());
                newUpload.put("author", resourceAuthor.getText());
                jArr.add(newUpload);
                FileWriter fileWriter = new FileWriter("src\\com\\edu6\\cardinal\\json\\uploads.json");
                fileWriter.write(jArr.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            System.out.println("uploaded!");
        });
        add(resourceName);
        add(resourceAuthor);
        add(upload);
    }
}
