package com.juanvladimir13.mvc.propietario.negocio;

import com.juanvladimir13.mvc.propietario.dato.DPropietario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class NPropietario {

  private DPropietario dato;

  public NPropietario() {
    this.dato = new DPropietario();
  }

  public void setData(Map<String, String> data) {
    dato.setData(data);
  }

  public List<Map<String, String>> list() {
    return dato.list();
  }

  public Map<String, String> find(String id) {
    dato.setId(id);
    return dato.find();
  }

  public boolean save() {
    return dato.save();
  }

  public boolean delete(String id) {
    dato.setId(id);
    return dato.delete();
  }

  public Map<String, String> find(String columnName, Object columnValue) {
    return dato.find(columnName, columnValue);
  }
}
