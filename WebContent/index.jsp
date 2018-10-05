<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
	<meta charset="ISO-8859-1">
	<title>Cliente</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/jquery-ui.css" />
    <body>
     <div class="container"> 
        <div class="row">
	      <div class="panel panel-primary">
	    	<div class="panel-heading">Cliente</div>	      
		      <div class="panel-body">  
		          <form action="sistema?op=adiciona" method="post">		        
			        	<div class="form-group">
			        	   <div class="row">
				        	   	<div class="col-md-12">
								    <label for="nome">Nome</label>
								    <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome Pessoa ou Nome Empresa" required="required" autocomplete = "off" maxlength="150">
							    </div>
						   </div>
						 </div>
						 <div class="form-group">
						   <div class="row">
			                    <div class="col-md-6">
			                    	<label for="cpfCnpj">CPF/CNPJ</label>
						    		<input type="text" class="form-control" id="cpfCnpj" name="cpfCnpj" placeholder="Se for pessoa fisíca CPF, empresa CNPJ" required="required" autocomplete = "off" onkeyup = "mascara(this,cpfCnpjMask);" maxlength="18">
			                    </div>
			                    <div class="col-md-6">
			                    	<label for="data">Data</label>
						    		<input type="text" class="form-control calendario" id="data" name="dataNasc" placeholder="Se for pessoa fisíca Nascimento, empresa Abertura" required="required" autocomplete = "off">
			                    </div>
			                </div>				    
						 </div>
										 
						 <div class="form-group">
			                <div class="row">
			                    <div class="col-md-12">
			                        <label>Contatos</label>
			                        <table id="listaContatos" class="table"></table>
			                        <div class="text-center">
			                            <a  href="#" id="add_Contato" class="btn btn-warning btn-sm">Adicionar Contato</a>
			                        </div>   
			                    </div>
			                </div>
		            	</div>            	
		            	<div class="form-group">
		                    <div class="row">
		                        <div class="col-md-12">
		                            <label>Endereços</label>
		                            <table id="listaEnderecos" class="table"></table>
		                            <div class="text-center">
		                                <a  href="#" id="add_Endereco" class="btn btn-success btn-sm">Adicionar Endereço</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="form-group">
		                    <div class="row">
		                        <div class="col-md-12 text-right">
		                           <button type="submit" class="btn btn-primary text-right">Salvar</button> 
		                        </div>
		                    </div>
		                </div>
			        </form>
			     </div>
		     </div>
	        <hr />	        
	        <div class="panel panel-success">
	        	<div class="panel-heading">Lista dos Clientes</div>
	        	   <div class="panel-body">        
		        	<c:if test="${empty lista}">
		        	   <div class="form-group">
		        	      <div class="row">
			                 <div class="col-md-12">
			        			<a href="sistema?op=lista" class="btn btn-default">Listar Clientes Cadastrados</a>
			        		 </div>
		        		  </div>
		        	   </div>
		    		</c:if>
		    		<c:if test="${not empty lista}">
		    		<div class="form-group">
		        	      <div class="row">
			                 <div class="col-md-12">
			        			<form action="sistema?op=listaFiltro" method="post" class="form-inline">					    		   
						             <div class="form-group">
									    <label for="pesNome">DDD</label>
									    <input type="text" class="form-control ddd" id="pesNome" name="txt" placeholder="Pesquisar por DDD" autocomplete = "off" maxlength="2">
									 </div>
									 <input class="btn btn-info" type="submit" value="pesquisar" />
									 <a href="/controleClientes" class="btn btn-default">Fechar Lista</a>						        	  								   
					        	</form>
			        		 </div>
		        		  </div>
		        	   </div>		    		   
		    		</c:if>
		           
			        <table class="table table-striped">
			            <c:if test="${!empty lista}">
		        			<tr>
				            	<th>Nome</th>
				            	<th>CPF/CNPJ</th>
				            	<th>Data</th>
				            	<th>Telefones</th>
				            	<th>Endereços</th>
			            	</tr>
		    			</c:if>		        	
				        <c:forEach var="cli" items="${lista}" varStatus="id">			            
				            <tr>
				                <td>${cli.getNome()}</td> 
				                <td>${cli.getCpfCnpj()}</td>
				                <td>${cli.getDataNascFormatada()}</td>
				                <td>		                  
				                	<c:forEach var="cont" items="${cli.getContatos()}">
				                		<p>${cont.getFixo()} - ${cont.getCelular()}</p>
				                	</c:forEach>		                  
				                </td>
				                <td>		                  
				                	<c:forEach var="end" items="${cli.getEnderecos()}">
				                		<p>${end.getLogradouro()} - ${end.getCidade()} / ${end.getUf()}</p>			                		
				                	</c:forEach>		                  
				                </td>
				            </tr>
				        </c:forEach>
			    	</table>
		    	</div>
		     </div>	    	
    	</div>
      </div>
    </body>    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/jquery.mask.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/sistema.js"></script>
    
</html>