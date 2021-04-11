package br.com.bankmv.model;

import java.util.Date;

public class Movimentacao {
	private Date dhmov;
	private Long codmov;
	private Pessoa idPessoa;
	private Conta conta;
	private String tipo;
	private Double valor;

	public Movimentacao(Date dhmov, Pessoa idPessoa, Conta conta, String tipo, Double valor) {
		this.dhmov = dhmov;
		this.idPessoa = idPessoa;
		this.conta = conta;
		this.tipo = tipo;
		this.valor = valor;
	}

	public Date getDhmov() {
		return dhmov;
	}

	public void setDhmov(Date dhmov) {
		this.dhmov = dhmov;
	}

	public Long getCodmov() {
		return codmov;
	}

	public void setCodmov(Long codmov) {
		this.codmov = codmov;
	}

	public Pessoa getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		valor = valor;
	}

}
