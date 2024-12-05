package com.example.phanmembansach;

public class DonHang {
    private  Integer DiaChiNhanHangID;
    private  Integer DonHangID;
    private  String TenDangNhap;
    private  String TinhTrang;

    public DonHang() {
    }

    public DonHang(Integer diaChiNhanHangID, Integer donHangID, String tenDangNhap, String tinhTrang) {
        DiaChiNhanHangID = diaChiNhanHangID;
        DonHangID = donHangID;
        TenDangNhap = tenDangNhap;
        TinhTrang = tinhTrang;
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
}
