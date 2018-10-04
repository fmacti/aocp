package br.com.fernando.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fernando.config.Conexao;
import br.com.fernando.model.Cliente;
import br.com.fernando.model.ClienteView;

public class ClienteDao {
	
	private Connection conn;

    public ClienteDao() {
        this.conn = new Conexao().getConnection();
    }
	
	public int salvar(Cliente cliente){		 
				 
		 String sql = "INSERT INTO cliente(nome,cpf_cnpj,data_nasc)" +
		 " VALUES(?,?,?)";
		 
		 PreparedStatement pstm = null;
		 
		 try {		 		 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			 
			 pstm.setString(1, cliente.getNome());
			 pstm.setString(2, cliente.getCpfCnpj());
			 pstm.setDate(3, new java.sql.Date(cliente.getDataNasc().getTime()));
			 
			 pstm.execute();
			 
			 int ultimoId = 0;
			 ResultSet rs = pstm.getGeneratedKeys();
			 if (rs.next()) {
				 ultimoId = rs.getInt(1);
			 }			 
			 return ultimoId;
		 
		 } catch (Exception e) {		 
			 e.printStackTrace();
		 }
		 finally{
			 
			 try{
				 /*if(pstm != null){		 
					 pstm.close();
				 }
			 
				 if(conn != null){
					 conn.close();
				 }*/
		 
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
		return 0;
	}
	
	public List<ClienteView> getLista() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<ClienteView> clientes = new ArrayList<ClienteView>();
            String sql = "select c.*, (select co.fixo from contato co where co.cliente_id = c.id limit 1) AS fixo, " + 
       			 "(select co.celular from contato co where co.cliente_id = c.id limit 1) AS celular " + 
       			 "from cliente c";
            pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
            	ClienteView cli = new ClienteView(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getDate("data_nasc")); 
            	cli.addContatos(rs.getString("fixo"), rs.getString("celular"));
            	clientes.add(cli);
            }            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
			 
			 try{
				 if(rs != null){		 
					 rs.close();
				 }
				 if(pstm != null){		 
					 pstm.close();
				 }
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
    }
	
	public List<ClienteView> getListaFone(String txt) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<ClienteView> clientes = new ArrayList<ClienteView>();
            String sql = "select c.*, (select co.fixo from contato co where co.cliente_id = c.id limit 1) AS fixo, " + 
            			 "(select co.celular from contato co where co.cliente_id = c.id limit 1) AS celular " + 
            			 "from cliente c where c.id in (select cliente_id from contato where fixo like ? OR celular like ?)";
            
            pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, '%' + txt + '%');
            pstm.setString(2, '%' + txt + '%');
            rs = pstm.executeQuery();

            while (rs.next()) {
                ClienteView cli = new ClienteView(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getDate("data_nasc"));
                cli.addContatos(rs.getString("fixo"), rs.getString("celular"));
                clientes.add(cli);
            }            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
			 
			 try{
				 if(rs != null){		 
					 rs.close();
				 }
				 if(pstm != null){		 
					 pstm.close();
				 }
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
    }
	
}
