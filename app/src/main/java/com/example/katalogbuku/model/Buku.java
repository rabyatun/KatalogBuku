package com.example.katalogbuku.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Buku {
    public static final String KOMIK="KOMIK";
    public static final String NOVEL="NOVEL";
    private String kode;
    private Date tanggal;
    private String judul;
    private String pengarang;
    private String jenis;
    private String tahunterbit;

    public Buku() {
        this.kode = UUID.randomUUID().toString();
        this.tanggal = new Date();
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudulbuku() {
        return judul;
    }

    public void setJudulbuku(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTahunterbit(){
        return tahunterbit;
    }
    public void setTahunterbit( String tahunterbit){
        this.tahunterbit = tahunterbit;
    }

    public static Buku fromJSONObject(JSONObject obj) {
        Buku tr = new Buku();
        try {
            tr.setKode(obj.getString("kode"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setPengarang(obj.getString("pengarang"));
            tr.setJudulbuku(obj.getString("judul buku"));
            tr.setJenis(obj.getString("jenis"));
            tr.setTahunterbit(obj.getString("tahun terbit"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("kode",this.kode);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("judul buku",this.judul);
            jsonObj.put("pengarang",this.pengarang);
            jsonObj.put("tahun terbit",this.tahunterbit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
