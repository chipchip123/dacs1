package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class DangNhapView extends JFrame {
    public JTextField txtTenDangNhap;
    public JPasswordField txtMatKhau;
    public JButton btnDangNhap;
    public JLabel lblThongBao;

    public DangNhapView() {
        setTitle("ĐĂNG NHẬP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 47, 62));
        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(420, 370));
        mainPanel.setBackground(new Color(44, 62, 80));

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 15);

        JLabel lblTitle = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(0, 20, 420, 30);
        mainPanel.add(lblTitle);

        JLabel lblUser = new JLabel("User Name:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(fontLabel);
        lblUser.setBounds(60, 70, 100, 25);
        mainPanel.add(lblUser);

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setFont(fontInput);
        txtTenDangNhap.setBounds(60, 95, 300, 32);
        mainPanel.add(txtTenDangNhap);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setFont(fontLabel);
        lblPass.setBounds(60, 135, 100, 25);
        mainPanel.add(lblPass);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(fontInput);
        txtMatKhau.setBounds(60, 160, 265, 32);
        txtMatKhau.setEchoChar('•');
        mainPanel.add(txtMatKhau);

        ImageIcon iconEye = loadScaledIcon("/image/eye.png", 20, 20);
        ImageIcon iconEyeOff = loadScaledIcon("/image/eye-off.png", 20, 20);

        JToggleButton btnEye = new JToggleButton(iconEye);
        btnEye.setBounds(330, 160, 30, 32);
        btnEye.setFocusable(false);
        btnEye.setContentAreaFilled(false);
        btnEye.setBorderPainted(false);
        btnEye.setOpaque(false);
        btnEye.addActionListener(e -> {
            boolean hien = btnEye.isSelected();
            txtMatKhau.setEchoChar(hien ? (char) 0 : '•');
            btnEye.setIcon(hien ? iconEyeOff : iconEye);
        });
        mainPanel.add(btnEye);

        JLabel lblQuenMK = new JLabel("<html><u>Quên mật khẩu?</u></html>");
        lblQuenMK.setForeground(new Color(173, 216, 230));
        lblQuenMK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblQuenMK.setBounds(60, 200, 150, 20);
        lblQuenMK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblQuenMK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(DangNhapView.this,
                        "Vui lòng liên hệ Admin:\n📧 Email: admin@example.com\n📞 SĐT: 0987 654 321",
                        "Hỗ trợ đặt lại mật khẩu", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainPanel.add(lblQuenMK);

        btnDangNhap = new JButton("Login");
        btnDangNhap.setFont(fontButton);
        btnDangNhap.setBounds(110, 235, 200, 38);
        mainPanel.add(btnDangNhap);

        lblThongBao = new JLabel("", SwingConstants.CENTER);
        lblThongBao.setForeground(Color.RED);
        lblThongBao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblThongBao.setBounds(60, 285, 300, 20);
        mainPanel.add(lblThongBao);

        add(mainPanel);
        pack();
    }

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            System.err.println("Không tìm thấy icon: " + path);
            return new ImageIcon();
        }
    }
}
