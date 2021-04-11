package br.com.bankmv.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bankmv.dao.ContaDAO;
import br.com.bankmv.dao.MovimentacaoDAO;
import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Conta;
import br.com.bankmv.model.Movimentacao;
import br.com.bankmv.model.Pessoa;
import br.com.bankmv.relatorio.Relatorio;
import net.sf.jasperreports.engine.JRException;

public class Principal {

	public static void main(String[] args) {

		// INSTANCIANDO PESSOA
		PessoaDAO dao = new PessoaDAO();
		Pessoa pe = new Pessoa("Paulo Magnus", null, "F", "112321", "123213", null);

		try {
			dao.salvar(pe);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// INSTANCIANDO CONTA
		ContaDAO daoConta = new ContaDAO();
		PessoaDAO daoPessoa = new PessoaDAO();
		Pessoa p = daoPessoa.buscar(23L);

		Conta c = new Conta(p);

		try {
			daoConta.salvar(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		MovimentacaoDAO daoMov = new MovimentacaoDAO();
		PessoaDAO daoPessoaMov = new PessoaDAO();
		Pessoa ps = daoPessoaMov.buscar(23L);

		// INSERIR AGENCIA E CONTA CADASTRADA ANTERIORMENTE
		Conta cc = new Conta(434, 1241);
		try {
			Movimentacao mov = new Movimentacao(new Date(), ps, cc, "D", new Double(200));
			daoMov.mov(mov);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// TESTANDO O IREPORT
		List<Pessoa> lista = new ArrayList<Pessoa>();
		PessoaDAO dao2 = new PessoaDAO();
		lista = dao2.listar();

		Relatorio rel = new Relatorio();
		try {
			rel.gerar(lista);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
