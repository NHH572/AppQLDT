package controller;

import controller.EduSysDAO;
import helper.JdbcHelper;
import model.ChuyenDe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    @Override
    public void insert(ChuyenDe cd){
        String sql ="Insert ChuyenDe values (?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, 
                cd.getMaCD(),
                cd.getTenCD(),
                cd.getHocPhi(),
                cd.getThoiLuong(),
                cd.getHinh(),
                cd.getMoTa());
    }
    @Override
    public void update (ChuyenDe cd){
        String sql="UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
        JdbcHelper.executeUpdate(sql, 
                cd.getTenCD(),
                cd.getHocPhi(),
                cd.getThoiLuong(),
                cd.getHinh(),
                cd.getMoTa(),
                cd.getMaCD()
            );
    }
    @Override
    public void delete(String maNV){
        String sql ="Delete from Chuyende where maCD=?";
        JdbcHelper.executeUpdate(sql, maNV);
    }
    
    private ChuyenDe readFormResultSet(ResultSet rs) throws SQLException{
        ChuyenDe cd = new ChuyenDe();
        cd.setMaCD(rs.getString("MaCD"));
        cd.setHinh(rs.getString("Hinh"));
        cd.setHocPhi(rs.getFloat("HocPhi"));
        cd.setMoTa(rs.getString("MoTa"));
        cd.setTenCD(rs.getString("TenCD"));
        cd.setThoiLuong(rs.getInt("ThoiLuong"));
        return cd;
    }
    
    @Override
    public List<ChuyenDe> selectAll() {
        String sql="SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }

    @Override
    public ChuyenDe selectById(String id) {
        String sql="SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list=selectBySql(sql,id);
        return list.size()>0?list.get(0):null;
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try {
            ResultSet rs = null;
            try {
                rs =JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    list.add(readFormResultSet(rs));
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }
}
