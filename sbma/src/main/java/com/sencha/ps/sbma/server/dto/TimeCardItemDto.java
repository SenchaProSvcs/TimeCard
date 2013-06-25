package com.sencha.ps.sbma.server.dto;

import java.util.Date;

public class TimeCardItemDto {

  public static final int TYPE_SCHEDULED = 1;
  public static final int TYPE_IN_PROGRESS = 2;
  public static final int TYPE_COMPLETED = 3;
  public static final int TYPE_PTO = 4;

  private long id;
  private int type;
  private Date beginDate;
  private Date endDate;
  private Double latitude;
  private Double longitude;
  private String notes;

  public TimeCardItemDto() {
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public long getId() {
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

  public void setId(long id) {
    this.id = id;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public void setNotes(String distanceReason) {
    this.notes = distanceReason;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "TimeCardItemDto [id=" + id + ", type=" + type + ", beginDate=" + beginDate + ", endDate=" + endDate + ", latitude=" + latitude + ", longitude=" + longitude + ", notes=" + notes + "]";
  }

}
