package model;

public class SanPhamModel {
    private int maSanPham;
    private String tenSanPham;
    private int maLoaiSanPham;
    private double giaSanPham;
    private int soLuong;
    private String donVi;
    private String trangThai;
    private String hinhAnh;

    // Constructor
    public SanPhamModel(int maSanPham, String tenSanPham, int maLoaiSanPham, double giaSanPham,
                        int soLuong, String donVi, String trangThai, String hinhAnh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoaiSanPham = maLoaiSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    // Getters & Setters
    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public int getMaLoaiSanPham() { return maLoaiSanPham; }
    public void setMaLoaiSanPham(int maLoaiSanPham) { this.maLoaiSanPham = maLoaiSanPham; }

    public double getGiaSanPham() { return giaSanPham; }
    public void setGiaSanPham(double giaSanPham) { this.giaSanPham = giaSanPham; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getDonVi() { return donVi; }
    public void setDonVi(String donVi) { this.donVi = donVi; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}
