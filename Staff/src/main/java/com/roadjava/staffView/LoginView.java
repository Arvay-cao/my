package com.roadjava.staffView;

import com.roadjava.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoginView extends JFrame{

    JLabel nameLabel=new JLabel("职工信息管理系统",JLabel.CENTER);
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel =new JPanel(springLayout);
    JLabel userNameLabel =new JLabel("账号");
    JTextField userTxt =new JTextField();
    JLabel pwdLabel =new JLabel("密码");
    JPasswordField pwdFiled=new JPasswordField();
    JButton loginBtn=new JButton("登陆");
    JButton resetBtn=new JButton("重置");
    JButton registerBtn= new JButton("注册");

    LoginHandler loginHandler;

    public LoginView() {
        super("登陆界面——职工信息管理系统");

        loginHandler =new LoginHandler(this);

        Container contentPane= getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont =new Font("楷体",Font.PLAIN,20);

        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdFiled.setPreferredSize(new Dimension(200,30));
        pwdFiled.setFont(centerFont);
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        registerBtn.setFont(centerFont);

        //把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdFiled);
        centerPanel.add(loginBtn);
        centerPanel.add(resetBtn);
        centerPanel.add(registerBtn);

        //按钮事件处理
        loginBtn.addActionListener(loginHandler);
        resetBtn.addActionListener(loginHandler);
        registerBtn.addActionListener(loginHandler);

        //弹簧布局
        layoutCenter();

        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        //设置窗体图标
        URL resource =LoginView.class.getClassLoader().getResource("Icon.png");
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
    //弹簧布局
    private void layoutCenter() {
        //SpringLayout.Constraints :使用弹簧布局的容器里面的布局约束，每个组件对应一个
        //Sping可以理解为一个能进行四则运算的整数

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),Spring.constant(20));
        int offsetX= childWidth.getValue() /2;

        //布局userNameLabel
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        //布局useerTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        //布局pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        //布局pwdFiled
        springLayout.putConstraint(SpringLayout.WEST,pwdFiled,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdFiled,0,SpringLayout.NORTH,pwdLabel);
        //布局loginBtn与resetBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,60,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
        //registerBtn
        springLayout.putConstraint(SpringLayout.WEST,registerBtn,20,SpringLayout.EAST,resetBtn);
        springLayout.putConstraint(SpringLayout.NORTH,registerBtn,100,SpringLayout.NORTH,loginBtn);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdFiled() {
        return pwdFiled;
    }

}
