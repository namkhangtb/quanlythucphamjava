/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author LENOVO
 */
public class HoaDon {
    int mahoadon;
    int manhanvien;
    int makhachhang;
    String ngaylap;
    String tongtien;

    public HoaDon() {
    }

    public HoaDon(int mahoadon, int manhanvien, int makhachhang, String ngaylap, String tongtien) {
        this.mahoadon = mahoadon;
        this.manhanvien = manhanvien;
        this.makhachhang = makhachhang;
        this.ngaylap = ngaylap;
        this.tongtien = tongtien;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    
    
}
