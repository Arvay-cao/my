package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BorderLayoutTest extends JFrame {

    JButton northBtn =new JButton("北边的button");
    JLabel southlabel =new JLabel("南边的label");
    JRadioButton westRadioBtn =new JRadioButton("男");
    JTextArea eastArea =new JTextArea("输入内容",10,20);
    JButton centerBtn = new JButton("中间的Button");

    public BorderLayoutTest(){
        super("边界布局测试");

        Container contentPane =getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(northBtn,BorderLayout.NORTH);
        contentPane.add(southlabel,BorderLayout.SOUTH);
        southlabel.setPreferredSize(new Dimension(10,20));
        contentPane.add(westRadioBtn,BorderLayout.WEST);
        westRadioBtn.setPreferredSize(new Dimension(200,0));
        contentPane.add(eastArea,BorderLayout.EAST);
        contentPane.add(centerBtn,BorderLayout.CENTER);


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
        new BorderLayoutTest();
    }
}
