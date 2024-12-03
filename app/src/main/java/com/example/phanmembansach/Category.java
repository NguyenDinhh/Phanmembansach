package com.example.phanmembansach;

public class Category {
    private  String TenTheLoai;
    private  Integer TheLoaiID;
    private String MoTa;
    private Integer DaBan;
    private  String Anh;
    private Integer SoLuong;

    public Category() {
    }

    public Category(String tenTheLoai, Integer theLoaiID, String moTa, Integer daBan, String anh, Integer soLuong) {
        TenTheLoai = tenTheLoai;
        TheLoaiID = theLoaiID;
        MoTa = moTa;
        DaBan = daBan;
        Anh = anh;
        SoLuong = soLuong;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public Integer getTheLoaiID() {
        return TheLoaiID;
    }

    public void setTheLoaiID(Integer theLoaiID) {
        TheLoaiID = theLoaiID;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public Integer getDaBan() {
        return DaBan;
    }

    public void setDaBan(Integer daBan) {
        DaBan = daBan;
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
}
