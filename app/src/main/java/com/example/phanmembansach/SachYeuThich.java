package com.example.phanmembansach;

public class SachYeuThich {
    private  Integer SachID;
    private  String TenDangNhap;

    public SachYeuThich() {
    }

    public SachYeuThich(Integer sachID, String tenDangNhap) {
        SachID = sachID;
        TenDangNhap = tenDangNhap;
    }

    public Integer getSachID() {
        return SachID;
    }

    public void setSachID(Integer sachID) {
        SachID = sachID;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }
}
