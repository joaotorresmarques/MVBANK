package br.com.bankmv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import br.com.bankmv.connection.SingleConnection;
import br.com.bankmv.model.Conta;

public class ContaDAO {
	private Connection connection;
	private static final Integer saldo = 0;
	private static final Integer limite = 1000;
	private static final Integer agencia = 1241;
	private static final Random random = new Random();

	public ContaDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Conta conta) throws SQLException {
		String sql = "INSERT INTO CONTA(SALDO,LIMITE,NUMEROCONTA,AGENCIA,IDPESSOA) " + "VALUES (?,?,?,?,?) ";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, saldo);
			stmt.setDouble(2, limite);
			stmt.setInt(3, random.nextInt(999));
			stmt.setInt(4, agencia);
			stmt.setLong(5, conta.getTitular().getId());
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void deletar(Conta conta) throws SQLException {
		String sql = "DELETE FROM CONTA WHERE NUMEROCONTA = ? and AGENCIA = ? ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, conta.getNumeroConta());
			stmt.setInt(2, conta.getAgencia());
			stmt.execute();
			connection.commit();

		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

}
