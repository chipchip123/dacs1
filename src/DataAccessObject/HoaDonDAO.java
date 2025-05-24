package DataAccessObject;


import config.ConnectDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonModel; // tạo class model để dễ quản lý dữ liệu

public class HoaDonDAO {

    public static boolean themHoaDon(HoaDonModel hd) {
        String sql = "INSERT INTO HoaDon (SoDienThoai, NgayThanhToan, TongTien, TrangThai, GhiChu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hd.getSoDienThoai());
            ps.setDate(2, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setDouble(3, hd.getTongTien());
            ps.setString(4, hd.getTrangThai());
            ps.setString(5, hd.getGhiChu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<HoaDonModel> getTatCaHoaDon() {
        List<HoaDonModel> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY MaHoaDon DESC";
        try (Connection conn = ConnectDatabase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonModel hd = new HoaDonModel();
                hd.setMaHoaDon(rs.getInt("MaHoaDon"));
                hd.setSoDienThoai(rs.getString("SoDienThoai"));
                hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getString("TrangThai"));
                hd.setGhiChu(rs.getString("GhiChu"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
