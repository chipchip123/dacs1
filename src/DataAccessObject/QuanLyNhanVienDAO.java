package DataAccessObject;


import controller.NhanVien;
import config.ConnectDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

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
    
    public boolean xuatDanhSachNhanVienRaExcel(File file) {
        List<NhanVien> danhSach = getDanhSachNhanVien();
        if (danhSach.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu nhân viên để xuất!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try (Workbook wb = new XSSFWorkbook(); FileOutputStream out = new FileOutputStream(file)) {
            XSSFSheet sheet = (XSSFSheet) wb.createSheet("Danh sách nhân viên");

            // 1) Ghi header
            String[] headers = { "Tên", "CCCD", "SĐT", "Tài khoản", "Mật khẩu", "Năm sinh", "Giới tính", "Email" };
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // 2) Ghi dữ liệu
            int rowIndex = 1;
            for (NhanVien nv : danhSach) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(nv.getTen());
                row.createCell(1).setCellValue(nv.getCccd());
                row.createCell(2).setCellValue(nv.getSdt());
                row.createCell(3).setCellValue(nv.getTaikhoan());
                row.createCell(4).setCellValue(nv.getMatkhau());
                row.createCell(5).setCellValue(nv.getNamsinh().toString());
                row.createCell(6).setCellValue(nv.getGioitinh());
                row.createCell(7).setCellValue(nv.getEmail());
            }

            // 3) Tự động điều chỉnh độ rộng
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            wb.write(out);

            JOptionPane.showMessageDialog(null, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel:\n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

    

