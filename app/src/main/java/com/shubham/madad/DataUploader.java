package com.shubham.madad;

import android.app.Activity;

/**
 * Created by vikeshy on 21/3/17.
 */

public class DataUploader extends Activity
{

    DataUploader()
    {

    }

    public String getAdharCardNumber() {
        return AdharCardNumber;
    }

    public void setAdharCardNumber(String adharCardNumber) {
        AdharCardNumber = adharCardNumber;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCurrentAdress() {
        return CurrentAdress;
    }

    public void setCurrentAdress(String currentAdress) {
        CurrentAdress = currentAdress;
    }

    public String getCurrentCountry() {
        return CurrentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        CurrentCountry = currentCountry;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        DateofBirth = dateofBirth;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getEmigrantIdNumber() {
        return EmigrantIdNumber;
    }

    public void setEmigrantIdNumber(String emigrantIdNumber) {
        EmigrantIdNumber = emigrantIdNumber;
    }

    public String getFistName() {
        return FistName;
    }

    public void setFistName(String fistName) {
        FistName = fistName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getGrivience() {
        return Grivience;
    }

    public void setGrivience(String grivience) {
        Grivience = grivience;
    }

    public String getIsPersonSameNot() {
        return IsPersonSameNot;
    }

    public void setIsPersonSameNot(String isPersonSameNot) {
        IsPersonSameNot = isPersonSameNot;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobilenumber() {
        return Mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        Mobilenumber = mobilenumber;
    }

    public String getPassportNumber() {
        return PassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        PassportNumber = passportNumber;
    }

    public String getRecruitmentAgentName() {
        return RecruitmentAgentName;
    }

    public void setRecruitmentAgentName(String recruitmentAgentName) {
        RecruitmentAgentName = recruitmentAgentName;
    }

    public DataUploader(String adharCardNumber, String country, String currentAdress, String currentCountry, String dateofBirth, String emailId, String emigrantIdNumber, String fistName, String gender, String grivience, String isPersonSameNot, String lastName, String mobilenumber, String passportNumber, String recruitmentAgentName) {
        AdharCardNumber = adharCardNumber;
        Country = country;
        CurrentAdress = currentAdress;
        CurrentCountry = currentCountry;
        DateofBirth = dateofBirth;
        EmailId = emailId;
        EmigrantIdNumber = emigrantIdNumber;
        FistName = fistName;
        Gender = gender;
        Grivience = grivience;
        IsPersonSameNot = isPersonSameNot;
        LastName = lastName;
        Mobilenumber = mobilenumber;
        PassportNumber = passportNumber;
        RecruitmentAgentName = recruitmentAgentName;
    }

    private   String   AdharCardNumber;
    private   String  Country;
    private   String CurrentAdress;
    private   String CurrentCountry;
    private   String  DateofBirth;
    private   String   EmailId;
    private   String   EmigrantIdNumber;
    private   String   FistName;
    private   String  Gender;
    private   String  Grivience;
    private   String  IsPersonSameNot;
    private   String  LastName;
    private   String  Mobilenumber;
    private   String  PassportNumber;
    private   String  RecruitmentAgentName;



}