package com.msc.mubeen.school_base.MODEL;

public class Employee  {

    String id;
    String name;
    String subject;
    String degree;
    String cnic;
    String number;
    String stutus;
    String adddress;
    String email;
    String password;


    public Employee(String id, String name, String subject, String degree, String cnic, String number, String stutus, String adddress, String email, String password) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.degree = degree;
        this.cnic = cnic;
        this.number = number;
        this.stutus = stutus;
        this.adddress = adddress;
        this.email = email;
        this.password = password;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStutus() {
        return stutus;
    }

    public void setStutus(String stutus) {
        this.stutus = stutus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

