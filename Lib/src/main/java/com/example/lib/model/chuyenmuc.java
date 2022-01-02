package com.example.lib.model;

public class chuyenmuc {
    public String tenchuyenmuc;
    public  int hinhanhchuyenmuc;

    public chuyenmuc(String tenchuyenmuc, int hinhanhchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
        this.hinhanhchuyenmuc = hinhanhchuyenmuc;
    }

    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    public int getHinhanhchuyenmuc() {
        return hinhanhchuyenmuc;
    }

    public void setHinhanhchuyenmuc(int hinhanhchuyenmuc) {
        this.hinhanhchuyenmuc = hinhanhchuyenmuc;
    }
}
