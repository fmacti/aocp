package br.com.fernando.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fernando.config.Conexao;
import br.com.fernando.model.Endereco;

public class EnderecoDao {
	
	private Connection conn;

    public EnderecoDao() {
        this.conn = new Conexao().getConnection();
    }
	
	public void salvar(Endereco endereco){		 
				 
		 String sql = "INSERT INTO endereco(logradouro, cidade, uf, cliente_id)" +
		 " VALUES(?,?,?,?)";
		 
		 PreparedStatement pstm = null;
		 
		 try {		 		 
			 pstm = conn.prepareStatement(sql);
			 
			 pstm.setString(1, endereco.getLogradouro());
			 pstm.setString(2, endereco.getCidade());
			 pstm.setString(3, endereco.getUf());
			 pstm.setInt(4, endereco.getClienteId());
			 
			 pstm.execute();
		 
		 } catch (Exception e) {		 
			 e.printStackTrace();
		 }
		 finally{
			 
			 try{
				 if(pstm != null){		 
					 pstm.close();
				 }			 				 		 
			 }
			 catch(Exception e){		 
				 e.printStackTrace();
			 }
		 }
	}
	
	public List<Endereco> getLista(int clienteId) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<Endereco> enderecos = new ArrayList<Endereco>();
            pstm = this.conn.prepareStatement("select * from endereco where cliente_id = ?");
            pstm.setInt(1, clienteId);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Endereco e = new Endereco(rs.getInt("id"), rs.getString("logradouro"), rs.getString("cidade"), rs.getString("uf"), rs.getInt("cliente_id"));           
                enderecos.add(e);
            }            
            return enderecos;
            
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
