package com.sencha.ps.sbma.server.resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sencha.ps.sbma.server.dao.SbmaDao;
import com.sencha.ps.sbma.server.dto.CredentialRequestDto;
import com.sencha.ps.sbma.server.dto.CredentialResponseDto;
import com.sencha.ps.sbma.server.entity.CredentialEntity;
import com.sencha.ps.sbma.server.entity.EmployeeEntity;

@Path("/credential")
public class CredentialResource {

  @GET
  public String get() {
    StringBuilder s = new StringBuilder();
    s.append("<h1>CredentialResource Status Report</h1>");
    s.append("<hr/>");
    s.append("<b>Date: </b>" + new Date() + "<br/>");
    return s.toString();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public CredentialResponseDto post(CredentialRequestDto credentialRequestDto) {
    System.out.println("CredentialResource.post: credentialRequestDto=" + credentialRequestDto);
    CredentialResponseDto credentialResponseDto;
    SbmaDao sbmaDao = new SbmaDao();
    EmployeeEntity employeeEntity = sbmaDao.findEmployeeEntityByUserName(credentialRequestDto.getUserName());
    if (employeeEntity == null) {
      credentialResponseDto = new CredentialResponseDto();
    } else {
      if (employeeEntity.getPassword().equals(credentialRequestDto.getPassword())) {
        CredentialEntity credentialEntity = employeeEntity.getCredentialEntity();
        if (credentialEntity == null) {
          credentialEntity = sbmaDao.createCredentialEntity(employeeEntity);
        }
        credentialResponseDto = new CredentialResponseDto();
        credentialResponseDto.setAuthenticationToken(credentialEntity.getAuthenticationToken());
        credentialResponseDto.setIsManager(employeeEntity.isManager());
      } else {
        credentialResponseDto = new CredentialResponseDto();
      }
    }
    return credentialResponseDto;
  }

}
