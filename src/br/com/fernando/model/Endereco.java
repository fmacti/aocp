package br.com.fernando.model;

public class Endereco {
	
	
	private int id;
	private String logradouro;
	private String cidade;
	private String uf;
	private int clienteId;
	
	public Endereco(int id, String logradouro, String cidade, String uf, int clienteId) {
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.uf = uf;
		this.clienteId = clienteId;
	}
	
	public Endereco(String logradouro, String cidade, String uf, int clienteId) {
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.uf = uf;
		this.clienteId = clienteId;
	}
	
	public Endereco() {}
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
		
}
