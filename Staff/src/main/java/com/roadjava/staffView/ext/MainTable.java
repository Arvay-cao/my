package com.roadjava.staffView.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainTable extends JTable {
    public MainTable() {

        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.BLUE);
        //设置表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.BLACK);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }



    public void renderRule(){
        Vector<String> columns =MainTableModel.getColums();
        MainCellRender render =new MainCellRender();
        for (int i =0;i < columns.size();i++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            //设置单列的宽度
            if(i == 0){
                column.setPreferredWidth(50);
                //宽度不可改变
                column.setResizable(false);
            }

        }
    }
}
