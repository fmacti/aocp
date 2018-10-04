package br.com.fernando.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ClienteView {
	
	
	private int id;
	private String nome;
	private String cpfCnpj;
	private Date dataNasc;
	private List<Contato> contatos = new LinkedList<>();
	private List<Endereco> enderecos = new LinkedList<>();
	
	
	public ClienteView(int id, String nome, String cpfCnpj, Date dataNasc) {
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.dataNasc = dataNasc;
	}
			
	public ClienteView() {}
	
	public void addContatos(List<Contato> conts) {
		if(!conts.isEmpty()) {
			contatos.addAll(conts);
		}
	}
	
	public void addEnderecos(List<Endereco> ends) {
		if(!ends.isEmpty()) {
			enderecos.addAll(ends);
		}		
	}
			
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

	public List<Contato> getContatos() {
		return contatos;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}		
}
