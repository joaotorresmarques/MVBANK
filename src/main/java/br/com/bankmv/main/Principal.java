package br.com.bankmv.main;

import java.util.ArrayList;
import java.util.List;

import br.com.bankmv.dao.PessoaDAO;
import br.com.bankmv.model.Pessoa;
import br.com.bankmv.relatorio.Relatorio;
import net.sf.jasperreports.engine.JRException;

public class Principal {

	public static void main(String[] args) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		PessoaDAO dao = new PessoaDAO();
		lista = dao.listar();
		
		Relatorio rel = new Relatorio();
		try {
			rel.gerar(lista);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
