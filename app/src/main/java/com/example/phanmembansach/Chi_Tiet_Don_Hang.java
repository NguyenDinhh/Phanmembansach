package com.example.phanmembansach;

import android.widget.ImageView;
import android.widget.TextView;

public class Chi_Tiet_Don_Hang {
    private Integer ChiTietDonHangID;
    private  Integer DonHangID;
    private  double Gia;
    private  Integer SoLuong;
    private  Integer SachID;
    private String  Anh;
    private String Ten;

    public Chi_Tiet_Don_Hang() {
    }

    public Chi_Tiet_Don_Hang(Integer chiTietDonHangID, Integer donHangID, double gia, Integer soLuong, Integer sachID) {
        ChiTietDonHangID = chiTietDonHangID;
        DonHangID = donHangID;
        Gia = gia;
        SoLuong = soLuong;
        SachID = sachID;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Integer getChiTietDonHangID() {
        return ChiTietDonHangID;
    }

    public void setChiTietDonHangID(Integer chiTietDonHangID) {
        ChiTietDonHangID = chiTietDonHangID;
    }

    public Integer getDonHangID() {
        return DonHangID;
    }

    public void setDonHangID(Integer donHangID) {
        DonHangID = donHangID;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Integer getSachID() {
        return SachID;
    }

    public void setSachID(Integer sachID) {
        SachID = sachID;
    }
}
