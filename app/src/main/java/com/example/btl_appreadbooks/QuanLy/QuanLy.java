package com.example.btl_appreadbooks.QuanLy;

import java.io.Serializable;

public class QuanLy implements Serializable {
    private int id;
    private byte[] hinh;
    private String title;

    public QuanLy(int id, byte[] hinh, String title) {
        this.id = id;
        this.hinh = hinh;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
