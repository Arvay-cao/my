package com.roadjava.staffView;

import com.roadjava.handler.ActionHandler;
import com.roadjava.req.StaffRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StaffService;
import com.roadjava.service.impl.StaffServiceImpl;
import com.roadjava.staffView.ext.MainTable;
import com.roadjava.staffView.ext.MainTableModel;
import com.roadjava.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class SearchView extends JFrame{


    JPanel westPanel = new JPanel(new GridLayout(20,1));
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JPanel southPanel =new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton backBtn =new JButton("返回");
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    JLabel fLabel=new JLabel("按学历查询");
    JButton alterBtn=new JButton("修改");
    JButton deleteBtn =new JButton("删除");
//    JLabel SLabel=new JLabel("按学历查询");


    ActionHandler actionHandler;
    MainTable mainTable=new MainTable();

    private int pageNow = 1; // 当前是第几页
    private int pageSize = 10; // 一页显示多少条记录

    public SearchView(){
        super("查询界面");
        Container contentPane =getContentPane();
        actionHandler=new ActionHandler(this);
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);
        //contentPane.setLayout(new BorderLayout());
        //中间的table
        LayoutCenter(contentPane);
        //西边的search
        LayoutWest(contentPane);
        //南边的back
        LoyoutSouth(contentPane);

        //设置窗体图标
        URL resource = SearchView.class.getClassLoader().getResource("Icon.png");
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

    private void LoyoutSouth(Container contentPane) {
        preBtn.addActionListener(actionHandler);
        nextBtn.addActionListener(actionHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        southPanel.add(backBtn);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView();
                SearchView.this.setVisible(false);
            }
        });
    }

    /*
   设置上一页下一页是否可见
    */
    private void showPreNext(int totalCount) {
        if (pageNow == 1) {
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        int pageCount = 0;//总共有多少页
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = totalCount / pageSize + 1;
        }
        if (pageNow == pageCount) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }

    private void LayoutWest(Container contentPane) {

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
//        JDialog d1=new JDialog(this,"only one row",true);
//        JLabel d1Label=new JLabel("只能修改一行",JLabel.CENTER);
//        d1.add(d1Label);
        p1.add(fLabel);
        p2.add(searchTxt);
        p2.add(searchBtn);
        p3.add(alterBtn);
        p4.add(deleteBtn);
        fLabel.setFont(new Font("华文行楷",Font.PLAIN,30));
        westPanel.add(p1);
        westPanel.add(p2);
        westPanel.add(p3);
        westPanel.add(p4);
        contentPane.add(westPanel,BorderLayout.WEST);
        searchBtn.addActionListener(actionHandler);
        alterBtn.addActionListener(actionHandler);
        deleteBtn.addActionListener(actionHandler);
    }

    private void LayoutCenter(Container contentPane) {

        TableDTO dto = getTableDTO();
        //data+totalCount
        MainTableModel mainTableModel =MainTableModel.assembleModel(dto.getData());
        mainTable.setModel(mainTableModel);
        mainTable.renderRule();
        JScrollPane jScrollPane =new JScrollPane(mainTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }
    private TableDTO getTableDTO() {
        StaffService staffService=new StaffServiceImpl();
        StaffRequest request =new StaffRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = staffService.retrieveStaff(request);
//        Vector<Vector<Object>> data = tableDTO.getData();
        return tableDTO;
    }


    public void reloadTable(){
        TableDTO dto=getTableDTO();
        MainTableModel.updateModel(dto.getData());
        mainTable.renderRule();

    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public int[] getSelectStaffIds() {
        int[] selectRows=mainTable.getSelectedRows();
        int[] ids =new int[selectRows.length];
        for (int i=0;i< selectRows.length;i++){
            int rowIndex=selectRows[i];
            Object idObj =mainTable.getValueAt(rowIndex,0);
            ids[i]=Integer.valueOf(idObj.toString());

        }
        return ids;
    }

}
