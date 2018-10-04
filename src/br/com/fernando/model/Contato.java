package br.com.fernando.model;

public class Contato {
	
	
	private int id;
	private String fixo;
	private String celular;
	private int clienteId;
	
	public Contato(int id, String fixo, String celular, int clienteId) {
		this.id = id;
		this.fixo = fixo;
		this.celular = celular;
		this.clienteId = clienteId;
	}
	
	public Contato(String fixo, String celular, int clienteId) {
		this.fixo = fixo;
		this.celular = celular;
		this.clienteId = clienteId;
	}
	
	public Contato() {}
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
		
}
