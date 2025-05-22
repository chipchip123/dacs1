package model;

import config.ConnectDatabase;
import java.sql.*;

public class NguoiDungModel {
    public boolean kiemtradangnhapAdmin(String tenDangNhap, String matKhau){
        try (Connection conn = ConnectDatabase.getConnection()){
            String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap = ? AND MatKhau = ? AND LaAdmin = 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tenDangNhap);
            pst.setString(2, matKhau);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }   
    
    public boolean kiemtradangnhapNhanvien(String tenDangNhap, String matKhau){
        try (Connection conn = ConnectDatabase.getConnection()){
            String sql = "SELECT * FROM NhanVien WHERE TaiKhoan = ? AND MatKhau = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tenDangNhap);
            pst.setString(2, matKhau);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
