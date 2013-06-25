package com.sencha.ps.sbma.server.dto;

public class CredentialResponseDto {

  private String authenticationToken;
  private boolean isManager;

  public CredentialResponseDto() {
  }

  public String getAuthenticationToken() {
    return authenticationToken;
  }

  public boolean getIsManager() {
    return isManager;
  }

  public void setAuthenticationToken(String authenticationToken) {
    this.authenticationToken = authenticationToken;
  }

  public void setIsManager(boolean isManager) {
    this.isManager = isManager;
  }

  @Override
  public String toString() {
    return "CredentialResponseDto [authenticationToken=" + authenticationToken + "]";
  }

}
