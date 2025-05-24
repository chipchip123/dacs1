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
-- Xoá bảng nếu có
DROP TABLE IF EXISTS SanPham;
DROP TABLE IF EXISTS LoaiSanPham;

-- Tạo bảng loại sản phẩm: tên làm khóa chính (kiểu chuỗi)
CREATE TABLE LoaiSanPham (
    TenLoaiSanPham NVARCHAR(100) PRIMARY KEY
);

-- Tạo bảng sản phẩm: dùng tên loại sản phẩm thay vì mã
CREATE TABLE SanPham (
    MaSanPham INT PRIMARY KEY,
    TenSanPham NVARCHAR(100) NOT NULL,
    TenLoaiSanPham NVARCHAR(100) NOT NULL,
    GiaSanPham FLOAT NOT NULL,
    SoLuong INT NOT NULL,
    DonVi NVARCHAR(50) NOT NULL,
    TrangThai NVARCHAR(50) DEFAULT N'Còn hàng',
    HinhAnh NVARCHAR(255),
    FOREIGN KEY (TenLoaiSanPham) REFERENCES LoaiSanPham(TenLoaiSanPham)
);

-- Xoá dữ liệu cũ (nếu cần)
DELETE FROM SanPham;
DELETE FROM LoaiSanPham;

-- Thêm các loại sản phẩm (tên)
INSERT INTO LoaiSanPham (TenLoaiSanPham) VALUES 
(N'Áo thun'),
(N'Áo sơ mi'),
(N'Quần jean'),
(N'Quần short'),
(N'Váy đầm'),
(N'Áo khoác');

-- Thêm sản phẩm (liên kết theo tên loại sản phẩm)
INSERT INTO SanPham (MaSanPham, TenSanPham, TenLoaiSanPham, GiaSanPham, SoLuong, DonVi, TrangThai, HinhAnh)
VALUES 
(301, N'Quần jean rách', N'Quần jean', 250000, 10, N'Cái', N'Còn hàng', N'jeanrach.jpg'),
(302, N'Quần đùi thể thao', N'Quần short', 180000, 15, N'Cái', N'Còn hàng', N'quandui.jpg');

SELECT *
 FROM SanPham

create table MaKhuyenMai(
	TenKhuyenMai NVARCHAR(100),
	PhanTram INT CHECK (PhanTram >= 0 AND PhanTram <= 100),
	NgayBatDau DATE,
	NgayKetThuc DATE,
	CHECK (NgayBatDau <= NgayKetThuc)
	)
CREATE TABLE NhanVien (
    Ten NVARCHAR(100) NOT NULL,
    CCCD VARCHAR(12) NOT NULL,
    SoDienThoai VARCHAR(15) NOT NULL,
    TaiKhoan VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(255) NOT NULL, -- có thể lưu mã hóa nếu muốn
    NamSinh DATE,
    GioiTinh NVARCHAR(10),
    Email VARCHAR(100)
);
INSERT INTO NhanVien (
    Ten, CCCD, SoDienThoai, TaiKhoan, MatKhau, NamSinh, GioiTinh, Email
) VALUES (
    N'Nguyễn Văn A',         -- Tên
    '012345678901',          -- CCCD
    '0901234567',            -- SĐT
    'nv01',                  -- Tài khoản (username)
    '123456',                -- Mật khẩu (chưa mã hóa, ví dụ)
    '1995-08-15',            -- Năm sinh
    N'Nam',                  -- Giới tính
    'nguyenvana@example.com'-- Email
);
