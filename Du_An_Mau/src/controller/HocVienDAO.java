/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.JdbcHelper;
import model.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HocVienDAO extends EduSysDAO<HocVien, String>{

    public HocVien readFromResultSet(ResultSet rs) throws SQLException{
        HocVien model=new HocVien();
        model.setMaHV(rs.getInt("MaHV"));
         model.setMaKH(rs.getInt("MaKH"));
         model.setMaNH(rs.getString("MaNH"));
         model.setDiem(rs.getFloat("Diem"));
         return model;
    }
    
    @Override
    public void insert(HocVien hv) {
        String sql="INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                hv.getMaKH(),
                 hv.getMaNH(),
                 hv.getDiem());
    }

    @Override
    public void update(HocVien hv) {
        String sql="UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        JdbcHelper.executeUpdate(sql,
                hv.getMaKH(),
                 hv.getMaNH(),
                 hv.getDiem(),
                 hv.getMaHV());
    }

    @Override
    public void delete(String MaHV) {
        String sql="DELETE FROM HocVien WHERE MaHV=?";
     JdbcHelper.executeUpdate(sql, MaHV);
    }

    @Override
    public List<HocVien> selectAll() {
        String sql="SELECT * FROM HocVien";
        return selectBySql(sql); 
    }

    @Override
    public HocVien selectById(String id) {
        String sql="SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list=selectBySql(sql, id);
        return list.size()>0?list.get(0):null;  
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list=new ArrayList<HocVien>();
        try {
            ResultSet rs=null;
            try{
                rs=JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    list.add(readFromResultSet(rs));
                }
            }finally{
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
    
    public List<HocVien> selectByKhoaHoc(int maKh){
        String sql ="SELECT * FROM HocVien WHERE MaKH =?";
        return this.selectBySql(sql, maKh);
    }
    
}
