package model;

import config.ConnectDatabase;
import java.sql.*;

public class NguoiDungModel {
    public String kiemTraDangNhap(String tenDangNhap, String matKhau) {
        String role = null;
        try (Connection conn = ConnectDatabase.getConnection()) {
            String sql = "SELECT MaNguoiDung, LaAdmin FROM NguoiDung WHERE TenDangNhap=? AND MatKhau=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tenDangNhap);
            pst.setString(2, matKhau); // giả sử đã mã hóa MD5 hoặc sẵn chuỗi mã hóa

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("LaAdmin");
                return isAdmin ? "admin" : "nhanvien";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
