package test;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import controller.DangNhapController;
import model.NguoiDungModel;
import view.DangNhapView;
import view.adminview;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Set giao diện tối (dark mode) cho màn đăng nhập
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        DangNhapView view = new DangNhapView();
        NguoiDungModel model = new NguoiDungModel();

        // Tạo controller, truyền thêm tham chiếu main frame để đổi theme sau khi đăng nhập
        new DangNhapController(view, model);

        view.setVisible(true);
    }
}
