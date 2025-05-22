package DataAccessObject;

import config.ConnectDatabase;
import model.SanPhamModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamModelDAO {
    private Connection conn;

    public SanPhamModelDAO() {
        this.conn = ConnectDatabase.getConnection();
    }

    // Thêm sản phẩm mới
    public boolean insertSanPham(SanPhamModel sp) {
        String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, MaLoaiSanPham, GiaSanPham, SoLuong, DonVi, TrangThai, HinhAnh) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sp.getMaSanPham());
            pst.setString(2, sp.getTenSanPham());
            pst.setInt(3, sp.getMaLoaiSanPham());
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

    // Cập nhật sản phẩm
    public boolean updateSanPham(SanPhamModel sp) {
        String sql = "UPDATE SanPham SET TenSanPham=?, MaLoaiSanPham=?, GiaSanPham=?, SoLuong=?, DonVi=?, TrangThai=?, HinhAnh=? WHERE MaSanPham=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sp.getTenSanPham());
            pst.setInt(2, sp.getMaLoaiSanPham());
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

    // Lấy danh sách tất cả sản phẩm (JOIN để lấy cả tên loại)
    public List<SanPhamModel> getAllSanPham() {
        List<SanPhamModel> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                sanPhamList.add(new SanPhamModel(
                        rs.getInt("MaSanPham"),
                        rs.getString("TenSanPham"),
                        rs.getInt("MaLoaiSanPham"),
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
        return sanPhamList;
    }

    // Tìm kiếm sản phẩm theo tên hoặc mã
    public List<SanPhamModel> searchSanPham(String keyword) {
        List<SanPhamModel> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE TenSanPham LIKE ? OR CAST(MaSanPham AS NVARCHAR) LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            pst.setString(1, like);
            pst.setString(2, like);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sanPhamList.add(new SanPhamModel(
                        rs.getInt("MaSanPham"),
                        rs.getString("TenSanPham"),
                        rs.getInt("MaLoaiSanPham"),
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
        return sanPhamList;
    }
    
}
