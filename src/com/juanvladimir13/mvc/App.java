package com.juanvladimir13.mvc;

import com.juanvladimir13.httpserver.MaryServer;
import com.juanvladimir13.template.MaryTemplate;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class App {
  private static int getPort(String portParam){    
    int port = 0;
    try {
      port = Integer.parseInt(portParam);
    } catch (Exception e) {
      port = 8000;
    }
    return port;
  }
  
  public static void main(String[] args) {    
    int port = getPort(args.length > 0 ? args[0] : "") ;
    
    MaryTemplate.setFilePath("templates");    
    MaryServer maryServer = new MaryServer(port, new Manager());
    maryServer.start();
    System.out.println("Server listen : " + port);
  }
}
