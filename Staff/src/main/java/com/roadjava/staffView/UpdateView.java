package com.roadjava.staffView;



import com.roadjava.entity.StaffDO;
import com.roadjava.handler.UpdateHandler;
import com.roadjava.service.StaffService;
import com.roadjava.service.impl.StaffServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class UpdateView extends JDialog{

    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel sidLabel = new JLabel("职工号:",JLabel.RIGHT);
    JTextField sidTxt = new JTextField();
    JLabel nameLabel = new JLabel("姓名:",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel ssexLabel = new JLabel("性别:",JLabel.RIGHT);
    JTextField ssexTxt = new JTextField();
    JLabel sageLabel = new JLabel("年龄:",JLabel.RIGHT);
    JTextField sageTxt = new JTextField();
    JLabel sliLabel = new JLabel("学历:",JLabel.RIGHT);
    JTextField sliTxt = new JTextField();
    JLabel wagesLabel = new JLabel("工资:",JLabel.RIGHT);
    JTextField wagesTxt = new JTextField();
    JLabel saddrLabel = new JLabel("住址:",JLabel.RIGHT);
    JTextField saddrTxt = new JTextField();
    JLabel stelLabel = new JLabel("电话:",JLabel.RIGHT);
    JTextField stelTxt = new JTextField();
    JButton upDateBtn = new JButton("修改");

    //AddHandler addHandler;
    UpdateHandler updateHandler;

    public UpdateView(SearchView searchView, int selectStaffId){
        super(searchView,"修改职工信息",true);
        updateHandler=new UpdateHandler(this,searchView);

        //查询selectStaffId对应的记录并回显
        StaffService staffService=new StaffServiceImpl();
        StaffDO selectStaff =staffService.getById(selectStaffId);

        sidLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sidLabel);
        sidTxt.setPreferredSize(new Dimension(200,30));
        sidTxt.setText(selectStaff.getSid()+"");
        //id设置不可编辑
        sidTxt.setEnabled(false);
        jPanel.add(sidTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);
        nameTxt.setText(String.valueOf(selectStaff.getSname()));

        ssexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(ssexLabel);
        ssexTxt.setPreferredSize(new Dimension(200,30));
        ssexTxt.setText(String.valueOf(selectStaff.getSsex()));
        jPanel.add(ssexTxt);

        sageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sageLabel);
        sageTxt.setPreferredSize(new Dimension(200,30));
        sageTxt.setText(String.valueOf(selectStaff.getSage()));
        jPanel.add(sageTxt);

        sliLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sliLabel);
        sliTxt.setPreferredSize(new Dimension(200,30));
        sliTxt.setText(String.valueOf(selectStaff.getSli()));
        jPanel.add(sliTxt);

        wagesLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(wagesLabel);
        wagesTxt.setPreferredSize(new Dimension(200,30));
        wagesTxt.setText(String.valueOf(selectStaff.getWages()));
        jPanel.add(wagesTxt);

        stelLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(stelLabel);
        stelTxt.setPreferredSize(new Dimension(200,30));
        stelTxt.setText(String.valueOf(selectStaff.getStel()));
        jPanel.add(stelTxt);

        saddrLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(saddrLabel);
        saddrTxt.setPreferredSize(new Dimension(200,30));
        saddrTxt.setText(String.valueOf(selectStaff.getSaddr()));
        jPanel.add(saddrTxt);

        upDateBtn.addActionListener(updateHandler);
        jPanel.add(upDateBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);



        URL resource =LoginView.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(350,500);
        //居中
        setLocationRelativeTo(null);
        //只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //大小不可变
        setResizable(false);
        setVisible(true);
    }


    public StaffDO buildStaffDo() {
        StaffDO staffDo=new StaffDO();
        staffDo.setSid(String.valueOf(sidTxt.getText()));
        staffDo.setSname(nameTxt.getText());
        staffDo.setSsex(ssexTxt.getText());
        staffDo.setSage(sageTxt.getText());
        staffDo.setSli(sliTxt.getText());
        staffDo.setWages(wagesTxt.getText());
        staffDo.setSaddr(saddrTxt.getText());
        staffDo.setStel(stelTxt.getText());
        return staffDo;
    }
}
