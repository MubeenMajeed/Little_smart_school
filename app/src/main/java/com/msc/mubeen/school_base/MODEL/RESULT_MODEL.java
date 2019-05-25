package com.msc.mubeen.school_base.MODEL;

public class RESULT_MODEL {

    String time;
    String gain;
    String grade;
    String subject;

    public RESULT_MODEL(String time, String gain, String grade, String subject) {
        this.time = time;
        this.gain = gain;
        this.grade = grade;
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

