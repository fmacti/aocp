package br.com.fernando.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fernando.config.Conexao;
import br.com.fernando.model.Cliente;

public class ClienteDao {
	
	private Connection conn;

    public ClienteDao() {
        this.conn = new Conexao().getConnection();
    }
	
	public void salvar(Cliente cliente){		 
				 
		 String sql = "INSERT INTO cliente(nome,cpf_cnpj,data_nasc)" +
		 " VALUES(?,?,?)";
		 
		 PreparedStatement pstm = null;
		 
		 try {		 		 
			 //Cria um PreparedStatment, classe usada para executar a query
			 pstm = conn.prepareStatement(sql);
			 
			 pstm.setString(1, cliente.getNome());
			 pstm.setString(2, cliente.getCpfCnpj());
			 pstm.setDate(3, new java.sql.Date(cliente.getDataNasc().getTime()));
			 
			 pstm.execute();
		 
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
	}
	
	public List<Cliente> getLista() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<Cliente> clientes = new ArrayList<Cliente>();
            pstm = this.conn.prepareStatement("select * from cliente");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getDate("data_nasc"));           
            	clientes.add(cli);
            }            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
			 
			 try{
				 /*if(rs != null){		 
					 rs.close();
				 }
				 if(pstm != null){		 
					 pstm.close();
				 }				 			 
				 if(conn != null){
					 conn.close();
				 }		 */
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
    }
	
	public List<Cliente> getListaFone(String txt) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<Cliente> clientes = new ArrayList<Cliente>();
            pstm = this.conn.prepareStatement("select * from cliente WHERE nome LIKE ?");
            pstm.setString(1, '%' + txt);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getDate("data_nasc"));           
            	clientes.add(cli);
            }            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
			 
			 try{
				 /*if(rs != null){		 
					 rs.close();
				 }
				 if(pstm != null){		 
					 pstm.close();
				 }				 			 
				 if(conn != null){
					 conn.close();
				 }		 */
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
    }
	
}
