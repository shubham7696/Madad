package com.shubham.madad;

/**
 * Created by Raman on 22-03-2017.
 */

class User {

   public String spinner1;
    public String spinner2;
    public String description;

    public User(String spinner1, String spinner2, String description) {
        this.spinner1 = spinner1;
        this.spinner2 = spinner2;
        this.description = description;
    }

    public User(String valueOf, String s) {
    }

    public String getSpinner1() {
        return spinner1;
    }

    public void setSpinner1(String spinner1) {
        this.spinner1 = spinner1;
    }

    public String getSpinner2() {
        return spinner2;
    }

    public void setSpinner2(String spinner2) {
        this.spinner2 = spinner2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
