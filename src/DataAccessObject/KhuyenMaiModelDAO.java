package DataAccessObject;

import config.ConnectDatabase;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class KhuyenMaiModelDAO {

    public static DefaultTableModel getAllKhuyenMai() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Tên khuyến mãi", "Phần trăm (%)", "Ngày bắt đầu", "Ngày kết thúc"}, 0);

        try (Connection conn = ConnectDatabase.getConnection()) {
            String sql = "SELECT * FROM MaKhuyenMai";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ten = rs.getString("TenKhuyenMai");
                int phanTram = rs.getInt("PhanTram");
                Date ngayBatDau = rs.getDate("NgayBatDau");
                Date ngayKetThuc = rs.getDate("NgayKetThuc");

                model.addRow(new Object[]{ten, phanTram, ngayBatDau.toString(), ngayKetThuc.toString()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}
