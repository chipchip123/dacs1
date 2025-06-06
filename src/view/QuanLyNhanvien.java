package view;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import DataAccessObject.QuanLyNhanVienDAO;
import controller.NhanVien;
import java.awt.Color;
import java.io.File;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class QuanLyNhanvien extends javax.swing.JPanel {

    
    public QuanLyNhanvien() {
        initComponents();
        placeholderSetup();
        jTable1.getSelectionModel().addListSelectionListener(e -> {
        // Đảm bảo không gọi 2 lần và có dòng được chọn
        if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
        int selectedRow = jTable1.getSelectedRow();

        jTextFieldHoTen.setText(jTable1.getValueAt(selectedRow, 0).toString());
        jTextFieldCCCD.setText(jTable1.getValueAt(selectedRow, 1).toString());
        jTextFieldSDT.setText(jTable1.getValueAt(selectedRow, 2).toString());
        jTextFieldTaikhoandn.setText(jTable1.getValueAt(selectedRow, 3).toString());
        jTextFieldMatkhaudn.setText(jTable1.getValueAt(selectedRow, 4).toString());
        jTextFieldNamsinh.setText(jTable1.getValueAt(selectedRow, 5).toString());
        
        String gioiTinh = jTable1.getValueAt(selectedRow, 6).toString();
        if ("Nam".equalsIgnoreCase(gioiTinh)) {
            jRadioButtonNam.setSelected(true);
        } else {
            jRadioButtonNu.setSelected(true);
        }

        jTextFieldEmail.setText(jTable1.getValueAt(selectedRow, 7).toString());
    }
});
        jTable1.setModel(new DefaultTableModel(
        new Object[][]{},
        new String[] { "Tên", "CCCD", "SĐT", "Tài khoản", "Mật khẩu", "Năm sinh", "Giới tính", "Email" }
        ) {
        boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });
        loadNhanVienToTable();
    }
    
    public void loadNhanVienToTable() {
    QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();
    List<NhanVien> list = dao.getDanhSachNhanVien();

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);  // Xóa hết dòng hiện có trước khi load mới

    for (NhanVien nv : list) {
        model.addRow(new Object[] {
            nv.getTen(),
            nv.getCccd(),
            nv.getSdt(),
            nv.getTaikhoan(),
            nv.getMatkhau(),
            nv.getNamsinh(),
            nv.getGioitinh(),
            nv.getEmail()
        });
    }
}
    
    private void placeholderSetup() {
    jTextFieldTim.setText("Nhập tên nhân viên...");
    jTextFieldTim.setForeground(Color.GRAY);

    jTextFieldTim.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (jTextFieldTim.getText().equals("Nhập tên nhân viên...")) {
                jTextFieldTim.setText("");
                jTextFieldTim.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (jTextFieldTim.getText().isEmpty()) {
                jTextFieldTim.setText("Nhập tên nhân viên...");
                jTextFieldTim.setForeground(Color.GRAY);
            }
        }
    });
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTim = new javax.swing.JTextField();
        jbuttonXuatExcel = new javax.swing.JButton();
        jbuttonTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelHoten = new javax.swing.JLabel();
        jLabelCCCD = new javax.swing.JLabel();
        jLabelNamsinh = new javax.swing.JLabel();
        jLabelGioitinh = new javax.swing.JLabel();
        jLabelSDT = new javax.swing.JLabel();
        jLabelTaikhoandn = new javax.swing.JLabel();
        jTextFieldHoTen = new javax.swing.JTextField();
        jTextFieldCCCD = new javax.swing.JTextField();
        jTextFieldSDT = new javax.swing.JTextField();
        jTextFieldNamsinh = new javax.swing.JTextField();
        jTextFieldTaikhoandn = new javax.swing.JTextField();
        jLabelMatkhau = new javax.swing.JLabel();
        jTextFieldMatkhaudn = new javax.swing.JTextField();
        jRadioButtonNam = new javax.swing.JRadioButton();
        jRadioButtonNu = new javax.swing.JRadioButton();
        jbuttonLamMoi = new javax.swing.JButton();
        jbuttonThem = new javax.swing.JButton();
        jbuttonXoa = new javax.swing.JButton();
        jbuttonCapNhat = new javax.swing.JButton();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1214, 673));
        setMinimumSize(new java.awt.Dimension(1214, 673));

        jPanel1.setBackground(new java.awt.Color(34, 47, 62));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản Lý Nhân Viên");

        jTextFieldTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimActionPerformed(evt);
            }
        });

        jbuttonXuatExcel.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonXuatExcel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonXuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonXuatExcel.setText("Xuất excel");
        jbuttonXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonXuatExcelActionPerformed(evt);
            }
        });

        jbuttonTim.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonTim.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonTim.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonTim.setText("Tìm");
        jbuttonTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldTim, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jbuttonTim, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jbuttonXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTim, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonTim, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Họ và tên", "Tài khoản", "Mật khẩu", "Năm sinh", "Giới Tính", "CCCD", "SDT", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setShowGrid(false);
        jScrollPane1.setViewportView(jTable1);

        jLabelHoten.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelHoten.setText("Họ và tên :");

        jLabelCCCD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelCCCD.setText("CCCD :");

        jLabelNamsinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelNamsinh.setText("Năm sinh :");

        jLabelGioitinh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelGioitinh.setText("Giới tính");

        jLabelSDT.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelSDT.setText("SDT :");

        jLabelTaikhoandn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelTaikhoandn.setText("Tên đăng nhập :");

        jTextFieldHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHoTenActionPerformed(evt);
            }
        });

        jLabelMatkhau.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelMatkhau.setText("Mật Khẩu :");

        jRadioButtonNam.setText("Nam");
        jRadioButtonNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNamActionPerformed(evt);
            }
        });

        jRadioButtonNu.setText("Nữ");

        jbuttonLamMoi.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonLamMoi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonLamMoi.setText("Làm mới");
        jbuttonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonLamMoiActionPerformed(evt);
            }
        });

        jbuttonThem.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonThem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonThem.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonThem.setText("Thêm ");
        jbuttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonThemActionPerformed(evt);
            }
        });

        jbuttonXoa.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonXoa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonXoa.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonXoa.setText("Xóa");
        jbuttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonXoaActionPerformed(evt);
            }
        });

        jbuttonCapNhat.setBackground(new java.awt.Color(34, 47, 62));
        jbuttonCapNhat.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jbuttonCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        jbuttonCapNhat.setText("Cập nhật");
        jbuttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCapNhatActionPerformed(evt);
            }
        });

        jLabelEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelEmail.setText("Email :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCCCD))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSDT))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNamsinh))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelGioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonNam)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonNu))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTaikhoandn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTaikhoandn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMatkhaudn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(159, 159, 159)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbuttonLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbuttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTaikhoandn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jTextFieldTaikhoandn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMatkhaudn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonNam)
                            .addComponent(jRadioButtonNu))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextFieldHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHoTenActionPerformed
        
    }//GEN-LAST:event_jTextFieldHoTenActionPerformed

    private void jRadioButtonNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNamActionPerformed
        
    }//GEN-LAST:event_jRadioButtonNamActionPerformed

    private void jbuttonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonLamMoiActionPerformed
        loadNhanVienToTable();
    }//GEN-LAST:event_jbuttonLamMoiActionPerformed

    private void jbuttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonThemActionPerformed
        String ten = jTextFieldHoTen.getText();
        String cccd = jTextFieldCCCD.getText();
        String sdt = jTextFieldSDT.getText();
        String taiKhoan = jTextFieldTaikhoandn.getText();
        String matKhau = jTextFieldMatkhaudn.getText();
        String gioiTinh = jRadioButtonNam.isSelected() ? "Nam" : "Nữ";
        String email = jTextFieldEmail.getText();
        Date namSinh = java.sql.Date.valueOf(jTextFieldNamsinh.getText()); // yyyy-MM-dd

        NhanVien nv = new NhanVien(ten, cccd, sdt, taiKhoan, matKhau, namSinh, gioiTinh, email);
        QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();

        if (dao.themNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            loadNhanVienToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }//GEN-LAST:event_jbuttonThemActionPerformed

    private void jbuttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonXoaActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa.");
            return;
        }

        String taiKhoan = jTable1.getValueAt(row, 3).toString(); // cột 3 là Tài khoản
        QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();

        if (dao.xoaNhanVien(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadNhanVienToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_jbuttonXoaActionPerformed

    private void jbuttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCapNhatActionPerformed
        int row = jTable1.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật.");
        return;
        }

        // Lấy dữ liệu từ bảng
        String ten = jTable1.getValueAt(row, 0).toString();
        String cccd = jTable1.getValueAt(row, 1).toString();
        String sdt = jTable1.getValueAt(row, 2).toString();
        String taiKhoan = jTable1.getValueAt(row, 3).toString();
        String matKhau = jTable1.getValueAt(row, 4).toString();
        java.sql.Date namSinh = java.sql.Date.valueOf(jTable1.getValueAt(row, 5).toString());
        String gioiTinh = jTable1.getValueAt(row, 6).toString();
        String email = jTable1.getValueAt(row, 7).toString();

        // Tạo đối tượng NhanVien
        NhanVien nv = new NhanVien(ten, cccd, sdt, taiKhoan, matKhau, namSinh, gioiTinh, email);

        // Gọi DAO cập nhật
        QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();
        if (dao.capNhatNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadNhanVienToTable(); // Reload lại bảng
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }//GEN-LAST:event_jbuttonCapNhatActionPerformed

    private void jTextFieldTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimActionPerformed
        jTextFieldTim.setText("Nhập tên nhân viên...");
        jTextFieldTim.setForeground(Color.GRAY);

        jTextFieldTim.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (jTextFieldTim.getText().equals("Nhập tên nhân viên...")) {
                jTextFieldTim.setText("");
                jTextFieldTim.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (jTextFieldTim.getText().isEmpty()) {
                jTextFieldTim.setText("Nhập tên nhân viên...");
                jTextFieldTim.setForeground(Color.GRAY);
            }
        }
    });
    }//GEN-LAST:event_jTextFieldTimActionPerformed

    private void jbuttonXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonXuatExcelActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Lưu danh sách nhân viên");
        chooser.setSelectedFile(new File("DanhSachNhanVien.xlsx"));

        int option = chooser.showSaveDialog(this); // 'this' là JFrame chứa nút
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();
            dao.xuatDanhSachNhanVienRaExcel(file);
        }
    }//GEN-LAST:event_jbuttonXuatExcelActionPerformed

    private void jbuttonTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonTimActionPerformed
        String tenCanTim = jTextFieldTim.getText().trim();
        if (tenCanTim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên cần tìm.");
            return;
        }

        QuanLyNhanVienDAO dao = new QuanLyNhanVienDAO();
        List<NhanVien> ketQua = dao.timNhanVienTheoTen(tenCanTim);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        for (NhanVien nv : ketQua) {
            model.addRow(new Object[]{
                nv.getTen(), nv.getCccd(), nv.getSdt(), nv.getTaikhoan(), nv.getMatkhau(),
                nv.getNamsinh(), nv.getGioitinh(), nv.getEmail()
            });
        }

        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào.");
        }
    }//GEN-LAST:event_jbuttonTimActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCCCD;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelGioitinh;
    private javax.swing.JLabel jLabelHoten;
    private javax.swing.JLabel jLabelMatkhau;
    private javax.swing.JLabel jLabelNamsinh;
    private javax.swing.JLabel jLabelSDT;
    private javax.swing.JLabel jLabelTaikhoandn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonNam;
    private javax.swing.JRadioButton jRadioButtonNu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCCCD;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHoTen;
    private javax.swing.JTextField jTextFieldMatkhaudn;
    private javax.swing.JTextField jTextFieldNamsinh;
    private javax.swing.JTextField jTextFieldSDT;
    private javax.swing.JTextField jTextFieldTaikhoandn;
    private javax.swing.JTextField jTextFieldTim;
    private javax.swing.JButton jbuttonCapNhat;
    private javax.swing.JButton jbuttonLamMoi;
    private javax.swing.JButton jbuttonThem;
    private javax.swing.JButton jbuttonTim;
    private javax.swing.JButton jbuttonXoa;
    private javax.swing.JButton jbuttonXuatExcel;
    // End of variables declaration//GEN-END:variables
}
