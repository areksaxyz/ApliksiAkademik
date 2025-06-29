package com.yourcompany.akademik.model;

public class Nilai {
    private int id; 
    private String nim; 
    private String kodeMk; 
    private String absensi;
    private double quiz;
    private double uts;
    private double uas;
    private String grade;

    public Nilai(int id, String nim, String kodeMk, String absensi, double quiz, double uts, double uas, String grade) {
        this.id = id;
        this.nim = nim;
        this.kodeMk = kodeMk;
        this.absensi = absensi;
        this.quiz = quiz;
        this.uts = uts;
        this.uas = uas;
        this.grade = grade;
    }

    // Getters
    public int getId() { return id; }
    public String getNim() { return nim; }
    public String getKodeMk() { return kodeMk; }
    public String getAbsensi() { return absensi; }
    public double getQuiz() { return quiz; }
    public double getUts() { return uts; }
    public double getUas() { return uas; }
    public String getGrade() { return grade; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNim(String nim) { this.nim = nim; }
    public void setKodeMk(String kodeMk) { this.kodeMk = kodeMk; }
    public void setAbsensi(String absensi) { this.absensi = absensi; }
    public void setQuiz(double quiz) { this.quiz = quiz; }
    public void setUts(double uts) { this.uts = uts; }
    public void setUas(double uas) { this.uas = uas; }
    public void setGrade(String grade) { this.grade = grade; }
}