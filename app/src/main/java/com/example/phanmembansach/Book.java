package com.example.phanmembansach;

import android.media.Image;
import android.widget.ImageView;

public class Book {
    private Integer SachID;
    private Integer NhaXuaBanID;
    private String Ten;
    private String Mota;
    private Integer TacGiaID;
    private Integer TheLoaiID;
    private double Sao;
    private double Gia;
    private Integer SoLuong;
    private Integer DaBan;
    private String Anh;
    private  double Sale;
    private  Integer DiemThuong;
    private String NgayKhoiBan;
    private  String NgayXuatBan;
    private  String TenTacGia;
    public Book() {

    }

    public Book(Integer sachID, Integer nhaXuaBanID, String ten, String mota, Integer tacgiaID, Integer theLoaiID, double sao, double gia, Integer soLuong, Integer daBan, String anh, double sale, Integer diemThuong, String ngayKhoiBan, String ngayXuatBan) {
        SachID = sachID;
        NhaXuaBanID = nhaXuaBanID;
        Ten = ten;
        Mota = mota;
        TacGiaID = tacgiaID;
        TheLoaiID = theLoaiID;
        Sao = sao;
        Gia = gia;
        SoLuong = soLuong;
        DaBan = daBan;
        Anh = anh;
        Sale = sale;
        DiemThuong = diemThuong;
        NgayKhoiBan = ngayKhoiBan;
        NgayXuatBan = ngayXuatBan;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public Integer getSachID() {
        return SachID;
    }

    public void setSachID(Integer sachID) {
        SachID = sachID;
    }

    public Integer getNhaXuaBanID() {
        return NhaXuaBanID;
    }

    public void setNhaXuaBanID(Integer nhaXuaBanID) {
        NhaXuaBanID = nhaXuaBanID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public Integer getTacgiaID() {
        return TacGiaID;
    }

    public void setTacgiaID(Integer tacgiaID) {
        TacGiaID = tacgiaID;
    }

    public Integer getTheLoaiID() {
        return TheLoaiID;
    }

    public void setTheLoaiID(Integer theLoaiID) {
        TheLoaiID = theLoaiID;
    }

    public double getSao() {
        return Sao;
    }

    public void setSao(double sao) {
        Sao = sao;
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

    public double getSale() {
        return Sale;
    }

    public void setSale(double sale) {
        Sale = sale;
    }

    public Integer getDiemThuong() {
        return DiemThuong;
    }

    public void setDiemThuong(Integer diemThuong) {
        DiemThuong = diemThuong;
    }

    public String getNgayKhoiBan() {
        return NgayKhoiBan;
    }

    public void setNgayKhoiBan(String ngayKhoiBan) {
        NgayKhoiBan = ngayKhoiBan;
    }

    public String getNgayXuatBan() {
        return NgayXuatBan;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        NgayXuatBan = ngayXuatBan;
    }
}
