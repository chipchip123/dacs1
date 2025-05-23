package DataAccessObject;


import controller.NhanVien;
import config.ConnectDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanVienDAO {
    
    public List<NhanVien> getDanhSachNhanVien(){
        List<NhanVien> danhsach = new ArrayList<>();
        
        try (Connection conn = ConnectDatabase.getConnection()){
            String sql = "SELECT * FROM NhanVien";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String ten = rs.getString("Ten");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDienThoai");
                String taiKhoan = rs.getString("TaiKhoan");
                String matKhau = rs.getString("MatKhau");
                Date namSinh = rs.getDate("NamSinh");
                String gioiTinh = rs.getString("GioiTinh");
                String email = rs.getString("Email");

                NhanVien nv = new NhanVien(ten, cccd, sdt, taiKhoan, matKhau, namSinh, gioiTinh, email);
                danhsach.add(nv);
            }
        } catch (Exception e) {
        }
        return danhsach;
    }  
    public boolean themNhanVien(NhanVien nv){
        try (Connection conn = ConnectDatabase.getConnection()){
           String sql = "INSERT INTO NhanVien (Ten, CCCD, SoDienThoai, TaiKhoan, MatKhau, NamSinh, GioiTinh, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
           PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nv.getTen());
        stmt.setString(2, nv.getCccd());
        stmt.setString(3, nv.getSdt());
        stmt.setString(4, nv.getTaikhoan());
        stmt.setString(5, nv.getMatkhau());
        stmt.setDate(6, new java.sql.Date(nv.getNamsinh().getTime()));
        stmt.setString(7, nv.getGioitinh());
        stmt.setString(8, nv.getEmail());

        return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhanVien(String taiKhoan) {
    try (Connection conn = ConnectDatabase.getConnection()) {
        String sql = "DELETE FROM NhanVien WHERE TaiKhoan = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, taiKhoan);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean capNhatNhanVien(NhanVien nv) {
    try (Connection conn = ConnectDatabase.getConnection()) {
        String sql = "UPDATE NhanVien SET Ten = ?, CCCD = ?, SoDienThoai = ?, MatKhau = ?, NamSinh = ?, GioiTinh = ?, Email = ? WHERE TaiKhoan = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nv.getTen());
        stmt.setString(2, nv.getCccd());
        stmt.setString(3, nv.getSdt());
        stmt.setString(4, nv.getMatkhau());
        stmt.setDate(5, new java.sql.Date(nv.getNamsinh().getTime()));
        stmt.setString(6, nv.getGioitinh());
        stmt.setString(7, nv.getEmail());
        stmt.setString(8, nv.getTaikhoan());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }
    
    public List<NhanVien> timNhanVienTheoTen(String tenCanTim) {
    List<NhanVien> danhsach = new ArrayList<>();
    try (Connection conn = ConnectDatabase.getConnection()) {
        String sql = "SELECT * FROM NhanVien WHERE Ten LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + tenCanTim + "%"); // tìm gần đúng
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String ten = rs.getString("Ten");
            String cccd = rs.getString("CCCD");
            String sdt = rs.getString("SoDienThoai");
            String taiKhoan = rs.getString("TaiKhoan");
            String matKhau = rs.getString("MatKhau");
            Date namSinh = rs.getDate("NamSinh");
            String gioiTinh = rs.getString("GioiTinh");
            String email = rs.getString("Email");

            NhanVien nv = new NhanVien(ten, cccd, sdt, taiKhoan, matKhau, namSinh, gioiTinh, email);
            danhsach.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhsach;
    }
}

    

