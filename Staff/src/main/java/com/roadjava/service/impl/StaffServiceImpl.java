package com.roadjava.service.impl;

import com.roadjava.entity.StaffDO;
import com.roadjava.req.StaffRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StaffService;
import com.roadjava.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StaffServiceImpl implements StaffService {
    @Override
    public TableDTO retrieveStaff(StaffRequest request) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from staff_info ");
        if(request.getSearchKey() !=null && !"".equals(request.getSearchKey().trim())){
            //按学历查询
            sql.append("where Sli like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append("order by Sid desc limit ").append(request.getStart()).append(",")
                .append(request.getPageSize());


        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        TableDTO returnDTO =new TableDTO();

        try {
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from staff_info ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append(" where Sli like '%"+request.getSearchKey().trim()+"%'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }

            return returnDTO;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);

        }
        return null;
    }
//INSERT INTO staff_info(Sid,Sname,Wages,Ssex ,Sage ,Sli,Saddr,Stel)
//                        values('20182034','Aravy','15000','男','29','本科','江苏','1********9');
    @Override
    public boolean add(StaffDO staffDO) {
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO staff_info(Sid,Sname,Wages,Ssex ,Sage ,Sli,Saddr,Stel) ");
        sql.append("values(?,?,?,?,?,?,?,?)");

        Connection conn =null;
        PreparedStatement ps =null;

        try {
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            ps.setString(1,staffDO.getSid());
            ps.setString(2,staffDO.getSname());
            ps.setString(3,staffDO.getWages());
            ps.setString(4,staffDO.getSsex());
            ps.setString(5,staffDO.getSage());
            ps.setString(6,staffDO.getSli());
            ps.setString(7,staffDO.getSaddr());
            ps.setString(8,staffDO.getStel());
            return ps.executeUpdate()==1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);

        }
        return false;
    }

    @Override
    public StaffDO getById(int selectStaffId) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from staff_info where sid=?");
        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        StaffDO staffDO =new StaffDO();
        try {
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            ps.setInt(1,selectStaffId);
            rs=ps.executeQuery();

            while (rs.next()){
                //处理查出的消息记录
                String Sid = rs.getString("Sid");
                String Sname= rs.getString("Sname");
                String Ssex= rs.getString("Ssex");
                String Sage= rs.getString("Sage");
                String Sli= rs.getString("Sli");
                String Wages= rs.getString("Wages");
                String Saddr= rs.getString("Saddr");
                String Stel= rs.getString("Stel");
                staffDO.setSid(Sid);
                staffDO.setSname(Sname);
                staffDO.setSsex(Ssex);
                staffDO.setSage(Sage);
                staffDO.setSli(Sli);
                staffDO.setWages(Wages);
                staffDO.setSaddr(Saddr);
                staffDO.setStel(Stel);
            }
            return staffDO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);

        }

        return null;
    }

    @Override
    public boolean update(StaffDO staffDO) {
        StringBuilder sql=new StringBuilder();
        sql.append("update staff_info set Sname=?,Wages=?,Ssex=? ,Sage=? ,Sli=?,Saddr=?,Stel=? ");
        sql.append("where id=?");

        Connection conn =null;
        PreparedStatement ps =null;

        try {
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            ps.setString(1,staffDO.getSname());
            ps.setString(2,staffDO.getWages());
            ps.setString(3,staffDO.getSsex());
            ps.setString(4,staffDO.getSage());
            ps.setString(5,staffDO.getSli());
            ps.setString(6,staffDO.getSaddr());
            ps.setString(7,staffDO.getStel());
            ps.setString(8,staffDO.getSid());
            return ps.executeUpdate()==1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);

        }

        return false;
    }

    @Override
    public boolean delete(int[] selectStaffIds) {

        StringBuilder sql=new StringBuilder();
        sql.append("delete from staff_info where id in (");
        int length=selectStaffIds.length;
        for (int i = 0; i < length; i++) {
            if (i == (length - 1)) {
                sql.append(" ? ");
            }else {
                sql.append(" ?, ");
            }
        }
        sql.append(")");
        Connection conn =null;
        PreparedStatement ps =null;
        try {
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            for (int i = 0; i < length; i++) {
                // 设置参数，从1开始
                ps.setInt(i + 1, selectStaffIds[i]);
            }
            return ps.executeUpdate() == length;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);

        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data =new Vector<>();
        while (rs.next()){
            //处理查出的消息记录
            Vector<Object> oneRecord = new Vector<>();
            String Sid = rs.getString("Sid");
            String Sname= rs.getString("Sname");
            String Ssex= rs.getString("Ssex");
            String Sage= rs.getString("Sage");
            String Sli= rs.getString("Sli");
            String Wages= rs.getString("Wages");
            String Stel= rs.getString("Stel");
            String Saddr= rs.getString("Saddr");

            oneRecord.addElement(Sid);
            oneRecord.addElement(Sname);
            oneRecord.addElement(Ssex);
            oneRecord.addElement(Sage);
            oneRecord.addElement(Sli);
            oneRecord.addElement(Wages);
            oneRecord.addElement(Saddr);
            oneRecord.addElement(Stel);
            data.addElement(oneRecord);
        }
        return data;
    }
}
