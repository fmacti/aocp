package br.com.fernando.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
	
	
	private int id;
	private String nome;
	private String cpfCnpj;
	private Date dataNasc;
	
	public Cliente(int id, String nome, String cpfCnpj, Date dataNasc) {
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.dataNasc = dataNasc;
	}
	
	public Cliente(String nome, String cpfCnpj, String dataNasc) {
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		setDataNasc(dataNasc);
	}
	
	public Cliente() {}
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public String getDataNascFormatada() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(dataNasc);
	}
	public void setDataNasc(String dataNasc) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataNasc = new java.sql.Date(((java.util.Date)formatter.parse(dataNasc)).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
