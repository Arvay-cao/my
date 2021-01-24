package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.StaffService;
import com.roadjava.service.impl.AdminServerImpl;
import com.roadjava.service.impl.StaffServiceImpl;
import com.roadjava.staffView.LoginView;
import com.roadjava.staffView.MainView;
import com.roadjava.staffView.SearchView;
import com.roadjava.staffView.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    private SearchView searchView;

    public ActionHandler(SearchView searchView) {

        this.searchView = searchView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("查询".equals(text)) {
            searchView.setPageNow(1);
            searchView.reloadTable();
        } else if ("修改".equals(text)) {
            int[] selectStaffIds = searchView.getSelectStaffIds();
            if (selectStaffIds.length != 1) {
                JOptionPane.showMessageDialog(searchView, "一次只能修改一行！");
                return;
            }
            new UpdateView(searchView, selectStaffIds[0]);
            //new UpdateView(SearchView.this).setVisible(true);

        } else if ("删除".equals(text)) {
            int[] selectStaffIds = searchView.getSelectStaffIds();
            if (selectStaffIds.length == 0) {
                JOptionPane.showMessageDialog(searchView, "请选择要删除的行！");
                return;
            }
            int option = JOptionPane.showConfirmDialog(searchView, "确认要删除选中的"
                            + selectStaffIds.length + "行吗?", "确认删除",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) { // 确认
                // 执行删除
                StaffService studentService = new StaffServiceImpl();
                boolean deleteResult = studentService.delete(selectStaffIds);
                if (deleteResult) {
                    // 重新加载表格查到最新数据
                    searchView.reloadTable();
                } else {
                    JOptionPane.showMessageDialog(searchView, "删除失败");
                }
            }

            } else if ("上一页".equals(text)) {
                searchView.setPageNow(searchView.getPageNow() - 1);
                searchView.reloadTable();
            } else if ("下一页".equals(text)) {
                searchView.setPageNow(searchView.getPageNow() + 1);
                searchView.reloadTable();
            }

        }

}
