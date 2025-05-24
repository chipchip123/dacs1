
package controller;

import model.SanPhamModel;
import DataAccessObject.SanPhamModelDAO;
import view.QuanLySanPham;
import view.Taosanphammoi;
import controller.TaoSanPhamController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QuanLySanPhamController {
    private final QuanLySanPham view;
    private final SanPhamModelDAO dao;
    private String currentImageFileName  = "";
    private String originalImageFileName = "";

    public QuanLySanPhamController(QuanLySanPham view) {
        this.view = view;
        this.dao  = new SanPhamModelDAO();
        initController();
    }

    private void initController() {
        // 0) **Thêm listener cho nút Xuất Excel** ngay ở đây**
        view.jButtonxuatexcel.addActionListener(evt -> exportToXLSX());
        // 1. Load tên loại sản phẩm lên combo chính (trong tab Chi tiết)
        loadLoaiSanPhamToComboBox();

        // 2. Hiển thị danh sách sản phẩm lên bảng
        showSanPhamList(dao.getAllSanPham());

        // 3. Double-click để chuyển sang tab Chi tiết và đổ dữ liệu
        view.jTable1.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = view.jTable1.getSelectedRow();
                    if (row < 0) return;

                    TableModel m = view.jTable1.getModel();
                    int    ma   = (int)        m.getValueAt(row, 0);
                    String ten  = (String)     m.getValueAt(row, 1);
                    ImageIcon ic= (ImageIcon)  m.getValueAt(row, 2);
                    String loai = (String)     m.getValueAt(row, 3);
                    String sl   = String.valueOf(m.getValueAt(row, 4));
                    String gia  = String.valueOf(m.getValueAt(row, 5));
                    String dv   = (String)     m.getValueAt(row, 6);
                    String tt   = (String)     m.getValueAt(row, 7);
                    String fn   = (String)     m.getValueAt(row, 8);

                    // Đổ dữ liệu vào form Chi tiết
                    view.jTextFieldmasp.setText(String.valueOf(ma));
                    view.jTextFieldmasp.setEnabled(false);
                    view.jTextFieldtensp.setText(ten);
                    view.jComboBox1.setSelectedItem(loai);
                    view.jTextFieldsoluongsp.setText(sl);
                    view.jTextFieldgiabansp.setText(gia);
                    view.jTextFielddonvisp.setText(dv);
                    view.jTextFieldtrangthaisp.setText(tt);
                    view.jLabelanh.setIcon(ic);

                    currentImageFileName  = fn;
                    originalImageFileName = fn;

                    view.jTabbedPane1.setSelectedIndex(1);
                }
            }
        });

        // 4. Click vào ảnh để chọn file mới
        view.jLabelanh.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Chọn hình ảnh sản phẩm");
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh","jpg","jpeg","png"));
                if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    currentImageFileName = f.getName();
                    ImageIcon ic = new ImageIcon(f.getAbsolutePath());
                    Image img   = ic.getImage().getScaledInstance(
                        view.jLabelanh.getWidth(),
                        view.jLabelanh.getHeight(),
                        Image.SCALE_SMOOTH
                    );
                    view.jLabelanh.setIcon(new ImageIcon(img));
                }
            }
        });

        // 5. Placeholder cho ô Tìm kiếm
        initPlaceholderTimKiem();

        // 6. Nút Tìm
        view.jButtontim.addActionListener(e -> {
            String kw = view.jTextFieldnhaptenmasp.getText().trim();
            if (kw.isEmpty() || kw.equals("Nhập tên / mã sản phẩm")) {
                view.jLabelthongbaotim.setText("");
                showSanPhamList(dao.getAllSanPham());
            } else {
                List<SanPhamModel> rs = dao.searchSanPham(kw);
                if (rs.isEmpty()) {
                    view.jLabelthongbaotim.setForeground(Color.RED);
                    view.jLabelthongbaotim.setText("Không tìm thấy sản phẩm nào!");
                } else {
                    view.jLabelthongbaotim.setText("");
                    showSanPhamList(rs);
                }
            }
        });

        // 7. Nút Hủy tìm
        view.jButtonHuytim.addActionListener(e -> {
            view.jTextFieldnhaptenmasp.setText("Nhập tên / mã sản phẩm");
            view.jTextFieldnhaptenmasp.setForeground(Color.GRAY);
            view.jLabelthongbaotim.setText("");
            showSanPhamList(dao.getAllSanPham());
        });

        // 8. Nút Cập nhật
        view.jButtoncapnhap.addActionListener(e -> {
            try {
                int    ma    = Integer.parseInt(view.jTextFieldmasp.getText().trim());
                String ten   = view.jTextFieldtensp.getText().trim();
                String loai  = (String) view.jComboBox1.getSelectedItem();
                if ("Khoản Thắng".equalsIgnoreCase(loai)) {
                    JOptionPane.showMessageDialog(view,
                        "Không được chọn loại 'Khoản Thắng'.",
                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int    sl    = Integer.parseInt(view.jTextFieldsoluongsp.getText().trim());
                double gia   = Double.parseDouble(view.jTextFieldgiabansp.getText().trim());
                String dv    = view.jTextFielddonvisp.getText().trim();
                String tt    = view.jTextFieldtrangthaisp.getText().trim();
                String fn    = currentImageFileName.isEmpty()
                             ? originalImageFileName
                             : currentImageFileName;

                SanPhamModel sp = new SanPhamModel(ma, ten, loai, gia, sl, dv, tt, fn);
                if (dao.updateSanPham(sp)) {
                    JOptionPane.showMessageDialog(view,
                        "Cập nhật thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                    showSanPhamList(dao.getAllSanPham());
                } else {
                    JOptionPane.showMessageDialog(view,
                        "Cập nhật thất bại!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view,
                    "Lỗi dữ liệu đầu vào!", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
            }
        });

        // 9. Nút Làm mới
        view.jButtonlammoi.addActionListener(e -> {
            view.jTextFieldmasp.setText("");
            view.jTextFieldmasp.setEnabled(true);
            view.jTextFieldtensp .setText("");
            view.jComboBox1       .setSelectedIndex(0);
            view.jTextFieldsoluongsp.setText("");
            view.jTextFieldgiabansp .setText("");
            view.jTextFielddonvisp  .setText("");
            view.jTextFieldtrangthaisp.setText("");
            view.jLabelanh        .setIcon(null);
            currentImageFileName  = "";
            originalImageFileName = "";
        });

        // 10. Nút Tạo sản phẩm mới
        view.jButtontaosanphammoi.addActionListener(e -> {
            Taosanphammoi form = new Taosanphammoi(this);
            form.parentController = this;
            // controller con sẽ load combo và bắt nút Create
            new TaoSanPhamController(form).loadLoaiSanPham();
            form.setLocationRelativeTo(view);
            form.setVisible(true);
        });
    }

    private void initPlaceholderTimKiem() {
        JTextField tf = view.jTextFieldnhaptenmasp;
        tf.setText("Nhập tên / mã sản phẩm");
        tf.setForeground(Color.GRAY);
        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override public void focusGained(java.awt.event.FocusEvent e) {
                if (tf.getText().equals("Nhập tên / mã sản phẩm")) {
                    tf.setText(""); tf.setForeground(Color.BLACK);
                }
            }
            @Override public void focusLost(java.awt.event.FocusEvent e) {
                if (tf.getText().trim().isEmpty()) {
                    tf.setText("Nhập tên / mã sản phẩm");
                    tf.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void loadLoaiSanPhamToComboBox() {
        view.jComboBox1.removeAllItems();
        dao.getDanhSachTenLoaiSanPham().stream()
           .filter(t -> !t.equalsIgnoreCase("Khoản Thắng"))
           .forEach(view.jComboBox1::addItem);
    }

    public void showSanPhamList(List<SanPhamModel> list) {
        DefaultTableModel model = (DefaultTableModel) view.jTable1.getModel();
        model.setRowCount(0);
        for (SanPhamModel sp : list) {
            ImageIcon ic = new ImageIcon("images/" + sp.getHinhAnh());
            Image img    = ic.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            model.addRow(new Object[]{
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                new ImageIcon(img),
                sp.getTenLoaiSanPham(),
                sp.getSoLuong(),
                sp.getGiaSanPham(),
                sp.getDonVi(),
                sp.getTrangThai(),
                sp.getHinhAnh()
            });
        }
        if (view.jTable1.getColumnCount() > 8) {
            view.jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            view.jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        }
    }
    
private void exportToXLSX() {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Lưu file Excel (.xlsx)");
    chooser.setSelectedFile(new File("DanhSachSanPham.xlsx"));
    if (chooser.showSaveDialog(view) != JFileChooser.APPROVE_OPTION) return;

    try (
        Workbook wb = new XSSFWorkbook();
        FileOutputStream out = new FileOutputStream(chooser.getSelectedFile())
    ) {
        // 1) Tạo sheet
        XSSFSheet sheet = ((XSSFWorkbook)wb).createSheet("Sản phẩm");


        // 2) Lấy model từ JTable
        TableModel model = view.jTable1.getModel();
        int cols = model.getColumnCount();
        int rows = model.getRowCount();

        // 3) Xác định index cột ảnh (tiêu đề column phải đúng)
        int imgCol = -1;
        for (int c = 0; c < cols; c++) {
            if (model.getColumnName(c).equalsIgnoreCase("Ảnh")) {
                imgCol = c;
                break;
            }
        }

        // 4) Ghi header (bỏ cột ảnh)
        Row header = sheet.createRow(0);
        for (int c = 0, outC = 0; c < cols; c++) {
            if (c == imgCol) continue;
            header.createCell(outC++)
                  .setCellValue(model.getColumnName(c));
        }

        // 5) Ghi dữ liệu
        for (int r = 0; r < rows; r++) {
            Row row = sheet.createRow(r + 1);
            for (int c = 0, outC = 0; c < cols; c++) {
                if (c == imgCol) continue;
                Object val = model.getValueAt(r, c);
                row.createCell(outC++)
                   .setCellValue(val != null ? val.toString() : "");
            }
        }

        // 6) Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < header.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }

        // 7) Ghi file ra đĩa
        wb.write(out);
        JOptionPane.showMessageDialog(view,
            "Xuất .xlsx thành công!", "Thông báo",
            JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(view,
            "Lỗi khi xuất .xlsx:\n" + ex.getMessage(),
            "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    
}
