package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;



public class SpringLayoutTest extends JFrame {
    SpringLayout springLayout= new SpringLayout();
    JPanel jPanel =new JPanel(springLayout);

    JLabel titleLabel =new JLabel("文章标题：");
    JTextField titleTxt =new JTextField();
    JLabel authorLabel =new JLabel("作者：");
    JTextField authorTxt =new JTextField();
    JLabel contLabel =new JLabel("请输入内容：");
    JTextArea contArea =new JTextArea(4,10);

    public SpringLayoutTest(){
        super("测试弹簧布局");

        Container contentPane =getContentPane();
        jPanel.add(titleLabel);
        titleTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(titleTxt);
        jPanel.add(authorLabel);
        authorTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(authorTxt);
        jPanel.add(contLabel);
        jPanel.add(contArea);

        jPanel.setBackground(Color.black);

        //SpringLayout.Constraints :使用弹簧布局的容器里面的布局约束，每个组件对应一个
        //Sping可以理解为一个能进行四则运算的整数
        Spring titleLableWidth= Spring.width(titleLabel);
        Spring titleTexWidth =Spring.width(titleTxt);
        Spring spaceWidth=Spring.constant(20);
        Spring childWidth=Spring.sum(Spring.sum(titleLableWidth,titleTexWidth),spaceWidth);
        int offsetX=childWidth.getValue()/2;

        SpringLayout.Constraints titleLableC= springLayout.getConstraints(titleLabel);
        springLayout.putConstraint(SpringLayout.WEST,titleLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,jPanel);
        //titleLableC.setX(Spring.constant(100));
        titleLableC.setY(Spring.constant(50));
        //设置约束的第一种写法 比较复杂
        //设置标题文本框：titleTxt 东边距离titleLabel的西边20px，北边相同
        SpringLayout.Constraints titleTxtC=springLayout.getConstraints(titleTxt);
        Spring titleLabelEastSpring =titleLableC.getConstraint(SpringLayout.EAST);
        titleTxtC.setConstraint(SpringLayout.WEST,Spring.sum(titleLabelEastSpring,Spring.constant(20)));
        titleTxtC.setConstraint(SpringLayout.NORTH,titleLableC.getConstraint(SpringLayout.NORTH));

        //设置约束的第二种写法
        //e1:要设置组件的那个边界
        //c1 要设置的组件
        //pad 距离值
        //e2 参照的组件的边界名
        //c2 参考物
        //设置authorLabel东边和titleLabel对齐 北边距离titleLabel南边20px
        springLayout.putConstraint(SpringLayout.EAST,authorLabel,0,SpringLayout.EAST,titleLabel);
        springLayout.putConstraint(SpringLayout.NORTH,authorLabel,20,SpringLayout.SOUTH,titleLabel);
        //设置authorTxt 西边距离titleLabel的东边20px，北边相同
        springLayout.putConstraint(SpringLayout.WEST,authorTxt,20,SpringLayout.EAST,authorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,authorTxt,0,SpringLayout.NORTH,authorLabel);
        //设置contLabel
        springLayout.putConstraint(SpringLayout.EAST,contLabel,0,SpringLayout.EAST,authorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,contLabel,20,SpringLayout.SOUTH,authorLabel);
        //设置contArea
        springLayout.putConstraint(SpringLayout.WEST,contArea,20,SpringLayout.EAST,contLabel);
        springLayout.putConstraint(SpringLayout.NORTH,contArea,0,SpringLayout.NORTH,contLabel);
        //设置contArea南边东边参照jPanel设置
        springLayout.putConstraint(SpringLayout.EAST,contArea,-20,SpringLayout.EAST,jPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,contArea,-20,SpringLayout.SOUTH,jPanel);
        


        contentPane.add(jPanel);
        URL resource =JframeTest.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setVisible(true);
    }



    public static void main(String[] args){

        new SpringLayoutTest();
    }
}
