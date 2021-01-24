package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FlowLayoutTest extends JFrame {
    //JPanel的默认方式就是流布局
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,80,30));
    JButton jb1 =new JButton("1");
    JButton jb2 =new JButton("2");
    JButton jb3 =new JButton("3");
    JButton jb4 =new JButton("4");
    JButton jb5 =new JButton("5");
    JButton jb6 =new JButton("6");

    public FlowLayoutTest(){
        super("测试流布局");

        Container contentPane =getContentPane();
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);
        contentPane.add(jPanel);

        URL resource =JframeTest.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args){

        new FlowLayoutTest();
    }
}
