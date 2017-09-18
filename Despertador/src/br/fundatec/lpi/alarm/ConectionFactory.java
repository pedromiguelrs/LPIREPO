package br.fundatec.lpi.alarm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {

	public Connection fabricate() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Smartphone", "postgres", "postgres");
		} catch (SQLException e) {
			throw new RuntimeException("Connection error");
		}
	}
}
