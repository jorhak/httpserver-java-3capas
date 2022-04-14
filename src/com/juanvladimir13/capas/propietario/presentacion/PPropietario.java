package com.juanvladimir13.capas.propietario.presentacion;

import com.juanvladimir13.httpserver.Response;
import com.juanvladimir13.capas.propietario.negocio.NPropietario;
import com.juanvladimir13.template.MaryTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class PPropietario {

  private String title = "Gestionar propietario";
  private NPropietario negocio;

  public PPropietario() {
    this.negocio = new NPropietario();
  }

  public Response list() {
    List<Map<String, String>> data = negocio.list();
    String html = inflaterTable(data);
    return new Response().setResponseHTML(html);
  }

  public Response find(String id) {
    Map<String, String> data = negocio.find(id);
    List<Map<String, String>> table = negocio.list();

    String html = inflaterFind(table, data);
    return new Response().setResponseHTML(html);
  }

  public Response delete(String id) {
    negocio.delete(id);
    return new Response().setResponseRedirect("/propietario");
  }

  public Response save(Map<String, String> data) {
    negocio.setData(data);
    if (!negocio.save()) {
      List<Map<String, String>> table = negocio.list();
      String html = inflaterFind(table, data);
      return new Response().setResponseHTML(html);
    }

    String id = data.getOrDefault("id", "0");
    if (!id.equals("0")) {
      return new Response().setResponseRedirect("/propietario/" + id);
    }

    Map<String, String> row = negocio.find("ci", data.getOrDefault("ci", ""));
    List<Map<String, String>> table = negocio.list();
    String html = inflaterFind(table, row);
    return new Response().setResponseHTML(html);
  }

  private String findAndDelete(String id) {
    String td = "<a href='/propietario/" + id + "'>&nbsp;&#x1f50e;</a>";
    td += "<a href='/propietario/" + id + "/delete'> &#x274c;&nbsp;</a>";
    return td;
  }

  private String generateTable(List<Map<String, String>> rows) {
    String table = "";
    for (int index = 0; index < rows.size(); index++) {
      Map<String, String> row = rows.get(index);
      table += "<tr>";
      table += "<td>" + row.get("nombres") + "</td>";
      table += "<td>" + row.get("apellidos") + "</td>";
      table += "<td>" + row.get("ci") + "</td>";
      table += "<td>" + findAndDelete(row.get("id")) + "</td>";
      table += "</tr>";
    }
    return !table.isEmpty() ? table : "Sin datos";
  }

  private String inflaterTable(List<Map<String, String>> rows) {
    Map<String, String> data = new HashMap<>();
    data.put("tbody", generateTable(rows));
    data.put("title", title);
    return MaryTemplate.render("propietario", data);
  }

  private String inflaterFind(List<Map<String, String>> table, Map<String, String> row) {
    Map<String, String> data = new HashMap<>();
    data.put("title", title);
    data.put("tbody", generateTable(table));

    if (!row.isEmpty()) {
      data.put("id", row.getOrDefault("id", "0"));
      data.put("nombres", row.getOrDefault("nombres", ""));
      data.put("apellidos", row.getOrDefault("apellidos", ""));
      data.put("ci", row.getOrDefault("ci", ""));
    }
    return MaryTemplate.render("propietario", data);
  }
}
