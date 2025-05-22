package controller;

import model.SanPhamModel;
import DataAccessObject.SanPhamModelDAO;
import view.QuanLySanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class QuanLySanPhamController {
    private QuanLySanPham view;
    private SanPhamModelDAO dao;
    private String currentImageFileName = "";
    private String originalImageFileName = "";

    public QuanLySanPhamController(QuanLySanPham view) {
        this.view = view;
        this.dao = new SanPhamModelDAO();
        initController();
    }

    private void initController() {
        List<SanPhamModel> list = dao.getAllSanPham();
        System.out.println("✅ Có " + list.size() + " sản phẩm trong DB.");
        showSanPhamList(list);

        view.jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = view.jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        TableModel model = view.jTable1.getModel();

                        int maSP = (int) model.getValueAt(selectedRow, 0);
                        String tenSP = (String) model.getValueAt(selectedRow, 1);
                        ImageIcon icon = (ImageIcon) model.getValueAt(selectedRow, 2);
                        String loaiSP = String.valueOf(model.getValueAt(selectedRow, 3));
                        String soLuong = String.valueOf(model.getValueAt(selectedRow, 4));
                        String giaBan = String.valueOf(model.getValueAt(selectedRow, 5));
                        String donVi = (String) model.getValueAt(selectedRow, 6);
                        String trangThai = (String) model.getValueAt(selectedRow, 7);
                        String tenFileAnh = (String) model.getValueAt(selectedRow, 8);

                        view.jTextFieldmasp.setText(String.valueOf(maSP));
                        view.jTextFieldmasp.setEnabled(false);
                        view.jTextFieldtensp.setText(tenSP);
                        view.jLabelanh.setIcon(icon);
                        currentImageFileName = tenFileAnh;
                        originalImageFileName = tenFileAnh;
                        view.jTextFieldloaisp.setText(loaiSP);
                        view.jTextFieldsoluongsp.setText(soLuong);
                        view.jTextFieldgiabansp.setText(giaBan);
                        view.jTextFielddonvisp.setText(donVi);
                        view.jTextFieldtrangthaisp.setText(trangThai);

                        view.jTabbedPane1.setSelectedIndex(1);
                    }
                }
            }
        });

        view.jLabelanh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn hình ảnh sản phẩm");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png"));
                int result = fileChooser.showOpenDialog(view);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    currentImageFileName = selectedFile.getName();

                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image img = icon.getImage().getScaledInstance(view.jLabelanh.getWidth(), view.jLabelanh.getHeight(), Image.SCALE_SMOOTH);
                    view.jLabelanh.setIcon(new ImageIcon(img));
                }
            }
        });

        initPlaceholderTimKiem();

        view.jButtontim.addActionListener(e -> {
            String keyword = view.jTextFieldnhaptenmasp.getText().trim();
            if (keyword.isEmpty() || keyword.equals("Nhập tên / mã sản phẩm")) {
                view.jLabelthongbaotim.setText("");
                showSanPhamList(dao.getAllSanPham());
                return;
            }

            List<SanPhamModel> result = dao.searchSanPham(keyword);

            if (result == null || result.isEmpty()) {
                view.jLabelthongbaotim.setForeground(Color.RED);
                view.jLabelthongbaotim.setText("Không tìm thấy sản phẩm nào!");
            } else {
                view.jLabelthongbaotim.setText("");
                showSanPhamList(result);
            }
        });

        view.jButtonHuytim.addActionListener(e -> {
            view.jTextFieldnhaptenmasp.setText("Nhập tên / mã sản phẩm");
            view.jTextFieldnhaptenmasp.setForeground(Color.GRAY);
            view.jLabelthongbaotim.setText("");
            showSanPhamList(dao.getAllSanPham());
        });

        view.jButtoncapnhap.addActionListener(e -> {
            try {
                int maSP = Integer.parseInt(view.jTextFieldmasp.getText().trim());
                String tenSP = view.jTextFieldtensp.getText().trim();
                int maLoaiSP = Integer.parseInt(view.jTextFieldloaisp.getText().trim());
                int soLuong = Integer.parseInt(view.jTextFieldsoluongsp.getText().trim());
                double giaBan = Double.parseDouble(view.jTextFieldgiabansp.getText().trim());
                String donVi = view.jTextFielddonvisp.getText().trim();
                String trangThai = view.jTextFieldtrangthaisp.getText().trim();

                String tenFileAnh = (currentImageFileName == null || currentImageFileName.isEmpty())
                        ? originalImageFileName
                        : currentImageFileName;

                SanPhamModel sp = new SanPhamModel(maSP, tenSP, maLoaiSP, giaBan, soLuong, donVi, trangThai, tenFileAnh);
                boolean success = dao.updateSanPham(sp);

                if (success) {
                    JOptionPane.showMessageDialog(view, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showSanPhamList(dao.getAllSanPham());
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật thất bại! Vui lòng kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi dữ liệu đầu vào!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void initPlaceholderTimKiem() {
        JTextField tf = view.jTextFieldnhaptenmasp;
        tf.setText("Nhập tên / mã sản phẩm");
        tf.setForeground(Color.GRAY);

        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (tf.getText().equals("Nhập tên / mã sản phẩm")) {
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (tf.getText().trim().isEmpty()) {
                    tf.setText("Nhập tên / mã sản phẩm");
                    tf.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void showSanPhamList(List<SanPhamModel> sanPhamList) {
        DefaultTableModel model = (DefaultTableModel) view.jTable1.getModel();
        model.setRowCount(0);

        for (SanPhamModel sp : sanPhamList) {
            ImageIcon scaledIcon = new ImageIcon();
            File f = new File("images/" + sp.getHinhAnh());
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(img);
            }

            model.addRow(new Object[]{
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                scaledIcon,
                sp.getMaLoaiSanPham(),
                sp.getSoLuong(),
                sp.getGiaSanPham(),
                sp.getDonVi(),
                sp.getTrangThai(),
                sp.getHinhAnh() // Ẩn nhưng cần cho cập nhật
            });
        }

        if (view.jTable1.getColumnCount() > 8) {
            view.jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            view.jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        }
    }
}
