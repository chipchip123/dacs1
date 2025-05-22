use DoAnCs1
-- Tạo bảng NgườiDung nếu chưa có
CREATE TABLE NguoiDung (
    MaNguoiDung INT PRIMARY KEY IDENTITY(1,1),
    TenDangNhap NVARCHAR(50) NOT NULL UNIQUE,
    MatKhau NVARCHAR(100) NOT NULL,
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(100),
    LaAdmin BIT DEFAULT 0,
    DangHoatDong BIT DEFAULT 1
);

-- Tạo bảng PhanQuyen
CREATE TABLE PhanQuyen (
    MaQuyen INT PRIMARY KEY IDENTITY(1,1),
    TenQuyen NVARCHAR(50) NOT NULL UNIQUE,
    MoTa NVARCHAR(255)
);

-- Tạo bảng phân quyền người dùng
CREATE TABLE NguoiDung_PhanQuyen (
    MaNguoiDung INT NOT NULL,
    MaQuyen INT NOT NULL,
    PRIMARY KEY (MaNguoiDung, MaQuyen),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaQuyen) REFERENCES PhanQuyen(MaQuyen)
);

-- Thêm dữ liệu mẫu
INSERT INTO NguoiDung (TenDangNhap, MatKhau, HoTen, SoDienThoai, Email, LaAdmin)
VALUES 
    ('admin', '21232f297a57a5a743894a0e4a801fc3', 'Nguyễn Văn Admin', '0987654321', 'admin@example.com', 1),
    ('user1', 'ee11cbb19052e40b07aac0ca060c23ee', 'Trần Thị User', '0912345678', 'user1@example.com', 0);

INSERT INTO PhanQuyen (TenQuyen, MoTa)
VALUES 
    ('QuanTri', 'Quyền quản trị hệ thống'),
    ('XemBaoCao', 'Xem các báo cáo'),
    ('QuanLyNhanVien', 'Quản lý thông tin nhân viên');

-- Phân quyền cho admin
INSERT INTO NguoiDung_PhanQuyen (MaNguoiDung, MaQuyen)
VALUES (1, 1), (1, 2), (1, 3);

-- Phân quyền cho user thường
INSERT INTO NguoiDung_PhanQuyen (MaNguoiDung, MaQuyen)

-- sử lý cho phần sản phầm 
CREATE TABLE SanPham (
    MaSanPham INT PRIMARY KEY,
    TenSanPham NVARCHAR(100) NOT NULL,
    MaLoaiSanPham INT NOT NULL FOREIGN KEY REFERENCES LoaiSanPham(MaLoaiSanPham),
    GiaSanPham FLOAT NOT NULL,
    SoLuong INT NOT NULL,
    DonVi NVARCHAR(50) NOT NULL,
    TrangThai NVARCHAR(50) DEFAULT N'Còn hàng',
    HinhAnh NVARCHAR(255)
);
CREATE TABLE LoaiSanPham (
    MaLoaiSanPham INT PRIMARY KEY IDENTITY(1,1),
    TenLoaiSanPham NVARCHAR(100) NOT NULL UNIQUE
);
DELETE FROM LoaiSanPham; -- nếu có dữ liệu cũ

INSERT INTO LoaiSanPham (TenLoaiSanPham) VALUES 
(N'Áo thun'),
(N'Áo sơ mi'),
(N'Quần jean'),
(N'Quần short'),
(N'Váy đầm'),
(N'Áo khoác');
DELETE FROM SanPham; -- nếu cần xóa dữ liệu cũ
DELETE FROM SanPham;
INSERT INTO SanPham (MaSanPham, TenSanPham, MaLoaiSanPham, GiaSanPham, SoLuong, DonVi, TrangThai, HinhAnh)
VALUES (301, N'Quần jean ', 3, 250000, 10, N'Cái', N'Còn hàng', N'jeanrach.jpg');

INSERT INTO SanPham (MaSanPham, TenSanPham, MaLoaiSanPham, GiaSanPham, SoLuong, DonVi, TrangThai, HinhAnh)
VALUES (302, N'Quần đùi', 3, 250000, 10, N'Cái', N'Còn hàng', N'quandui.jpg');


SELECT *
 FROM SanPham

create table MaKhuyenMai(
	TenKhuyenMai NVARCHAR(100),
	PhanTram INT CHECK (PhanTram >= 0 AND PhanTram <= 100),
	NgayBatDau DATE,
	NgayKetThuc DATE,
	CHECK (NgayBatDau <= NgayKetThuc)
	)

