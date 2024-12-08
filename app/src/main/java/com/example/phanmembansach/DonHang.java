package com.example.phanmembansach;

public class DonHang {
    private  Integer DiaChiNhanHangID;
    private  Integer DonHangID;
    private  String TenDangNhap;
    private  String TinhTrang;
    private  Double TongTien;
    public DonHang() {
    }

    public DonHang(Integer diaChiNhanHangID, Integer donHangID, String tenDangNhap, String tinhTrang, Double tongTien) {
        DiaChiNhanHangID = diaChiNhanHangID;
        DonHangID = donHangID;
        TenDangNhap = tenDangNhap;
        TinhTrang = tinhTrang;
        TongTien = tongTien;
    }

    public Integer getDiaChiNhanHangID() {
        return DiaChiNhanHangID;
    }

    public void setDiaChiNhanHangID(Integer diaChiNhanHangID) {
        DiaChiNhanHangID = diaChiNhanHangID;
    }

    public Integer getDonHangID() {
        return DonHangID;
    }

    public void setDonHangID(Integer donHangID) {
        DonHangID = donHangID;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public Double getTongTien() {
        return TongTien;
    }

    public void setTongTien(Double tongTien) {
        TongTien = tongTien;
    }
}
