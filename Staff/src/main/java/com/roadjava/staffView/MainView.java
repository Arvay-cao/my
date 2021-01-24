package com.roadjava.staffView;

import com.roadjava.res.TableDTO;
import com.roadjava.staffView.ext.MainTable;
import com.roadjava.staffView.ext.MainTableModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainView extends JFrame{

    JLabel northLabel=new JLabel("欢迎使用职工信息管理系统",JLabel.CENTER);

    //菜单栏
    private  JMenuBar menuBar;
    private  JMenu menuEdit,menuSelect,menuSave;
    private  JMenuItem miAdd,miDelete,miAlter,miSelect1,miSave;
    MainTable mainTable=new MainTable();

    public MainView() {
        super("主界面——职工信息管理系统");

        Container contentPane =getContentPane();

        northLabel.setFont(new Font("华文行楷",Font.PLAIN,50));
        northLabel.setPreferredSize(new Dimension(0,100));


        contentPane.setLayout(new BorderLayout());
        contentPane.add(northLabel,BorderLayout.CENTER);

        //创建菜单对象
        menuBar =new JMenuBar();
        //将菜单栏设置到窗体
        this.setJMenuBar(menuBar);
        //创建菜单
        menuEdit=new JMenu("修改职工信息");
        menuSelect=new JMenu("查询");
        menuSave=new JMenu("保存职工信息");
        //将菜单添加到菜单栏
        menuBar.add(menuEdit);
        menuBar.add(menuSave);
        menuBar.add(menuSelect);
        //创建菜单项
        miAdd=new JMenuItem("添加");
        miAlter=new JMenuItem("修改");
        miDelete=new JMenuItem("删除");
        miSelect1=new JMenuItem("查询");
        miSave=new JMenuItem("保存到");

        menuEdit.add(miAdd);
        menuEdit.add(miAlter);
        menuEdit.add(miDelete);
        menuSelect.add(miSelect1);

        menuSave.add(miSave);

        contentPane.add(menuBar,BorderLayout.NORTH);
        menuBar.setPreferredSize(new Dimension(1000,40));

        //菜单事件处理
        menuHandler();


        //设置窗体图标
        URL resource = MainView.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);

        //自适应界面大小
        setBounds(DimensionUtil.getBounds());
        //设置窗体完全充满
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //居中
        setLocationRelativeTo(null);
        //关闭退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //大小可变
        setResizable(true);
        setVisible(true);
    }

    private void menuHandler() {
        miAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddView(MainView.this).setVisible(true);

            }
        });

        miAlter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchView();
                MainView.this.setVisible(false);
                //new UpdateView(MainView.this).setVisible(true);

            }
        });

        miDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchView();
                MainView.this.setVisible(false);
               // System.out.println("delete");
            }
        });

        miSelect1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchView();
                MainView.this.setVisible(false);
               // System.out.println("Select1");
            }
        });


        miSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("save as..");
            }
        });
    }
}
