package model;

import java.time.LocalDate;

public class KhachHang {
    private String soDienThoai;
    private String tenKhachHang;
    private LocalDate ngayTaoTaiKhoan;
    private int diemTichLuy;

    public KhachHang(String soDienThoai, String tenKhachHang, LocalDate ngayTaoTaiKhoan, int diemTichLuy) {
        this.soDienThoai = soDienThoai;
        this.tenKhachHang = tenKhachHang;
        this.ngayTaoTaiKhoan = ngayTaoTaiKhoan;
        this.diemTichLuy = diemTichLuy;
    }

    public String getSoDienThoai() { return soDienThoai; }
    public String getTenKhachHang() { return tenKhachHang; }
    public LocalDate getNgayTaoTaiKhoan() { return ngayTaoTaiKhoan; }
    public int getDiemTichLuy() { return diemTichLuy; }
}
