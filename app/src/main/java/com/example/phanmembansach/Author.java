package com.example.phanmembansach;

public class Author {
    private  String Ten;
    private  String CongViec;
    private  Integer TacGiaID;
    private  String MoTa;
    private  String Anh;

    public Author() {
    }

    public Author(String ten, String congViec, Integer tacGiaID, String moTa, String anh) {
        Ten = ten;
        CongViec = congViec;
        TacGiaID = tacGiaID;
        MoTa = moTa;
        Anh = anh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getCongViec() {
        return CongViec;
    }

    public void setCongViec(String congViec) {
        CongViec = congViec;
    }

    public Integer getTacGiaID() {
        return TacGiaID;
    }

    public void setTacGiaID(Integer tacGiaID) {
        TacGiaID = tacGiaID;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }
}
