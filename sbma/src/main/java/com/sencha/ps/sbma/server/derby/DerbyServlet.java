package com.sencha.ps.sbma.server.derby;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.drda.NetworkServerControl;

import com.sencha.ps.sbma.server.util.Log;

public class DerbyServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static NetworkServerControl networkServerControl;

  public DerbyServlet() {

  }

  public void init(ServletConfig config) throws ServletException {
    try {
      Log.log("DerbyServlet.init: entered");
      
      String derbySystemHome = config.getInitParameter("derby.system.home");
      if (derbySystemHome != null) {
        System.setProperty("derby.system.home", derbySystemHome);
      }

      String derbyDrdaHostString = config.getInitParameter("derby.drda.host");
      InetAddress derbyDrdaHost = InetAddress.getByName(derbyDrdaHostString);

      String derbyDrdaPortString = config.getInitParameter("derby.drda.port");
      int derbyDrdaPort = derbyDrdaPortString == null ? 0 : Integer.parseInt(derbyDrdaPortString);

      networkServerControl = new NetworkServerControl(derbyDrdaHost, derbyDrdaPort);
      networkServerControl.start(new PrintWriter(System.out, true));
      Log.log("DerbyServlet.init: returning");
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void destroy() {
    try {
      Log.log("DerbyServlet.destroy: entered");
      if (networkServerControl != null) {
        networkServerControl.shutdown();
      }
      Log.log("DerbyServlet.destroy: returning");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

}
