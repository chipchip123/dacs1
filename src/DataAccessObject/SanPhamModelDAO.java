package DataAccessObject;

import config.ConnectDatabase;
import model.SanPhamModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamModelDAO {
    private final Connection conn;

    public SanPhamModelDAO() {
        this.conn = ConnectDatabase.getConnection();
    }

    public boolean insertSanPham(SanPhamModel sp) {
        String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, TenLoaiSanPham, GiaSanPham, "
                   + "SoLuong, DonVi, TrangThai, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sp.getMaSanPham());
            pst.setString(2, sp.getTenSanPham());
            pst.setString(3, sp.getTenLoaiSanPham());
            pst.setDouble(4, sp.getGiaSanPham());
            pst.setInt(5, sp.getSoLuong());
            pst.setString(6, sp.getDonVi());
            pst.setString(7, sp.getTrangThai());
            pst.setString(8, sp.getHinhAnh());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSanPham(SanPhamModel sp) {
        String sql = "UPDATE SanPham SET TenSanPham=?, TenLoaiSanPham=?, GiaSanPham=?, "
                   + "SoLuong=?, DonVi=?, TrangThai=?, HinhAnh=? WHERE MaSanPham=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sp.getTenSanPham());
            pst.setString(2, sp.getTenLoaiSanPham());
            pst.setDouble(3, sp.getGiaSanPham());
            pst.setInt(4, sp.getSoLuong());
            pst.setString(5, sp.getDonVi());
            pst.setString(6, sp.getTrangThai());
            pst.setString(7, sp.getHinhAnh());
            pst.setInt(8, sp.getMaSanPham());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SanPhamModel> getAllSanPham() {
        List<SanPhamModel> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SanPhamModel(
                    rs.getInt("MaSanPham"),
                    rs.getString("TenSanPham"),
                    rs.getString("TenLoaiSanPham"),
                    rs.getDouble("GiaSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getString("DonVi"),
                    rs.getString("TrangThai"),
                    rs.getString("HinhAnh")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPhamModel> searchSanPham(String keyword) {
        List<SanPhamModel> list = new ArrayList<>();
        // nếu keyword chỉ toàn số, tìm đúng mã
        if (keyword.matches("\\d+")) {
            String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, Integer.parseInt(keyword));
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        list.add(mapRow(rs));
                    }
                }
            } catch (SQLException e) { e.printStackTrace(); }
            return list;
        }
        // multi-term tìm tên
        String[] terms = keyword.trim().split("\\s+");
        if (terms.length == 0) return getAllSanPham();

        StringBuilder sql = new StringBuilder("SELECT * FROM SanPham WHERE ");
        for (int i = 0; i < terms.length; i++) {
            if (i > 0) sql.append(" AND ");
            sql.append("TenSanPham LIKE ?");
        }
        try (PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < terms.length; i++) {
                pst.setString(i+1, "%" + terms[i] + "%");
            }
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<String> getDanhSachTenLoaiSanPham() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT TenLoaiSanPham FROM LoaiSanPham";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("TenLoaiSanPham"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean existsMaSanPham(int ma) {
        String sql = "SELECT COUNT(1) FROM SanPham WHERE MaSanPham = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ma);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private SanPhamModel mapRow(ResultSet rs) throws SQLException {
        return new SanPhamModel(
            rs.getInt("MaSanPham"),
            rs.getString("TenSanPham"),
            rs.getString("TenLoaiSanPham"),
            rs.getDouble("GiaSanPham"),
            rs.getInt("SoLuong"),
            rs.getString("DonVi"),
            rs.getString("TrangThai"),
            rs.getString("HinhAnh")
        );
    }

    public boolean giamSoLuongSanPham(int maSanPham, int soLuongMua) {
        String sql = "UPDATE SanPham SET SoLuong = SoLuong - ? WHERE MaSanPham = ? AND SoLuong >= ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, soLuongMua);
            pst.setInt(2, maSanPham);
            pst.setInt(3, soLuongMua); // để đảm bảo không giảm quá số lượng có sẵn
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
