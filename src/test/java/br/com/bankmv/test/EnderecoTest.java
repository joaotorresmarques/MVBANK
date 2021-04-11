package br.com.bankmv.test;

import org.junit.Test;

import br.com.bankmv.dao.EnderecoDAO;
import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Endereco;
import br.com.bankmv.model.Pessoa;

public class EnderecoTest {
	@Test
	public void salvarEndereco(){
		EnderecoDAO dao = new EnderecoDAO();
		PessoaDAO daoPessoa = new PessoaDAO();
		Pessoa p = daoPessoa.buscar(23L);
		Endereco end = new Endereco("Via Local", "12", "Cond. Aurora", "Centro", "Paulista", "PE", "55819050", p);
		
		try {
			dao.salvar(end);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void deletarEndereco(){
		EnderecoDAO dao = new EnderecoDAO();
		
		try {
			dao.deletar(1L);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
