package br.com.fernando.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");		
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
    }

}
