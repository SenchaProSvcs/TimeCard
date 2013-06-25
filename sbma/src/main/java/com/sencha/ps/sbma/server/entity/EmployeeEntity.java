package com.sencha.ps.sbma.server.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Remove authenticationToken foreign key and replace by foreign key from credential entity.

@Entity
@Table(name = "employee")
@NamedQueries( //
{@NamedQuery(name = "EmployeeEntity.findAll", query = "select e from EmployeeEntity e"), //
  @NamedQuery(name = "EmployeeEntity.findByUserName", query = "select e from EmployeeEntity e where e.userName = :userName"), //
  @NamedQuery(name = "EmployeeEntity.findById", query = "select e from EmployeeEntity e where e.id = :id"), //
    @NamedQuery(name = "EmployeeEntity.findByAuthenticationToken", query = "select e from EmployeeEntity e where e.credentialEntity.authenticationToken = :authenticationToken")})
//@NamedQuery(name = "EmployeeEntity.findByUserName", query = "select e from EmployeeEntity e where e.userName = :userName")
public class EmployeeEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 32, unique = true)
  private String userName;

  @Column(length = 32, unique = true)
  private String password;

  @Column(length = 16, nullable = true)
  private String courtesyTitle;

  @Column(length = 32, nullable = false)
  private String firstName;

  @Column(length = 32, nullable = false)
  private String lastName;

  @Column(length = 128, nullable = false)
  private String addressLine1;

  @Column(length = 128, nullable = true)
  private String addressLine2;

  @Column(length = 128, nullable = true)
  private String addressLine3;

  @Column(length = 64, nullable = false)
  private String city;

  @Column(length = 64, nullable = false)
  private String stateOrRegion;

  @Column(length = 2, nullable = false)
  private String isoCountryCode;

  @Column(length = 16, nullable = false)
  private String postCode;

  @Column(nullable = false)
  private Date dateOfBirth;

  /**
   * <dl>
   * <dt>M</dt>
   * <dd>Male</dd>
   * <dt>F</dt>
   * <dd>Female</dd>
   * </dl>
   */
  private char gender;

  private int positionCode;

  @Column(precision = 10, scale = 2)
  private BigDecimal wageAmount;

  /**
   * <dl>
   * <dt>H</dt>
   * <dd>Hourly</dd>
   * <dt>B</dt>
   * <dd>Bimonthly</dd>
   * <dt>M</dt>
   * <dd>Monthly</dd>
   * <dt>Y</dt>
   * <dd>Yearly</dd>
   * </dl>
   */
  private char wageInterval;

  private boolean isExempt;

  private boolean isManager;

  @OneToOne
  private CredentialEntity credentialEntity;

  @OneToMany
  private Set<TimeCardItemEntity> timeCardItemEntitySet;

  @OneToMany
  private Set<EmployeeEntity> directReports;

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

  public CredentialEntity getCredentialEntity() {
    return credentialEntity;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public Set<EmployeeEntity> getDirectReports() {
    return directReports;
  }

  public String getFirstName() {
    return firstName;
  }

  public char getGender() {
    return gender;
  }

  public Long getId() {
    return id;
  }

  public String getIsoCountryCode() {
    return isoCountryCode;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPassword() {
    return password;
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

  public Set<TimeCardItemEntity> getTimeCardItemEntitySet() {
    return timeCardItemEntitySet;
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

  public void setCredentialEntity(CredentialEntity credentialEntity) {
    this.credentialEntity = credentialEntity;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setDirectReports(Set<EmployeeEntity> directReports) {
    this.directReports = directReports;
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

  public void setId(Long id) {
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

  public void setPassword(String password) {
    this.password = password;
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

  public void setTimeCardItemEntitySet(Set<TimeCardItemEntity> timeCardItemEntities) {
    this.timeCardItemEntitySet = timeCardItemEntities;
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
    return "EmployeeEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", courtesyTitle=" + courtesyTitle + ", firstName=" + firstName + ", lastName=" + lastName + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", city=" + city + ", stateOrRegion=" + stateOrRegion + ", isoCountryCode=" + isoCountryCode + ", postCode=" + postCode + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", positionCode=" + positionCode + ", wageAmount=" + wageAmount + ", wageInterval=" + wageInterval + ", isExempt=" + isExempt + ", isManager=" + isManager + ", credentialEntity=" + credentialEntity + ", timeCardItemEntitySet=" + timeCardItemEntitySet + ", directReports=" + directReports + "]";
  }

}
