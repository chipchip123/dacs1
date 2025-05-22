package controller;

import com.formdev.flatlaf.FlatLaf;
import model.NguoiDungModel;
import view.DangNhapView;
import view.adminview;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Window;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.NhanVienView;

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
            private void setGiaoDien(){
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    for (Window window : Window.getWindows()){
                        SwingUtilities.updateComponentTreeUI(window);
                    }
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenDangNhap = view.txtTenDangNhap.getText().trim();
                String matKhau = new String(view.txtMatKhau.getPassword());

                boolean isAdmin = model.kiemtradangnhapAdmin(tenDangNhap, matKhau);
                boolean isNhanVien = model.kiemtradangnhapNhanvien(tenDangNhap, matKhau);

                if (isAdmin){
                    setGiaoDien();
                    new adminview().setVisible(true);
                    view.dispose();
                } else if (isNhanVien) {
                    setGiaoDien();
                    new NhanVienView().setVisible(true);
                    view.dispose();
                } else {
                    view.lblThongBao.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
                }
            }
        });
    }
}
