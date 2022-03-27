package com.example.btl_appreadbooks.books;

import java.io.Serializable;

public class Books implements Serializable {
    private int resourceId;
    private int masach;
    private String title;
    private String chitiet;
    private String tacgia;

    public Books(int resourceId, int masach, String title, String chitiet, String tacgia) {
        this.resourceId = resourceId;
        this.masach = masach;
        this.title = title;
        this.chitiet = chitiet;
        this.tacgia = tacgia;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public Books() {

    }
}
