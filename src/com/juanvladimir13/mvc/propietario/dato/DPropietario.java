package com.juanvladimir13.mvc.propietario.dato;

import com.juanvladimir13.database.MaryDatabase;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class DPropietario {

  private int id;
  private String nombres;
  private String apellidos;
  private String ci;

  public DPropietario() {
    this.id = 0;
    this.nombres = "";
    this.apellidos = "";
    this.ci = "";
  }

  public void setId(String id) {
    this.id = Integer.parseInt(id);
  }

  public void setData(Map<String, String> data) {
    this.id = Integer.parseInt(data.getOrDefault("id", "0"));
    this.nombres = data.getOrDefault("nombres", "");
    this.apellidos = data.getOrDefault("apellidos", "");
    this.ci = data.getOrDefault("ci", "");
  }

  public List<Map<String, String>> list() {
    String sql = "select * from propietario_propietario";
    return MaryDatabase.getInstance().executeSQLResultList(sql);
  }

  public Map<String, String> find() {
    return find("id", id);
  }

  public Map<String, String> find(String columnName, Object columnValue) {
    String sql = "select * from propietario_propietario where %s='%s' limit 1;";
    sql = String.format(sql, columnName, columnValue);

    List<Map<String, String>> resultado = MaryDatabase.getInstance().executeSQLResultList(sql);
    if (!resultado.isEmpty()) {
      return resultado.get(0);
    }

    Map<String, String> row = new HashMap<>();
    return row;
  }

  public boolean delete() {
    String sql = "delete from propietario_propietario where id=?;";
    return MaryDatabase.getInstance().delete(sql, id);
  }

  public boolean save() {
    boolean proccessed = false;
    String sqlInsert = "insert into propietario_propietario (nombres,apellidos,ci) "
        + "values (?,?,?);";
    String sqlUpdate = "update propietario_propietario "
        + "set nombres=?, apellidos=?, ci=? "
        + "where id=?;";
    String sql = id != 0 ? sqlUpdate : sqlInsert;

    try {
      PreparedStatement statement = MaryDatabase.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, nombres);
      statement.setString(2, apellidos);
      statement.setString(3, ci);
      if (id != 0) {
        statement.setInt(4, id);
      }
      proccessed = MaryDatabase.getInstance().executeSQL(statement);
    } catch (SQLException e) {
    }
    return proccessed;
  }
}
