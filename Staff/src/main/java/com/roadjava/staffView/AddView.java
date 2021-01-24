package com.roadjava.staffView;



import com.roadjava.entity.StaffDO;
import com.roadjava.handler.AddHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class AddView extends JDialog{

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
    JButton addBtn = new JButton("添加");
    AddHandler addHandler;
    public AddView(MainView mainView){
        super(mainView,"添加职工信息",true);
        addHandler = new AddHandler(this);
        sidLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sidLabel);
        sidTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sidTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);

        ssexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(ssexLabel);
        ssexTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(ssexTxt);

        sageLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sageLabel);
        sageTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sageTxt);

        sliLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sliLabel);
        sliTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sliTxt);

        wagesLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(wagesLabel);
        wagesTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(wagesTxt);

        stelLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(stelLabel);
        stelTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(stelTxt);

        saddrLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(saddrLabel);
        saddrTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(saddrTxt);
        jPanel.add(addBtn);

        addBtn.addActionListener(addHandler);
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
    public StaffDO buildStaffDo(){
        StaffDO staffDo=new StaffDO();
        staffDo.setSid(sidTxt.getText());
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
