package com.example.phanmembansach;

public class Address {
    private String Ten;
    private String DienThoai;
    private Integer DiaChiNhanHangID;
    private String DiaChi;
    private String TenDangNhap;

    public Address() {
    }

    public Address(String ten, String dienThoai, Integer diaChiNhanHangID, String diaChi, String tenDangNhap) {
        Ten = ten;
        DienThoai = dienThoai;
        DiaChiNhanHangID = diaChiNhanHangID;
        DiaChi = diaChi;
        TenDangNhap = tenDangNhap;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public Integer getDiaChiNhanHangID() {
        return DiaChiNhanHangID;
    }

    public void setDiaChiNhanHangID(Integer diaChiNhanHangID) {
        DiaChiNhanHangID = diaChiNhanHangID;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }
}
