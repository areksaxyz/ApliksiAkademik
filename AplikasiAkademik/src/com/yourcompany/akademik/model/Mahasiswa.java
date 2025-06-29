package com.yourcompany.akademik.model;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String kelas;
    private String jurusan;
    private int semester;

    public Mahasiswa(String nim, String nama, String kelas, String jurusan, int semester) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.semester = semester;
    }

    // Getters
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getKelas() { return kelas; }
    public String getJurusan() { return jurusan; }
    public int getSemester() { return semester; }

    // Setters (optional, but good for DTOs)
    public void setNim(String nim) { this.nim = nim; }
    public void setNama(String nama) { this.nama = nama; }
    public void setKelas(String kelas) { this.kelas = kelas; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }
    public void setSemester(int semester) { this.semester = semester; }

    @Override
    public String toString() {
        return nama + " (" + nim + ")";
    }
}