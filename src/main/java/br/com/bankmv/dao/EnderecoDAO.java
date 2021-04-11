package br.com.bankmv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bankmv.connection.SingleConnection;
import br.com.bankmv.model.Endereco;

public class EnderecoDAO {
	private Connection connection;

	public EnderecoDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Endereco endereco) throws SQLException {
		String sql = "INSERT INTO ENDERECO(RUA,NUMERO,COMPLEMENTO,BAIRRO,CIDADE,UF,CEP,IDPESSOA) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getBairro());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getUF());
			stmt.setString(7, endereco.getCEP());
			stmt.setLong(8, endereco.getPessoa().getId());
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	public void deletar(Long id) throws SQLException {
		String sql = "DELETE FROM ENDERECO WHERE CODEND = " + id;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();

		}

	}
	
}
