package com.example.de_thi_on_tap.model;

public class Taxi_MaDe {
    private int Ma;
    private String SoXe;
    private double QuangDuong;
    private double DonGia;
    private int PhanTramKhuyenMai;

    public double GetTongTien() {
        return DonGia * QuangDuong * (100 - PhanTramKhuyenMai) / 100;
    }
    public Taxi_MaDe(int ma, String soXe, double quangDuong, double donGia, int phanTramKhuyenMai) {
        Ma = ma;
        SoXe = soXe;
        QuangDuong = quangDuong;
        DonGia = donGia;
        PhanTramKhuyenMai = phanTramKhuyenMai;
    }

    public Taxi_MaDe(String soXe, double quangDuong, double donGia, int phanTramKhuyenMai) {
        SoXe = soXe;
        QuangDuong = quangDuong;
        DonGia = donGia;
        PhanTramKhuyenMai = phanTramKhuyenMai;
    }

    public Taxi_MaDe() {
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public String getSoXe() {
        return SoXe;
    }

    public void setSoXe(String soXe) {
        SoXe = soXe;
    }

    public double getQuangDuong() {
        return QuangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        QuangDuong = quangDuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public int getPhanTramKhuyenMai() {
        return PhanTramKhuyenMai;
    }

    public void setPhanTramKhuyenMai(int phanTramKhuyenMai) {
        PhanTramKhuyenMai = phanTramKhuyenMai;
    }
}
