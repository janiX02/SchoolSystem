package com.janikcrew.szkola.dto;

public class StudentParentDTO {
    private String imieRodzica;
    private String nazwiskoRodzica;
    private String peselRodzica;
    private String dataUrodzeniaRodzica;
    private String emailRodzica;
    private String hasloRodzica;
    private String imieUcznia;
    private String nazwiskoUcznia;
    private String peselUcznia;
    private String dataUrodzeniaUcznia;
    private String emailUcznia;
    private String hasloUcznia;

    private boolean hasEmptyFields;

    private boolean hasInvalidEmail;

    private boolean sameEmails;

    private boolean isCorrectPesel;

    private boolean isCorrectDate;

    private boolean samePesel;

    private boolean goodPassword;

    private boolean correctName;

    public StudentParentDTO() {

    }

    public String getImieRodzica() {
        return imieRodzica;
    }

    public void setImieRodzica(String imieRodzica) {
        this.imieRodzica = imieRodzica;
    }

    public String getNazwiskoRodzica() {
        return nazwiskoRodzica;
    }

    public void setNazwiskoRodzica(String nazwiskoRodzica) {
        this.nazwiskoRodzica = nazwiskoRodzica;
    }

    public String getPeselRodzica() {
        return peselRodzica;
    }

    public void setPeselRodzica(String peselRodzica) {
        this.peselRodzica = peselRodzica;
    }

    public String getDataUrodzeniaRodzica() {
        return dataUrodzeniaRodzica;
    }

    public void setDataUrodzeniaRodzica(String dataUrodzeniaRodzica) {
        this.dataUrodzeniaRodzica = dataUrodzeniaRodzica;
    }

    public String getEmailRodzica() {
        return emailRodzica;
    }

    public void setEmailRodzica(String emailRodzica) {
        this.emailRodzica = emailRodzica;
    }

    public String getHasloRodzica() {
        return hasloRodzica;
    }

    public void setHasloRodzica(String hasloRodzica) {
        this.hasloRodzica = hasloRodzica;
    }

    public String getImieUcznia() {
        return imieUcznia;
    }

    public void setImieUcznia(String imieUcznia) {
        this.imieUcznia = imieUcznia;
    }

    public String getNazwiskoUcznia() {
        return nazwiskoUcznia;
    }

    public void setNazwiskoUcznia(String nazwiskoUcznia) {
        this.nazwiskoUcznia = nazwiskoUcznia;
    }

    public String getPeselUcznia() {
        return peselUcznia;
    }

    public void setPeselUcznia(String peselUcznia) {
        this.peselUcznia = peselUcznia;
    }

    public String getDataUrodzeniaUcznia() {
        return dataUrodzeniaUcznia;
    }

    public void setDataUrodzeniaUcznia(String dataUrodzeniaUcznia) {
        this.dataUrodzeniaUcznia = dataUrodzeniaUcznia;
    }

    public String getEmailUcznia() {
        return emailUcznia;
    }

    public void setEmailUcznia(String emailUcznia) {
        this.emailUcznia = emailUcznia;
    }

    public String getHasloUcznia() {
        return hasloUcznia;
    }

    public void setHasloUcznia(String hasloUcznia) {
        this.hasloUcznia = hasloUcznia;
    }

    public boolean isHasEmptyFields() {
        return hasEmptyFields;
    }

    public void setHasEmptyFields(boolean hasEmptyFields) {
        this.hasEmptyFields = hasEmptyFields;
    }

    public boolean isHasInvalidEmail() {
        return hasInvalidEmail;
    }

    public void setHasInvalidEmail(boolean hasInvalidEmail) {
        this.hasInvalidEmail = hasInvalidEmail;
    }

    public boolean isSameEmails() {
        return sameEmails;
    }

    public void setSameEmails(boolean sameEmails) {
        this.sameEmails = sameEmails;
    }

    public boolean isCorrectPesel() {
        return isCorrectPesel;
    }

    public void setCorrectPesel(boolean correctPesel) {
        isCorrectPesel = correctPesel;
    }

    public boolean isCorrectDate() {
        return isCorrectDate;
    }

    public void setCorrectDate(boolean correctDate) {
        isCorrectDate = correctDate;
    }

    public boolean isSamePesel() {
        return samePesel;
    }

    public void setSamePesel(boolean samePesel) {
        this.samePesel = samePesel;
    }

    public boolean isGoodPassword() {
        return goodPassword;
    }

    public void setGoodPassword(boolean goodPassword) {
        this.goodPassword = goodPassword;
    }

    public boolean isCorrectName() {
        return correctName;
    }

    public void setCorrectName(boolean correctName) {
        this.correctName = correctName;
    }
}
