package com.roadjava.staffView.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainTableModel extends DefaultTableModel {

    static Vector<String> colums=new Vector<>();
    static {
        //colums.addElement("序号");
        colums.addElement("职工号");
        colums.addElement("姓名");
        colums.addElement("性别");
        colums.addElement("年龄");
        colums.addElement("学历");
        colums.addElement("工资");
        colums.addElement("住址");
        colums.addElement("电话");
    }
    private MainTableModel(){
        super(null,colums);
    }

    private static MainTableModel MainTableModel =new MainTableModel();

    public static MainTableModel assembleModel(Vector<Vector<Object>> data){

        MainTableModel.setDataVector(data,colums);
        return MainTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data){

        MainTableModel.setDataVector(data,colums);

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