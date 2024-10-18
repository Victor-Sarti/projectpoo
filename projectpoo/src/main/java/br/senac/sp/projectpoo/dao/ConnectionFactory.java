package br.senac.sp.projectpoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static 	Connection conexao;
	
	public static Connection getConexao() {
		if(conexao == null) {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectpoo","root","");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conexao;
	}
}
