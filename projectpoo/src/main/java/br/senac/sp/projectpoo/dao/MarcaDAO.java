package br.senac.sp.projectpoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senac.sp.projectpoo.modelo.Marca;

public class MarcaDAO {
	private Connection conexao;
	String sql;
	private PreparedStatement stmt;
	
	public MarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void inserir(Marca marca) throws SQLException {
		sql = "insert into marca(nome,logo) values(?,?) ";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getNome());
		stmt.setBytes(2, marca.getLogo());
		stmt.execute();
		stmt.close();
	}
	
	
	
	
}