package br.com.bankmv.test;

import java.sql.SQLException;

import org.junit.Test;

import br.com.bankmv.dao.ContaDAO;
import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Conta;
import br.com.bankmv.model.Pessoa;

/**
 * Unit test for simple App.
 */
public class ContaTest {

	@Test
	public void salvarConta() {
		ContaDAO dao = new ContaDAO();
		PessoaDAO daoPessoa = new PessoaDAO();
		Pessoa p = daoPessoa.buscar(23L);

		Conta c = new Conta(p);

		try {
			dao.salvar(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deletarConta() {
		ContaDAO dao = new ContaDAO();
		Conta c = new Conta(492, 1241);

		try {
			dao.deletar(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
