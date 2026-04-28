package com.sania.notification.model;

public class Order {
    private long id;
    private long produkId;
    private long pelangganId;
    private double harga;
    private int jumlah;
    private double total;
    private String tanggal;
    private String email;
    private String username;

    public Order(){
        
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public long getProdukId(){
        return produkId;
    }
    public void setProdukId(long produkId){
        this.produkId = produkId;
    }
    public long getPelangganId(){
        return pelangganId;
    }
    public void setPelangganId(long pelangganId){
        this.pelangganId = pelangganId;
    }
    public double getHarga(){
        return harga;
    }
    public void setHarga(double harga){
        this.harga = harga;
    }
    public int getJumlah(){
        return jumlah;
    }
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    public double getTotal(){
        return total;
    }
    public void setTotal(double total){
        this.total = total;
    }
    public String getTanggal(){
        return tanggal;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

}
