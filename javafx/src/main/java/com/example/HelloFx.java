package com.example;

import javax.swing.*;
import java.awt.*;

public class HelloFx {
    JFrame myFrame = null;

    public static void main(String[] a) {
        (new HelloFx()).test();
    }

    private void test() {
        String text = """
                committed by HeBo</span> <a href="mailto:qq111372757@gmail.com">&lt;qq111372757@gmail.com&gt;</a><span style="color:#808080"/> on 2/6/21 at 12:00 PM""";
        myFrame = new JFrame("JEditorPane Test");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(960, 540);
        myFrame.setLocation(1320, 1280);
        JEditorPane myPane = new JEditorPane();
        myPane.setContentType("text/html");
        myPane.setText(text);
        myPane.setFont(new Font(null, Font.BOLD, 4000));
        myFrame.setContentPane(myPane);
        myFrame.setVisible(true);
    }
}