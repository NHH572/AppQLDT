/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class NhanVien {
    private String maNV;
    private String matKhau;
    private String hoten;
    private boolean vaitro; 

    public NhanVien() {
    }

    public NhanVien(String maNV, String matKhau, String hoten, boolean vaitro) {
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.hoten = hoten;
        this.vaitro = vaitro;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
    }
}
