package com.example.myapplication.model;

import java.util.Date;

public class MachineDataDetail {

    private String id;
    private String name;
    private String type;
    private int qrnumber;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQrnumber() {
        return qrnumber;
    }

    public void setQrnumber(int qrnumber) {
        this.qrnumber = qrnumber;
    }

    public Date getLastmaintancedate() {
        return lastmaintancedate;
    }

    public void setLastmaintancedate(Date lastmaintancedate) {
        this.lastmaintancedate = lastmaintancedate;
    }

    private Date lastmaintancedate;


}
