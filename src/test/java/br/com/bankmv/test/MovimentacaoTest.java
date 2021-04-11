package br.com.bankmv.test;

import java.util.Date;

import org.junit.Test;

import br.com.bankmv.dao.MovimentacaoDAO;
import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Conta;
import br.com.bankmv.model.Movimentacao;
import br.com.bankmv.model.Pessoa;

/**
 * Unit test for simple App.
 */
public class MovimentacaoTest {

	@Test
	public void movimentar() {

		MovimentacaoDAO dao = new MovimentacaoDAO();
		PessoaDAO daoPessoa = new PessoaDAO();
		Pessoa p = daoPessoa.buscar(23L);
		
		Conta c = new Conta(434, 1241);
		try {
			Movimentacao mov = new Movimentacao(new Date(),p, c, "D", new Double(200));
			dao.mov(mov);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
