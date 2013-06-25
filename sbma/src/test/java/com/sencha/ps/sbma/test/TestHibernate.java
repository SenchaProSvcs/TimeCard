package com.sencha.ps.sbma.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sencha.ps.sbma.server.entity.CredentialEntity;
import com.sencha.ps.sbma.server.entity.EmployeeEntity;

public class TestHibernate {

  @BeforeClass
  public static void setUpClass() throws Exception {
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
    InitialContext ic = new InitialContext();

    ic.createSubcontext("java:");
    ic.createSubcontext("java:comp");
    ic.createSubcontext("java:comp/env");
    ic.createSubcontext("java:comp/env/jdbc");

    EmbeddedDataSource ds = new EmbeddedDataSource();
    ds.setDatabaseName("SbmaDB");
    // tell Derby to create the database if it does not already exist
    ds.setCreateDatabase("create");

    ic.bind("java:comp/env/jdbc/SbmaDS", ds);
  }

  @AfterClass
  public static void tearDownClass() throws Exception {

    InitialContext ic = new InitialContext();

    ic.unbind("java:comp/env/jdbc/SbmaDS");
  }

  @Test
  public void testNewUser() {

    EntityManager entityManager = Persistence.createEntityManagerFactory("SbmaPU").createEntityManager();

    entityManager.getTransaction().begin();

    EmployeeEntity user = new EmployeeEntity();

    user.setUserName(Long.toString(new Date().getTime()));

    entityManager.persist(user);

    entityManager.getTransaction().commit();

    // see that the ID of the user was set by Hibernate
    System.out.println("user=" + user + ", user.id=" + user.getId());

    EmployeeEntity foundUser = entityManager.find(EmployeeEntity.class, user.getId());

    // note that foundUser is the same instance as user and is a concrete class (not a proxy)
    System.out.println("foundUser=" + foundUser);

    assertEquals(user.getUserName(), foundUser.getUserName());

    entityManager.close();
  }

  @Test
  public void testFindUser() throws Exception {

    EntityManager entityManager = Persistence.createEntityManagerFactory("SbmaPU").createEntityManager();

    entityManager.getTransaction().begin();

    EmployeeEntity userEntity = new EmployeeEntity();

    String name = Long.toString(new Date().getTime());

    userEntity.setUserName(name);

    CredentialEntity credentialEntity = new CredentialEntity();

    credentialEntity.setAuthenticationToken("hello world");

    userEntity.setCredentialEntity(credentialEntity);

    entityManager.persist(credentialEntity);
    entityManager.persist(userEntity);

    entityManager.getTransaction().commit();

    entityManager.close();

    entityManager = Persistence.createEntityManagerFactory("SbmaPU").createEntityManager();

    EmployeeEntity foundUserEntity = entityManager.createNamedQuery("UserEntity.findByUserName", EmployeeEntity.class).setParameter("userName", name).getSingleResult();

    System.out.println(foundUserEntity);

    assertEquals(name, foundUserEntity.getUserName());

    assertEquals("hello world", foundUserEntity.getCredentialEntity().getAuthenticationToken());

    entityManager.close();
  }
}