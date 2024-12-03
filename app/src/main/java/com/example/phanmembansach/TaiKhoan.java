package com.example.phanmembansach;

public class TaiKhoan {
    private  Integer NhanVienID;
    private  Integer KhachHangID;
    private  String TenDangNhap;
    private  String MatKhau;
    private  String Anh;
    private  String TinhTrang;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer nhanVienID, Integer khachHangID, String tenDangNhap, String matKhau, String anh, String tinhTrang) {
        NhanVienID = nhanVienID;
        KhachHangID = khachHangID;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        Anh = anh;
        TinhTrang = tinhTrang;
    }

    public Integer getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(Integer nhanVienID) {
        NhanVienID = nhanVienID;
    }

    public Integer getKhachHangID() {
        return KhachHangID;
    }

    public void setKhachHangID(Integer khachHangID) {
        KhachHangID = khachHangID;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
