package com.sencha.ps.sbma.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timecarditem")
public class TimeCardItemEntity {

  @Id
  @GeneratedValue
  private Long id;

  private int type;

  private Date beginDate;

  private Date endDate;

  private Double latitude;

  private Double longitude;

  private String notes;

  public TimeCardItemEntity() {
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Long getId() {
    return id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public String getNotes() {
    return notes;
  }

  public int getType() {
    return type;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "TimeCardItemEntity [id=" + id + ", type=" + type + ", beginDate=" + beginDate + ", endDate=" + endDate + ", latitude=" + latitude + ", longitude=" + longitude + ", notes=" + notes + "]";
  }

}
