/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.JdbcHelper;
import model.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {

    @Override
    public void insert(KhoaHoc kh) {
        String sql = "insert into khoahoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao ) values (?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                kh.getMaCD(),
                kh.getHocPhi(),
                kh.getThoiLuong(),
                kh.getNgayKG(),
                kh.getGhiChu(),
                kh.getMaNV(),
                kh.getNgayTao());
    }

    @Override
    public void update(KhoaHoc model) {
        String sql = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql,
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }

    @Override
    public void delete(String maKH) {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql, maKH);
    }

    public KhoaHoc readFromResultSet(ResultSet rs) throws SQLException {
        KhoaHoc kh = new KhoaHoc();
        kh.setMaKH(rs.getInt("MaKH"));
        kh.setHocPhi(rs.getFloat("HocPhi"));
        kh.setThoiLuong(rs.getInt("ThoiLuong"));
        kh.setNgayKG(rs.getDate("NgayKG"));
        kh.setGhiChu(rs.getString("GhiChu"));
        kh.setMaNV(rs.getString("MaNV"));
        kh.setNgayTao(rs.getDate("NgayTao"));
        kh.setMaCD(rs.getString("MaCD"));
        return kh;
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String sql = "SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }

    @Override
    public KhoaHoc selectById(String maKH) {
        String sqlString = "select * from KhoaHoc where maKH=?";
        List<KhoaHoc> list = selectBySql(sqlString, maKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<KhoaHoc> selectByChuyenDe(String maCD) {
        String sqlString = "select * from KhoaHoc where maCD=?";
        return selectBySql(sqlString, maCD);
    }

    public List<Integer> selectYear() {
        String sql = "SELECT DISTINCT year(NgayKG) as nam FROM KhoaHoc ORDER BY nam DESC";
        List<Integer> list = new ArrayList<Integer>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                list.add(readFromResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if (rs != null) {
                try {
                    rs.getStatement().getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(KhoaHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
}
