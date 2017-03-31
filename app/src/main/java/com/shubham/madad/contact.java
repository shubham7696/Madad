package com.shubham.madad;

/**
 * Created by SHUBHAM on 3/30/2017.
 */

public class contact extends MainActivity {
    public contact(){}
    public contact(long number, String address, String agent) {
        this.number = number;
        this.address = address;
        this.agent = agent;
    }

    long number;
    String address;
    String agent;
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

}

