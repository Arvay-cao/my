package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JframeTest extends JFrame{
    JButton jButton;
    public JframeTest(){
        super("这是FRAME的标题");
        JButton jButton =new JButton("按钮");
        Container contentPane =getContentPane();
        contentPane.add(jButton);

        //设置窗体图标
        URL resource =JframeTest.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(600,400);
        //居中
        setLocationRelativeTo(null);
        //关闭退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //大小不可变
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args){
        new JframeTest();
    }
}
