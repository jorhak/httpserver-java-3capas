package com.juanvladimir13.capas;

import com.juanvladimir13.httpserver.PathManager;
import com.juanvladimir13.httpserver.Response;
import com.juanvladimir13.httpserver.RouteManager;
import com.juanvladimir13.capas.propietario.presentacion.PPropietario;
import com.juanvladimir13.template.MaryTemplate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class Manager extends RouteManager {

  @Override
  public Response noContentType(String method, String path, Map<String, String> query) {
    PathManager pathManager = new PathManager();
    pathManager.process(path);
    String pathAlias = pathManager.getPathAlias();
    List<String> params = pathManager.getParams();

    Response response = null;

    if (method.equals("GET")) {
      if (pathAlias.startsWith("/propietario")) {
        PPropietario propietario = new PPropietario();
        switch (pathAlias) {
          case "/propietario":
            response = propietario.list();
            break;
          case "/propietario/:id":
            response = propietario.find(params.get(0));
            break;
          case "/propietario/:id/delete":
            response = propietario.delete(params.get(0));
            break;
        }
      }
    }
    
    String html = MaryTemplate.render("index");    
    return response != null ? response : new Response().setResponseHTML(html);
  }

  @Override
  public Response formUrlencoded(String method, String path, Map<String, String> data, Map<String, String> query) {
    PathManager pathManager = new PathManager();
    pathManager.process(path);
    String pathAlias = pathManager.getPathAlias();
    List<String> params = pathManager.getParams();

    Response response = null;

    if (method.equals("POST")) {
      switch (pathAlias) {
        case "/propietario":
          PPropietario controller = new PPropietario();
          response = controller.save(data);
          break;
      }
    }
    String html = MaryTemplate.render("index"); 
    return response != null ? response : new Response().setResponseHTML(html);
  }

  @Override
  public Response applicationJson(String method, String path, String json, Map<String, String> query) {   
    return new Response().setResponseJSON(json);
  }
}
