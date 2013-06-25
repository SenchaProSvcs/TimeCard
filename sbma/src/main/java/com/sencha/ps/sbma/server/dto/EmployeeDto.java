package com.sencha.ps.sbma.server.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeDto {

  private long id;
  private String userName;
  private String courtesyTitle;
  private String firstName;
  private String lastName;
  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private String city;
  private String stateOrRegion;
  private String isoCountryCode;
  private String postCode;
  private Date dateOfBirth;
  private char gender;
  private int positionCode;
  private BigDecimal wageAmount;
  private char wageInterval;
  private boolean isExempt;
  private boolean isManager;

  public EmployeeDto() {
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public String getCity() {
    return city;
  }

  public String getCourtesyTitle() {
    return courtesyTitle;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getFirstName() {
    return firstName;
  }

  public char getGender() {
    return gender;
  }

  public long getId() {
    return id;
  }

  public String getIsoCountryCode() {
    return isoCountryCode;
  }

  public String getLastName() {
    return lastName;
  }

  public int getPositionCode() {
    return positionCode;
  }

  public String getPostCode() {
    return postCode;
  }

  public String getStateOrRegion() {
    return stateOrRegion;
  }

  public String getUserName() {
    return userName;
  }

  public BigDecimal getWageAmount() {
    return wageAmount;
  }

  public char getWageInterval() {
    return wageInterval;
  }

  public boolean isExempt() {
    return isExempt;
  }

  public boolean isManager() {
    return isManager;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCourtesyTitle(String courtesyTitle) {
    this.courtesyTitle = courtesyTitle;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setExempt(boolean isExempt) {
    this.isExempt = isExempt;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setIsoCountryCode(String isoCountryCode) {
    this.isoCountryCode = isoCountryCode;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setManager(boolean isManager) {
    this.isManager = isManager;
  }

  public void setPositionCode(int positionCode) {
    this.positionCode = positionCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public void setStateOrRegion(String stateOrRegion) {
    this.stateOrRegion = stateOrRegion;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setWageAmount(BigDecimal wageAmount) {
    this.wageAmount = wageAmount;
  }

  public void setWageInterval(char wageInterval) {
    this.wageInterval = wageInterval;
  }

  @Override
  public String toString() {
    return "EmployeeDto [id=" + id + ", userName=" + userName + ", courtesyTitle=" + courtesyTitle + ", firstName=" + firstName + ", lastName=" + lastName + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", city=" + city + ", stateOrRegion=" + stateOrRegion + ", isoCountryCode=" + isoCountryCode + ", postCode=" + postCode + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", positionCode=" + positionCode + ", wageAmount=" + wageAmount + ", wageInterval=" + wageInterval + ", isExempt=" + isExempt + ", isManager=" + isManager + "]";
  }

}
