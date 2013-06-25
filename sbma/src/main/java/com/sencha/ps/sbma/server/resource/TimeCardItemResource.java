package com.sencha.ps.sbma.server.resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sencha.ps.sbma.server.dao.SbmaDao;
import com.sencha.ps.sbma.server.dto.TimeCardItemDto;
import com.sencha.ps.sbma.server.entity.EmployeeEntity;
import com.sencha.ps.sbma.server.entity.TimeCardItemEntity;
import com.sencha.ps.sbma.server.util.Log;

@Path("/timeCardItem")
public class TimeCardItemResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<TimeCardItemDto> get(@QueryParam("at") String authenticationToken, @QueryParam("employeeId") String employeeId) {
    System.out.println("TimeCardItemResource.post: authenticationToken=" + authenticationToken + ", employeeId=" + employeeId);
    SbmaDao sbmaDao = new SbmaDao();
    EmployeeEntity employeeEntity = sbmaDao.findEmployeeEntityByAuthenticationToken(authenticationToken);
    if (employeeEntity.isManager() && employeeId != null) {
      employeeEntity = sbmaDao.findEmployeeEntityById(employeeId);
    }
    List<TimeCardItemDto> timeCardItemDtoList = new LinkedList<TimeCardItemDto>();
    Set<TimeCardItemEntity> timeCardEntitySet = employeeEntity.getTimeCardItemEntitySet();
    for (TimeCardItemEntity timeCardItemEntity : timeCardEntitySet) {
      TimeCardItemDto timeCardItemDto = new TimeCardItemDto();
      timeCardItemDto.setId(timeCardItemEntity.getId());
      timeCardItemDto.setType(timeCardItemEntity.getType());
      timeCardItemDto.setBeginDate(timeCardItemEntity.getBeginDate());
      timeCardItemDto.setEndDate(timeCardItemEntity.getEndDate());
      timeCardItemDto.setNotes(timeCardItemEntity.getNotes());
      timeCardItemDtoList.add(timeCardItemDto);
    }
    return timeCardItemDtoList;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TimeCardItemDto post(@QueryParam("at") String authenticationToken, TimeCardItemDto timeCardItemDto) {
    Log.log("TimeCardItemResource.post: authenticationToken=" + authenticationToken + ", timeCardItemDto=" + timeCardItemDto);
    SbmaDao sbmaDao = new SbmaDao();
    TimeCardItemEntity timeCardItemEntity = new TimeCardItemEntity();
    timeCardItemEntity.setType(timeCardItemDto.getType());
    timeCardItemEntity.setBeginDate(timeCardItemDto.getBeginDate());
    timeCardItemEntity.setEndDate(timeCardItemDto.getEndDate());
    timeCardItemEntity.setLatitude(timeCardItemDto.getLatitude());
    timeCardItemEntity.setLongitude(timeCardItemDto.getLongitude());
    timeCardItemEntity.setNotes(timeCardItemDto.getNotes());
    if (timeCardItemDto.getId() == -1) {
      sbmaDao.createTimeCardItemEntity(authenticationToken, timeCardItemEntity);
      timeCardItemDto.setId(timeCardItemEntity.getId());
    } else {
      timeCardItemEntity.setId(timeCardItemDto.getId());
      sbmaDao.updateTimeCardItemEntity(authenticationToken, timeCardItemEntity);
    }
    return timeCardItemDto;
  }

  //  @POST
  //  //@Consumes(MediaType.APPLICATION_JSON)
  //  public void post(@QueryParam("at") String authenticationToken, String timeCardItemDto) {
  //    Log.log("TimeCardItemResource.post: authenticationToken=" + authenticationToken + ", timeCardItemDto=" + timeCardItemDto);
  //  }

}
