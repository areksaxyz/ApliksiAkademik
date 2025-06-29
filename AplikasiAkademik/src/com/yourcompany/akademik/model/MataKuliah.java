package com.yourcompany.akademik.model;

public class MataKuliah {
    private String kodeMk;
    private String namaMk;
    private int sks;
    private String dosenPengampu;
    private String jadwal;
    private String ruangan;

    public MataKuliah(String kodeMk, String namaMk, int sks, String dosenPengampu, String jadwal, String ruangan) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.sks = sks;
        this.dosenPengampu = dosenPengampu;
        this.jadwal = jadwal;
        this.ruangan = ruangan;
    }

    // Getters
    public String getKodeMk() { return kodeMk; }
    public String getNamaMk() { return namaMk; }
    public int getSks() { return sks; }
    public String getDosenPengampu() { return dosenPengampu; }
    public String getJadwal() { return jadwal; }
    public String getRuangan() { return ruangan; }

    // Setters
    public void setKodeMk(String kodeMk) { this.kodeMk = kodeMk; }
    public void setNamaMk(String namaMk) { this.namaMk = namaMk; }
    public void setSks(int sks) { this.sks = sks; }
    public void setDosenPengampu(String dosenPengampu) { this.dosenPengampu = dosenPengampu; }
    public void setJadwal(String jadwal) { this.jadwal = jadwal; }
    public void setRuangan(String ruangan) { this.ruangan = ruangan; }
}