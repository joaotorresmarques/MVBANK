package br.com.bankmv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bankmv.connection.SingleConnection;
import br.com.bankmv.model.Pessoa;

public class PessoaDAO {
	private Connection connection;

	public PessoaDAO() {
		connection = SingleConnection.getConnection();
	}

	// CREATE
	public void salvar(Pessoa pessoa) throws SQLException {
		Date dta = new Date();
		java.sql.Date dataSql = new java.sql.Date(dta.getTime());
		String sql = "INSERT INTO PESSOA(DTCADASTRO,NOME,RAZAOSOCIAL,CGC_CPF,TIPO,RG,IE) " + "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1,dataSql);
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getRazaoSocial());
			stmt.setString(4, pessoa.getCgc_cpf());
			stmt.setString(5, pessoa.getTipo());
			stmt.setString(6, pessoa.getRg());
			stmt.setString(7, pessoa.getIe());
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	// READ
	public List<Pessoa> listar() {
		List<Pessoa> list = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM PESSOA";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoa pe = new Pessoa();
				pe.setDtCadastro(rs.getDate("DTCADASTRO"));
				pe.setNome(rs.getString("NOME"));
				pe.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
				pe.setCgc_cpf(rs.getString("CGC_CPF"));
				pe.setRg(rs.getString("RG"));
				pe.setIe(rs.getString("IE"));
				list.add(pe);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	// READ ONE
	public Pessoa buscar(Long id) {
		Pessoa pe = new Pessoa();
		String sql = "SELECT DTCADASTRO,NOME,RAZAOSOCIAL,CGC_CPF,RG,IE FROM PESSOA where id=" + id;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pe.setId(id);
				pe.setDtCadastro(rs.getDate("DTCADASTRO"));
				pe.setNome(rs.getString("NOME"));
				pe.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
				pe.setCgc_cpf(rs.getString("CGC_CPF"));
				pe.setRg(rs.getString("RG"));
				pe.setIe(rs.getString("IE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pe;
	}

	// UPDATE
	public void atualizar(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE PESSOA SET NOME = ? WHERE ID = " + pessoa.getId();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	// DELETE
	public void deletar(Long id) throws SQLException {
		String sql = "DELETE FROM PESSOA WHERE ID = " + id;
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

	}
	
	
	

}
