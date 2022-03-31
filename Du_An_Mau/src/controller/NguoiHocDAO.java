/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.JdbcHelper;
import model.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{

    @Override
    public void insert(NguoiHoc nh) {
        String sql="INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV,NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
         JdbcHelper.executeUpdate(sql,
             nh.getMaNH(),
             nh.getHoTen(),
             nh.getNgaysinh(),
             nh.isGioiTinh(),
             nh.getDienThoai(),
             nh.getEmail(),
             nh.getGhiChu(),
             nh.getMaNV(),
             nh.getNgayDK());
         
    }

    @Override
    public void update(NguoiHoc nh) {
        String sql="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?,MaNV=? ,NgayDK=? WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql,            
                 nh.getHoTen(),
                 nh.getNgaysinh(),
                 nh.isGioiTinh(),
                 nh.getDienThoai(),
                 nh.getEmail(),
                 nh.getGhiChu(),
                 nh.getMaNV(),
                 nh.getNgayDK(),
                 nh.getMaNH());
    }

    @Override
    public void delete(String id) {
        String sql="DELETE FROM NguoiHoc WHERE MaNH=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        String sql="SELECT * FROM NguoiHoc";
        return selectBySql(sql);
    }

    @Override
    public NguoiHoc selectById(String maNH) {
        String sql="SELECT * FROM NguoiHoc WHERE MaNH=?";
     List<NguoiHoc> list = selectBySql(sql, maNH);
     return list.size() > 0 ? list.get(0) : null;
    }
    
    private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException{
	NguoiHoc entity=new NguoiHoc();
         entity.setMaNH(rs.getString("MaNH"));
         entity.setHoTen(rs.getString("HoTen"));
         entity.setNgaysinh(rs.getDate("NgaySinh"));
         entity.setGioiTinh(rs.getBoolean("GioiTinh"));
         entity.setDienThoai(rs.getString("DienThoai"));
         entity.setEmail(rs.getString("Email"));
         entity.setGhiChu(rs.getString("GhiChu"));
         entity.setMaNV(rs.getString("MaNV"));
         entity.setNgayDK(rs.getDate("NgayDK"));
         return entity;
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
         List<NguoiHoc> list=new ArrayList<NguoiHoc>();
        try {
            ResultSet rs=null;
            try{
                rs=JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    list.add(readFromResultSet(rs));
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return selectBySql(sql, "%"+keyword+"%");
    }
    
    //truy xuất tất cả học viên không học khóa học maKH
     public List<NguoiHoc> selectNotlnCourse(int makh, String keyword){   //để là Integer cho đúng kiểu Object
     String sql="SELECT * FROM NguoiHoc WHERE HOTEN LIKE ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
     return this.selectBySql(sql,"%"+keyword+"%", makh);
     }
    
}
