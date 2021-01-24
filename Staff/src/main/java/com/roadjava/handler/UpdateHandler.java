package com.roadjava.handler;

import com.roadjava.entity.StaffDO;
import com.roadjava.service.StaffService;
import com.roadjava.service.impl.StaffServiceImpl;
import com.roadjava.staffView.SearchView;
import com.roadjava.staffView.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHandler implements ActionListener {

    private UpdateView updateView;
    private SearchView searchView;
    //private MainView mainView;
    public UpdateHandler(UpdateView updateView,SearchView searchView){
//,MainView mainView
        this.updateView =updateView;
        this.searchView=searchView;
       // this.mainView=mainView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       JButton jButton= (JButton) e.getSource();
       String text=jButton.getText();
       if("修改".equals(text)){
           StaffService staffService=new StaffServiceImpl();
           StaffDO staffDO=updateView.buildStaffDo();
           boolean addResult = staffService.update(staffDO);
           if (addResult){
               searchView.reloadTable();
               updateView.dispose();

           }else {
               JOptionPane.showMessageDialog(updateView,"修改失败");
           }
       }

    }


}
