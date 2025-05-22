package controller;

import java.util.*;

public class NhanVien {
    private String ten;
    private String cccd;
    private String sdt;
    private String taikhoan;
    private String matkhau;
    private Date namsinh;
    private String gioitinh;
    private String email;

    public NhanVien(String ten, String cccd, String sdt, String taikhoan, String matkhau, Date namsinh, String gioitinh, String email) {
        this.ten = ten;
        this.cccd = cccd;
        this.sdt = sdt;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.namsinh = namsinh;
        this.gioitinh = gioitinh;
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public String getCccd() {
        return cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public Date getNamsinh() {
        return namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getEmail() {
        return email;
    }
    
    
}
