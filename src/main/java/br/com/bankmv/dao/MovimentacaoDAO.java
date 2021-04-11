package br.com.bankmv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.bankmv.connection.SingleConnection;
import br.com.bankmv.model.Movimentacao;

public class MovimentacaoDAO {
	private Connection connection;

	public MovimentacaoDAO() {
		connection = SingleConnection.getConnection();
	}

	public void mov(Movimentacao mov) throws SQLException {
		String sql = "INSERT INTO MOVIMENTACAO(DHMOV,IDPESSOA,NUMEROCONTA,AGENCIA,TIPO,VALOR) " + "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new Date(mov.getDhmov().getTime()));
			stmt.setLong(2, mov.getIdPessoa().getId());
			stmt.setLong(3, mov.getConta().getNumeroConta());
			stmt.setLong(4, mov.getConta().getAgencia());
			stmt.setString(5, mov.getTipo());
			stmt.setDouble(6, mov.getValor());
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}
}
