package br.com.zup.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/loja_de_pecas?user=root"
					+ "&password=root&useTimezone=true&serverTimezone=UTC");
		} catch (SQLException exception) {
			throw new RuntimeException(exception);
		}
	}

}
