package com.sencha.ps.sbma.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credential")
public class CredentialEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 32, unique = true)
  private String authenticationToken;

  public String getAuthenticationToken() {
    return authenticationToken;
  }

  public Long getId() {
    return id;
  }

  public void setAuthenticationToken(String authenticationToken) {
    this.authenticationToken = authenticationToken;
  }

  @Override
  public String toString() {
    return "CredentialEntity [id=" + id + ", authenticationToken=" + authenticationToken + "]";
  }

}
