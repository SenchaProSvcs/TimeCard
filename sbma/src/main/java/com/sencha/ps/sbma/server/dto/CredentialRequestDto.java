package com.sencha.ps.sbma.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties({"id"})
public class CredentialRequestDto {
  
  private String userName;
  private String password;

  public CredentialRequestDto() {
  }
  
  public CredentialRequestDto(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "CredentialRequestDto [userName=" + userName + ", password=" + password + "]";
  }

}
