package com.example.btl_appreadbooks.TheLoai;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private int matheloai;
    private String tentheloai;
    private byte[] maanh;

    public TheLoai(int matheloai, String tentheloai, byte[] maanh) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.maanh = maanh;
    }

    public int getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(int matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public byte[] getMaanh() {
        return maanh;
    }

    public void setMaanh(byte[] maanh) {
        this.maanh = maanh;
    }
}
