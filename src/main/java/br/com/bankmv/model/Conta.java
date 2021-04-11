package br.com.bankmv.model;

public class Conta {
	private double saldo;
	private Pessoa titular;
	private double limite;
	private int numeroConta;
	private int agencia;
	
	public Conta() {
	}

	public Conta(Pessoa titular) {
		this.titular = titular;
	}

	public Conta(int numeroConta, int agencia) {
		this.numeroConta = numeroConta;
		this.agencia = agencia;
	}

	public Pessoa getTitular() {
		return titular;
	}

	public void setTitular(Pessoa titular) {
		this.titular = titular;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

}
