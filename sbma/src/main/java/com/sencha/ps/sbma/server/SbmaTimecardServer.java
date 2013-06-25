package com.sencha.ps.sbma.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.Pair;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.sencha.ps.sbma.server.resource.CredentialResource;
import com.sencha.ps.sbma.server.resource.EmployeeResource;
import com.sencha.ps.sbma.server.resource.TimeCardItemResource;

@ApplicationPath("sbma")
public class SbmaTimecardServer extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(CredentialResource.class);
    classes.add(TimeCardItemResource.class);
    classes.add(EmployeeResource.class);
    return classes;
  }

  // See http://wiki.fasterxml.com/JacksonJAXBAnnotations

  @Override
  public Set<Object> getSingletons() {
    Set<Object> s = new HashSet<Object>();
    ObjectMapper mapper = new ObjectMapper();
    JacksonAnnotationIntrospector primary = new JacksonAnnotationIntrospector();
    JaxbAnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
    Pair pair = new AnnotationIntrospector.Pair(primary, secondary);
    mapper.setAnnotationIntrospector(pair);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
    jaxbProvider.setMapper(mapper);
    s.add(jaxbProvider);
    return s;
  }
}
