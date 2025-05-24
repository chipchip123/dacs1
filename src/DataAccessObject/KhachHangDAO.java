package DataAccessObject;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import config.ConnectDatabase;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import config.ConnectDatabase;




public class KhachHangDAO {
    public static boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (SoDienThoai, TenKhachHang, NgayTaoTaiKhoan, DiemTichLuy) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getSoDienThoai());
            ps.setString(2, kh.getTenKhachHang());
            ps.setDate(3, java.sql.Date.valueOf(kh.getNgayTaoTaiKhoan()));
            ps.setInt(4, kh.getDiemTichLuy());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static DefaultTableModel getAllKhachHangModel() {
        String[] columns = {"Họ và tên", "Số điện thoại", "Điểm đã tích lũy"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT TenKhachHang, SoDienThoai, DiemTichLuy FROM KhachHang";

        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String ten = rs.getString("TenKhachHang");
                String sdt = rs.getString("SoDienThoai");
                int diem = rs.getInt("DiemTichLuy");

                model.addRow(new Object[]{ten, sdt, diem});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }
    
    public KhachHang getKhachHangBySDT(String sdt) {
        String sql = "SELECT SoDienThoai, TenKhachHang, NgayTaoTaiKhoan, DiemTichLuy FROM KhachHang WHERE SoDienThoai = ?";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sdt);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                        rs.getString("SoDienThoai"),
                        rs.getString("TenKhachHang"),
                        rs.getDate("NgayTaoTaiKhoan").toLocalDate(),
                        rs.getInt("DiemTichLuy")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
