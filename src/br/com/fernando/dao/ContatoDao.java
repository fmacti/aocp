package br.com.fernando.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fernando.config.Conexao;
import br.com.fernando.model.Contato;

public class ContatoDao {
	
	private Connection conn;

    public ContatoDao() {
        this.conn = new Conexao().getConnection();
    }
	
	public void salvar(Contato contato){		 
				 
		 String sql = "INSERT INTO contato(fixo, celular, cliente_id)" +
		 " VALUES(?,?,?)";
		 
		 PreparedStatement pstm = null;
		 
		 try {		 		 
			 pstm = conn.prepareStatement(sql);
			 
			 pstm.setString(1, contato.getFixo());
			 pstm.setString(2, contato.getCelular());
			 pstm.setInt(3, contato.getClienteId());
			 
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
	
	public List<Contato> getLista(int clienteId) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
        try {
            List<Contato> contatos = new ArrayList<Contato>();
            pstm = this.conn.prepareStatement("select * from contato where cliente_id = ?");
            pstm.setInt(1, clienteId);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Contato c = new Contato(rs.getInt("id"), rs.getString("fixo"), rs.getString("celular"), rs.getInt("cliente_id"));           
                contatos.add(c);
            }            
            return contatos;
            
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
