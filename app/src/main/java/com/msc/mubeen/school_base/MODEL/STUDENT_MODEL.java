package com.msc.mubeen.school_base.MODEL;

public class STUDENT_MODEL {


    private String teacher_id;
    private String student_id;
    private String name;
    private String address;
    private String class_name;
    private String number;

    public STUDENT_MODEL(String teacher_id, String student_id, String name, String address, String class_name, String number) {
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.class_name = class_name;
        this.number = number;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

