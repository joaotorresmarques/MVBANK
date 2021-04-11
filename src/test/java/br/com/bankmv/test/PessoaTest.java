package br.com.bankmv.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Pessoa;

/**
 * Unit test for simple App.
 */
public class PessoaTest {

	@Test
	public void salvarPessoa() {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pe = new Pessoa("Paulo Magnus", null, "F", "112321", "123213", null);

		try {
			dao.salvar(pe);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void atualizarPessoa() {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pe = new Pessoa();
		pe.setId(21L);
		pe.setNome("Paulo Magnus da silva");
		try {
			dao.atualizar(pe);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void deletarPessoa() {
		PessoaDAO dao = new PessoaDAO();
		try {
			dao.deletar(21L);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Listar() {
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> list = dao.listar();

		for (Pessoa pessoa : list) {
			System.out.println(pessoa.getNome());
		}
	}

	@Test
	public void buscar() {
		PessoaDAO dao = new PessoaDAO();
		Pessoa user = dao.buscar(22L);
		System.out.println(user.toString());
	}

}
