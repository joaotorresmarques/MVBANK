package br.com.bankmv.model;

import java.util.Date;

public class Pessoa {
	private Date dtCadastro;
	private Long id;
	private String nome;
	private String razaoSocial;
	private String tipo;
	private String cgc_cpf;
	private String rg;
	private String ie;
	
	@Override
	public String toString() {
		return "Pessoa [dtCadastro=" + dtCadastro + ", id=" + id + ", nome=" + nome + ", razaoSocial=" + razaoSocial
				+ ", tipo=" + tipo + ", cgc_cpf=" + cgc_cpf + ", rg=" + rg + ", ie=" + ie + "]";
	}

	public Pessoa() {
	}
	
	public Pessoa(String nome, String razaoSocial, String tipo, String cgc_cpf, String rg, String ie) {
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.tipo = tipo;
		this.cgc_cpf = cgc_cpf;
		this.rg = rg;
		this.ie = ie;
	}
	
	
	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCgc_cpf() {
		return cgc_cpf;
	}
	public void setCgc_cpf(String cgc_cpf) {
		this.cgc_cpf = cgc_cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	
	


	
	
}
