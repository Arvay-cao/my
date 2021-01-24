package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServerImpl;
import com.roadjava.staffView.LoginView;
import com.roadjava.staffView.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler implements ActionListener {

    private LoginView loginView;
    public LoginHandler(LoginView loginView){

        this.loginView =loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton jButton= (JButton) e.getSource();
       String text=jButton.getText();
       if("重置".equals(text)){

           loginView.getUserTxt().setText("");
           loginView.getPwdFiled().setText("");
//           System.out.println(text);
       }
       else if ("登陆".equals(text)){

           login();
       }
       else if ("注册".equals(text)){

           //跳转注册页面
//           System.out.println(text);
       }
    }

    private void login() {
        String user=loginView.getUserTxt().getText();
        char[] chars=loginView.getPwdFiled().getPassword();
        //判断账号密码不为空
        if (user ==null ||"".equals(user.trim())||chars ==null){
            JOptionPane.showMessageDialog(loginView,"账号或密码不能为空");
            return;
        }

        String pwd =new String(chars);
        System.out.println(user);
        System.out.println(pwd);

        //查询DB
        AdminService adminService = new AdminServerImpl();
        AdminDO adminDO =new AdminDO();
        adminDO.setSid(user);
        adminDO.setPwd(pwd);
        boolean flag=adminService.validateAdmin(adminDO);
        if(flag){
            //跳转主页面并销毁登陆界面
            new MainView();
            loginView.dispose();

        }else {
            //弹出账号密码错误；
            JOptionPane.showMessageDialog(loginView,"账号或密码错误");
        }

//           System.out.println(text);
    }
}
