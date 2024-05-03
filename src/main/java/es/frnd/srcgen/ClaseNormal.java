package es.frnd.srcgen;

import es.frnd.srcgen.Version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClaseNormal {

  /**
   * Método principal donde se muestra un ejemplo de vulnerabilidad de inyección de SQL.
   * Este método ejecuta una consulta SQL utilizando datos proporcionados por el usuario sin escaparlos correctamente,
   * lo que puede permitir una inyección de SQL.
   * 
   * @param args Los argumentos de la línea de comandos.
   */
  public static void main(String[] args) {
    System.out.println("Sources generation Example:");
    System.out.println("Project name : " + Version.PROJECT_NAME);
    System.out.println("Version : " + Version.VERSION_NUMBER);

    // Vulnerabilidad de inyección de SQL
    String userInput = args.length > 0 ? args[0] : "1'; DROP TABLE users; --";
    String query = "SELECT * FROM users WHERE id = " + userInput;

    try {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      // Procesar resultados...
      
      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
