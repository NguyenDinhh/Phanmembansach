package com.example.phanmembansach;

public class GioHang {
    private  String TenDangNhap;
    private  Integer SachID;
    private  String Ten;
    private  String Anh;
    private  Integer SoLuong;
    private  Double Gia;
    private  Integer SoLuongMua;

    public GioHang() {
    }

    public GioHang(String tenDangNhap, Integer sachID, String ten, String anh, Integer soLuong, Double gia, Integer soLuongMua) {
        TenDangNhap = tenDangNhap;
        SachID = sachID;
        Ten = ten;
        Anh = anh;
        SoLuong = soLuong;
        Gia = gia;
        SoLuongMua = soLuongMua;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public Integer getSachID() {
        return SachID;
    }

    public void setSachID(Integer sachID) {
        SachID = sachID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    public Integer getSoLuongMua() {
        return SoLuongMua;
    }

    public void setSoLuongMua(Integer soLuongMua) {
        SoLuongMua = soLuongMua;
    }
}