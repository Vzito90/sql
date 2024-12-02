package javaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class scriviSql {
	
	public static void main(String[] args) {
		 // URL di connessione al database

      String url = "jdbc:mysql://localhost:3306/world";


      String username = "root";
      String password = "s4nt4g4t4";
      
      Connection connection = null;

      try {
          // Carica il driver MySQL
          Class.forName("com.mysql.cj.jdbc.Driver");

          // Effettua la connessione
          connection = DriverManager.getConnection(url, username, password);
          System.out.println("Connessione al database riuscita!");

      		Scanner input = new Scanner(System.in);
      		System.out.println("Inserisci nome: ");
      		String nome = input.nextLine();
      		System.out.println("Inserisci genere: ");
      		String genere = input.nextLine();
      		System.out.println("Inserisci locale: ");
      		String locale = input.nextLine();
      		input.close();
      		
      		
      		String insertQuery = "INSERT INTO listadj (nome, genere, locale) VALUES(?, ?, ?)";
      		
      		// Crea un PreparedStatement per eseguire la query
      		PreparedStatement stmt = connection.prepareStatement(insertQuery);
      		stmt.setString(1,  nome);
      		stmt.setString(2, genere);
      		stmt.setString(3, locale);
   
      	  int rowsAffected = stmt.executeUpdate();
      	 
          // Stampa il numero di righe aggiornate
          System.out.println("Numero di righe aggiornate: " + rowsAffected);

          // Chiudi il PreparedStatement
          stmt.close();
      } catch (SQLException e) {
          System.out.println("Errore nella connessione o esecuzione della query");
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          System.out.println("Driver JDBC non trovato");
          e.printStackTrace();
      } finally {
          // Chiude la connessione se aperta
          try {
              if (connection != null && !connection.isClosed()) {
                  connection.close();
                  System.out.println("Connessione chiusa.");
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
	}
	}


}
