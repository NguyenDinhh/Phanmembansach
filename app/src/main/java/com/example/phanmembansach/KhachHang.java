package com.example.phanmembansach;

public class KhachHang {
    private  Integer KhachHangID;
    private  String Ten;
    private  String Email;
    private  String DienThoai;
    private  String DiaChi;
    private  Integer Diemthuong;

    public KhachHang() {
    }

    public KhachHang(Integer khachHangID, String ten, String email, String dienThoai, String diaChi, Integer diemThuong) {
        KhachHangID = khachHangID;
        Ten = ten;
        Email = email;
        DienThoai = dienThoai;
        DiaChi = diaChi;
        Diemthuong = diemThuong;
    }

    public Integer getKhachHangID() {
        return KhachHangID;
    }

    public void setKhachHangID(Integer khachHangID) {
        KhachHangID = khachHangID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public Integer getDiemThuong() {
        return Diemthuong;
    }

    public void setDiemThuong(Integer diemThuong) {
        Diemthuong = diemThuong;
    }
}
