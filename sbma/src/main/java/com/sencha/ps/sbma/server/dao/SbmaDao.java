package com.sencha.ps.sbma.server.dao;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.sencha.ps.sbma.server.entity.CredentialEntity;
import com.sencha.ps.sbma.server.entity.EmployeeEntity;
import com.sencha.ps.sbma.server.entity.TimeCardItemEntity;

public class SbmaDao {

  private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SbmaPU");

  private EntityManager entityManager = entityManagerFactory.createEntityManager();

  public CredentialEntity createCredentialEntity(EmployeeEntity employeeEntity) {

    if (employeeEntity.getCredentialEntity() != null) {
      throw new IllegalStateException("employeeEntity already has a credentialEntity");
    }

    entityManager.getTransaction().begin();

    CredentialEntity credentialEntity = new CredentialEntity();
    String authenticationToken = createAuthenticationToken();
    credentialEntity.setAuthenticationToken(authenticationToken);
    employeeEntity.setCredentialEntity(credentialEntity);

    entityManager.persist(credentialEntity);
    entityManager.persist(employeeEntity);
    entityManager.getTransaction().commit();
    entityManager.close();

    return credentialEntity;
  }

  public void createEmployeeEntity(EmployeeEntity employeeEntity) {
    entityManager.getTransaction().begin();
    entityManager.persist(employeeEntity);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void createTimeCardItemEntity(String authenticationToken, TimeCardItemEntity timeCardItemEntity) {
    EmployeeEntity employeeEntity = findEmployeeEntityByAuthenticationToken(authenticationToken);
    entityManager.getTransaction().begin();
    entityManager.persist(timeCardItemEntity);
    employeeEntity.getTimeCardItemEntitySet().add(timeCardItemEntity);
    entityManager.persist(employeeEntity);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public List<EmployeeEntity> findAllEmployees() {
    TypedQuery<EmployeeEntity> namedQuery = entityManager.createNamedQuery("EmployeeEntity.findAll", EmployeeEntity.class);
    List<EmployeeEntity> employeeEntityList = namedQuery.getResultList();
    return employeeEntityList;
  }

  public EmployeeEntity findEmployeeEntityByAuthenticationToken(String authenticationToken) {
    TypedQuery<EmployeeEntity> namedQuery = entityManager.createNamedQuery("EmployeeEntity.findByAuthenticationToken", EmployeeEntity.class);
    namedQuery.setParameter("authenticationToken", authenticationToken);
    EmployeeEntity employeeEntity = namedQuery.getSingleResult();
    return employeeEntity;
  }

  public EmployeeEntity findEmployeeEntityById(String id) {
    TypedQuery<EmployeeEntity> namedQuery = entityManager.createNamedQuery("EmployeeEntity.findById", EmployeeEntity.class);
    namedQuery.setParameter("id", Long.parseLong(id));
    EmployeeEntity employeeEntity = namedQuery.getSingleResult();
    return employeeEntity;
  }

  public EmployeeEntity findEmployeeEntityByUserName(String userName) {
    TypedQuery<EmployeeEntity> namedQuery = entityManager.createNamedQuery("EmployeeEntity.findByUserName", EmployeeEntity.class);
    namedQuery.setParameter("userName", userName);
    EmployeeEntity foundUserEntity;
    List<EmployeeEntity> resultList = namedQuery.getResultList();
    if (resultList.size() == 0) {
      foundUserEntity = null;
    } else {
      foundUserEntity = resultList.get(0);
    }
    return foundUserEntity;
  }

  public TimeCardItemEntity findTimeCardItemEntityByAuthenticationTokenAndId(String authenticationToken, long id) {
    // TODO: Debug TimeCardItemEntity.findByAuthenticationTokenAndId
    //    TypedQuery<TimeCardItemEntity> typedQuery = entityManager.createNamedQuery("TimeCardItemEntity.findByAuthenticationTokenAndId", TimeCardItemEntity.class);
    //    typedQuery.setParameter("authenticationToken", authenticationToken);
    //    typedQuery.setParameter("id", id);
    //    TimeCardItemEntity timeCardItemEntity = typedQuery.getSingleResult();
    EmployeeEntity employeeEntity = findEmployeeEntityByAuthenticationToken(authenticationToken);
    Set<TimeCardItemEntity> timeCardEntitySet = employeeEntity.getTimeCardItemEntitySet();
    for (TimeCardItemEntity timeCardItemEntity : timeCardEntitySet) {
      if (timeCardItemEntity.getId() == id) {
        return timeCardItemEntity;
      }
    }
    return null;
  }

  public void updateEmployeeEntity(EmployeeEntity newEmployeeEntity) {
    EmployeeEntity oldEmployeeEntity = findEmployeeEntityByUserName(newEmployeeEntity.getUserName());
    if (oldEmployeeEntity == null) {
      throw new IllegalArgumentException("employeeEntity does not exist");
    } else {
      oldEmployeeEntity.setUserName(newEmployeeEntity.getUserName());
      oldEmployeeEntity.setCourtesyTitle(newEmployeeEntity.getCourtesyTitle());
      oldEmployeeEntity.setFirstName(newEmployeeEntity.getFirstName());
      oldEmployeeEntity.setLastName(newEmployeeEntity.getLastName());
      oldEmployeeEntity.setAddressLine1(newEmployeeEntity.getAddressLine1());
      oldEmployeeEntity.setAddressLine2(newEmployeeEntity.getAddressLine2());
      oldEmployeeEntity.setAddressLine3(newEmployeeEntity.getAddressLine3());
      oldEmployeeEntity.setCity(newEmployeeEntity.getCity());
      oldEmployeeEntity.setStateOrRegion(newEmployeeEntity.getStateOrRegion());
      oldEmployeeEntity.setIsoCountryCode(newEmployeeEntity.getIsoCountryCode());
      oldEmployeeEntity.setPostCode(newEmployeeEntity.getPostCode());
      oldEmployeeEntity.setDateOfBirth(newEmployeeEntity.getDateOfBirth());
      oldEmployeeEntity.setGender(newEmployeeEntity.getGender());
      oldEmployeeEntity.setPositionCode(newEmployeeEntity.getPositionCode());
      oldEmployeeEntity.setWageAmount(newEmployeeEntity.getWageAmount());
      oldEmployeeEntity.setWageInterval(newEmployeeEntity.getWageInterval());
      oldEmployeeEntity.setExempt(newEmployeeEntity.isExempt());
      oldEmployeeEntity.setManager(newEmployeeEntity.isManager());
      oldEmployeeEntity.setAddressLine1(newEmployeeEntity.getAddressLine1());
      oldEmployeeEntity.setAddressLine1(newEmployeeEntity.getAddressLine1());
      entityManager.getTransaction().begin();
      entityManager.merge(oldEmployeeEntity);
      entityManager.persist(oldEmployeeEntity);
      entityManager.getTransaction().commit();
      entityManager.close();
    }
  }

  public void updateTimeCardItemEntity(String authenticationToken, TimeCardItemEntity newTimeCardItemEntity) {
    TimeCardItemEntity oldTimeCardItemEntity = findTimeCardItemEntityByAuthenticationTokenAndId(authenticationToken, newTimeCardItemEntity.getId());
    if (oldTimeCardItemEntity == null) {
      throw new IllegalArgumentException("timeCardItemEntity does not exist");
    } else {
      oldTimeCardItemEntity.setType(newTimeCardItemEntity.getType());
      oldTimeCardItemEntity.setBeginDate(newTimeCardItemEntity.getBeginDate());
      oldTimeCardItemEntity.setEndDate(newTimeCardItemEntity.getEndDate());
      oldTimeCardItemEntity.setNotes(newTimeCardItemEntity.getNotes());
      entityManager.getTransaction().begin();
      entityManager.merge(oldTimeCardItemEntity);
      entityManager.persist(oldTimeCardItemEntity);
      entityManager.getTransaction().commit();
      entityManager.close();
    }
  }

  private String createAuthenticationToken() {
    Random r = new Random();
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      s.append('0' + r.nextInt(10));
    }
    return s.toString();
  }

}
