package com.example.btl_appreadbooks.search;

import java.io.Serializable;

public class Search implements Serializable {
    private int idsach;
    private boolean status;
    private String titleSach;

    public Search(int idsach, boolean status, String titleSach) {
        this.idsach = idsach;
        this.status = status;
        this.titleSach = titleSach;
    }

    public int getIdsach() {
        return idsach;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitleSach() {
        return titleSach;
    }

    public void setTitleSach(String titleSach) {
        this.titleSach = titleSach;
    }

    public Search(){

    }
}
