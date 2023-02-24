/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmjava2.vovandai.model;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class NhanVien implements Serializable{
    private String maNV;
    private String tenNV;
    private Double luongNV;
    private int tuoiNV;
    private String emailNV;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, Double luongNV, int tuoiNV, String emailNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.luongNV = luongNV;
        this.tuoiNV = tuoiNV;
        this.emailNV = emailNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Double getLuongNV() {
        return luongNV;
    }

    public void setLuongNV(Double luongNV) {
        this.luongNV = luongNV;
    }

    public int getTuoiNV() {
        return tuoiNV;
    }

    public void setTuoiNV(int tuoiNV) {
        this.tuoiNV = tuoiNV;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    
    
}
