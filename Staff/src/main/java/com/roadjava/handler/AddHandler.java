package com.roadjava.handler;

import com.roadjava.entity.StaffDO;
import com.roadjava.service.StaffService;
import com.roadjava.service.impl.StaffServiceImpl;
import com.roadjava.staffView.AddView;
import com.roadjava.staffView.MainView;
import com.roadjava.staffView.SearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHandler implements ActionListener {

    private AddView addView;
    //private MainView mainView;
    public AddHandler(AddView addView){
//,MainView mainView
        this.addView =addView;
       // this.mainView=mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       JButton jButton= (JButton) e.getSource();
       String text=jButton.getText();
       if("添加".equals(text)){
           StaffService staffService=new StaffServiceImpl();
           StaffDO staffDO=addView.buildStaffDo();
           boolean addResult = staffService.add(staffDO);
           if (addResult){
               //

               JOptionPane.showMessageDialog(addView,"添加成功");
               addView.dispose();

           }else {
               JOptionPane.showMessageDialog(addView,"添加失败");
           }
       }

    }


}
