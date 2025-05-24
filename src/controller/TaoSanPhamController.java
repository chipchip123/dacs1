package controller;

import view.Taosanphammoi;
import DataAccessObject.SanPhamModelDAO;
import model.SanPhamModel;
import javax.swing.JOptionPane;

/**
 * Controller xử lý tạo sản phẩm
 */
public class TaoSanPhamController {
    private final Taosanphammoi view;
    private final SanPhamModelDAO dao;

    public TaoSanPhamController(Taosanphammoi view) {
        this.view = view;
        this.dao  = new SanPhamModelDAO();
    }

    /** Load danh sách loại sản phẩm lên combo */
    public void loadLoaiSanPham() {
        view.jComboBoxtaosanphammoi.removeAllItems();
        dao.getDanhSachTenLoaiSanPham()
           .forEach(view.jComboBoxtaosanphammoi::addItem);
    }

    /** Tạo sản phẩm mới khi bấm nút */
    public void createSanPham() {
        // 1. Validate mã sản phẩm
        String rawMa = view.jTextFieldmasp.getText();
        System.out.println("DEBUG — raw mã sản phẩm: [" + rawMa + "]");
        int ma;
        try {
            String trimmed = rawMa.replaceAll("\\s+", "");
            ma = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                "Mã sản phẩm phải là số nguyên và không để trống!",
                "Lỗi định dạng Mã sản phẩm",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1.1 Kiểm tra trùng mã
        if (dao.existsMaSanPham(ma)) {
            JOptionPane.showMessageDialog(view,
                "Mã sản phẩm đã tồn tại, vui lòng nhập mã khác!",
                "Lỗi trùng mã",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Tên sản phẩm
        String ten = view.jTextFieldtensp.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "Tên sản phẩm không được để trống!",
                "Lỗi Tên sản phẩm",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Loại sản phẩm
        String loai = (String) view.jComboBoxtaosanphammoi.getSelectedItem();
        if (loai == null || loai.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "Bạn phải chọn loại sản phẩm!",
                "Lỗi Loại sản phẩm",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Validate giá sản phẩm (hỗ trợ dấu phẩy)
        double gia;
        try {
            String giaText = view.jTextFieldgiasp.getText()
                                   .trim()
                                   .replace(",", ".");
            gia = Double.parseDouble(giaText);
            if (gia < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                "Giá sản phẩm phải là số và ≥ 0!\n" +
                "(Nếu cần thập phân, dùng dấu \".\" hoặc \",\" )",
                "Lỗi định dạng Giá",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 5. Validate số lượng
        int sl;
        try {
            String slText = view.jTextFieldsoluongsp.getText().trim();
            sl = Integer.parseInt(slText);
            if (sl < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                "Số lượng phải là số nguyên ≥ 0!",
                "Lỗi định dạng Số lượng",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 6. Đơn vị
        String dv = view.jTextFieldDonvi.getText().trim();
        if (dv.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "Đơn vị không được để trống!",
                "Lỗi Đơn vị",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 7. Ảnh
        String img = view.selectedImageFileName;
        if (img == null || img.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "Vui lòng chọn hình ảnh!",
                "Lỗi Ảnh",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 8. Tạo model và gọi DAO
        SanPhamModel sp = new SanPhamModel(
            ma, ten, loai, gia, sl, dv, "Còn hàng", img
        );
        boolean ok = dao.insertSanPham(sp);
        if (ok) {
            JOptionPane.showMessageDialog(view,
                "Tạo sản phẩm thành công!",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
            // Refresh bảng ở parentController (đã bảo đảm không null)
            view.parentController.showSanPhamList(dao.getAllSanPham());
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view,
                "Tạo sản phẩm thất bại. Vui lòng thử lại.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
