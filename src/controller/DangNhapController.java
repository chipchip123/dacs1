package controller;

import model.NguoiDungModel;
import view.DangNhapView;
import view.adminview;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Window;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhapController {
    private final DangNhapView view;
    private final NguoiDungModel model;

    public DangNhapController(DangNhapView view, NguoiDungModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        view.btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenDangNhap = view.txtTenDangNhap.getText().trim();
                String matKhau = new String(view.txtMatKhau.getPassword());

                String role = model.kiemTraDangNhap(tenDangNhap, matKhau);
                if (role == null) {
                    view.lblThongBao.setText("Sai tên đăng nhập hoặc mật khẩu");
                } else {
                    // Đổi giao diện sang Light mode trước khi mở adminview
                    try {
                        UIManager.setLookAndFeel(new FlatLightLaf());
                        // Cập nhật giao diện tất cả cửa sổ hiện tại
                        for (Window window : Window.getWindows()) {
                            SwingUtilities.updateComponentTreeUI(window);
                        }
                    } catch (UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }

                    if (role.equalsIgnoreCase("admin")) {
                        new adminview().setVisible(true);
                    } else {
                        // new NhanVienView().setVisible(true);
                    }
                    view.dispose();
                }
            }
        });
    }
}
