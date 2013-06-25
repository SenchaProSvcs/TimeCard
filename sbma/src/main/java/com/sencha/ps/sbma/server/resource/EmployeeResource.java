package com.sencha.ps.sbma.server.resource;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sencha.ps.sbma.server.dao.SbmaDao;
import com.sencha.ps.sbma.server.dto.EmployeeDto;
import com.sencha.ps.sbma.server.entity.EmployeeEntity;
import com.sencha.ps.sbma.server.util.Log;

@Path("/employee")
public class EmployeeResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EmployeeDto> get(@QueryParam("at") String authenticationToken) {
    System.out.println("EmployeeResource.post: authenticationToken=" + authenticationToken);
    SbmaDao sbmaDao = new SbmaDao();
    EmployeeEntity authenticatedEmployeeEntity = sbmaDao.findEmployeeEntityByAuthenticationToken(authenticationToken);
    if (!authenticatedEmployeeEntity.isManager()) {
      throw new IllegalArgumentException("Employee is not a manager");
    }
    List<EmployeeDto> employeeDtoList = new LinkedList<EmployeeDto>();
    List<EmployeeEntity> employeeEntityList = sbmaDao.findAllEmployees();
    for (EmployeeEntity employeeEntity : employeeEntityList) {
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setId(employeeEntity.getId());
      employeeDto.setUserName(employeeEntity.getUserName());
      employeeDto.setCourtesyTitle(employeeEntity.getCourtesyTitle());
      employeeDto.setFirstName(employeeEntity.getFirstName());
      employeeDto.setLastName(employeeEntity.getLastName());
      employeeDto.setAddressLine1(employeeEntity.getAddressLine1());
      employeeDto.setAddressLine2(employeeEntity.getAddressLine2());
      employeeDto.setAddressLine3(employeeEntity.getAddressLine3());
      employeeDto.setCity(employeeEntity.getCity());
      employeeDto.setStateOrRegion(employeeEntity.getStateOrRegion());
      employeeDto.setIsoCountryCode(employeeEntity.getIsoCountryCode());
      employeeDto.setPostCode(employeeEntity.getPostCode());
      employeeDto.setDateOfBirth(employeeEntity.getDateOfBirth());
      employeeDto.setGender(employeeEntity.getGender());
      employeeDto.setPositionCode(employeeEntity.getPositionCode());
      employeeDto.setWageAmount(employeeEntity.getWageAmount());
      employeeDto.setWageInterval(employeeEntity.getWageInterval());
      employeeDto.setExempt(employeeEntity.isExempt());
      employeeDto.setManager(employeeEntity.isManager());
      employeeDto.setAddressLine1(employeeEntity.getAddressLine1());
      employeeDto.setAddressLine1(employeeEntity.getAddressLine1());
      employeeDtoList.add(employeeDto);
    }
    return employeeDtoList;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public EmployeeDto post(@QueryParam("at") String authenticationToken, EmployeeDto employeeDto) {
    Log.log("EmployeeResource.post: authenticationToken=" + authenticationToken + ", employeeDto=" + employeeDto);
    SbmaDao sbmaDao = new SbmaDao();
    EmployeeEntity employeeEntity = new EmployeeEntity();
    employeeEntity.setUserName(employeeDto.getUserName());
    employeeEntity.setCourtesyTitle(employeeDto.getCourtesyTitle());
    employeeEntity.setFirstName(employeeDto.getFirstName());
    employeeEntity.setLastName(employeeDto.getLastName());
    employeeEntity.setAddressLine1(employeeDto.getAddressLine1());
    employeeEntity.setAddressLine2(employeeDto.getAddressLine2());
    employeeEntity.setAddressLine3(employeeDto.getAddressLine3());
    employeeEntity.setCity(employeeDto.getCity());
    employeeEntity.setStateOrRegion(employeeDto.getStateOrRegion());
    employeeEntity.setIsoCountryCode(employeeDto.getIsoCountryCode());
    employeeEntity.setPostCode(employeeDto.getPostCode());
    employeeEntity.setDateOfBirth(employeeDto.getDateOfBirth());
    employeeEntity.setGender(employeeDto.getGender());
    employeeEntity.setPositionCode(employeeDto.getPositionCode());
    employeeEntity.setWageAmount(employeeDto.getWageAmount());
    employeeEntity.setWageInterval(employeeDto.getWageInterval());
    employeeEntity.setExempt(employeeDto.isExempt());
    employeeEntity.setManager(employeeDto.isManager());
    employeeEntity.setAddressLine1(employeeDto.getAddressLine1());
    employeeEntity.setAddressLine1(employeeDto.getAddressLine1());
    if (employeeDto.getId() == -1) {
      sbmaDao.createEmployeeEntity(employeeEntity);
      employeeDto.setId(employeeEntity.getId());
    } else {
      employeeEntity.setId(employeeDto.getId());
      sbmaDao.updateEmployeeEntity(employeeEntity);
    }
    return employeeDto;
  }

}
