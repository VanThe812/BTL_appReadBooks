package com.example.btl_appreadbooks.books;

import java.io.Serializable;

public class Books implements Serializable {

    private int masach;
    private String tensach;
    private byte[] hinhanh;

    public Books(int masach, String tensach, byte[] hinhanh) {
        this.masach = masach;
        this.tensach = tensach;
        this.hinhanh = hinhanh;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Books() {

    }
}
