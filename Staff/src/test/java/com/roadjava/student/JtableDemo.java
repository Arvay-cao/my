package com.roadjava.student;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class JtableDemo extends JFrame {

    public JtableDemo(){
        super("测试jtable");



        Vector<Vector<Object>> data =new Vector<>();

        Vector<Object> rowVector1=new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("20182018");
        rowVector1.addElement("张三");
        rowVector1.addElement("男");
        rowVector1.addElement("35");
        rowVector1.addElement("本科");
        rowVector1.addElement("15000");
        rowVector1.addElement("江西");
        rowVector1.addElement("1********8");

        Vector<Object> rowVector2=new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("20182019");
        rowVector2.addElement("李四");
        rowVector2.addElement("男");
        rowVector2.addElement("30");
        rowVector2.addElement("专科");
        rowVector2.addElement("14000");
        rowVector2.addElement("江西");
        rowVector2.addElement("1********7");

        data.addElement(rowVector1);
        data.addElement(rowVector2);

        //tablemodel：和jtable关联后，之后只需要更新model就能把数据变化反应到Jtable中
//        DefaultTableModel tableModel=new DefaultTableModel();
//        tableModel.setDataVector(data,colums);
          StaffTableModel staffTableModel=StaffTableModel.assembleModel(data);

        //jtable 和table关联
        JTable jTable=new JTable(staffTableModel);
        //设置表头
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.BLUE);
        //设置表格体
        jTable.setFont(new Font(null,Font.PLAIN,14));
        jTable.setForeground(Color.BLACK);
        jTable.setRowHeight(30);
        //设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //居中，设置表格列渲染方式
        Vector<String> columns =StaffTableModel.getColums();
        StaffCellRender render =new StaffCellRender();
        for (int i =0;i < columns.size();i++){

            TableColumn column = jTable.getColumn(columns.get(i));
            column.setCellRenderer(render);
            //设置单列的宽度
            if(i == 0){
                column.setPreferredWidth(50);
                //宽度不可改变
                column.setResizable(false);
            }

        }



        //jtable 放在jpanel上的话，默认不展示列头，需要特殊设置
        //一般放在JScrollPane中 默认展示列头
        Container contentPane =getContentPane();
        JScrollPane jScrollPane =new JScrollPane(jTable);
        contentPane.add(jScrollPane);

        URL resource =JtableDemo.class.getClassLoader().getResource("Icon.png");
        Image image =new ImageIcon(resource).getImage();
        setIconImage(image);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args){

        new JtableDemo();
    }
}

class StaffCellRender extends DefaultTableCellRenderer{
    //在每一行每一列显示前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if(row % 2 == 0){
            setBackground(Color.LIGHT_GRAY);
        }
        else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

//自定义tableModel
class StaffTableModel extends DefaultTableModel{

    static Vector<String> colums=new Vector<>();
    static {
        colums.addElement("序号");
        colums.addElement("职工号");
        colums.addElement("姓名");
        colums.addElement("性别");
        colums.addElement("年龄");
        colums.addElement("学历");
        colums.addElement("工资");
        colums.addElement("住址");
        colums.addElement("电话");
    }
    private StaffTableModel(){
        super(null,colums);
    }

    private static StaffTableModel staffTableModel =new StaffTableModel();

    public static StaffTableModel assembleModel(Vector<Vector<Object>> data){

        staffTableModel.setDataVector(data,colums);
        return staffTableModel;
    }

    public static Vector<String> getColums() {
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //单元格不可编辑
        return false;
    }
}