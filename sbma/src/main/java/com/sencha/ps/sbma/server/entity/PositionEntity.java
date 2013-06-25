package com.sencha.ps.sbma.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class PositionEntity {

  @Id
  private Long id;

  @Column(length = 64, unique = true)
  private String description;

}
