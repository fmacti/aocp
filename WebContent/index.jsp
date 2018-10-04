<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<meta charset="ISO-8859-1">
	<title>Cliente</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/jquery-ui.css" />
    <body>
      <div class="panel panel-primary">
    	<div class="panel-heading">Cliente</div>
      </div>	
      <div class="container"> 
        <div class="row"> 
          <div class="col-md-12">
	        <form action="sistema?op=adiciona" method="post">
	        
	        	<div class="form-group">
				    <label for="nome">Nome</label>
				    <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome Pessoa ou Nome Empresa" required="required" autocomplete = "off" maxlength="150">
				 </div>
				 <div class="form-group">
				    <label for="cpfCnpj">CPF/CNPJ</label>
				    <input type="text" class="form-control" id="cpfCnpj" name="cpfCnpj" placeholder="Se for pessoa fisíca CPF, empresa CNPJ" required="required" autocomplete = "off" onkeyup = "mascara(this,cpfCnpjMask);" maxlength="18">
				 </div>
				 <div class="form-group">
				    <label for="data">Data</label>
				    <input type="text" class="form-control calendario" id="data" name="dataNasc" placeholder="Se for pessoa fisíca Nascimento, empresa Abertura" required="required" autocomplete = "off">
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
				 
				 <button type="submit" class="btn btn-primary">Salvar</button>           
	        </form>
	        
	        <hr />
	        
	       
	        	<c:if test="${empty lista}">
	        		<a href="sistema?op=lista" class="btn btn-default">Listar Clientes Cadastrados</a>
	    		</c:if>
	    		<c:if test="${not empty lista}">
	    		   <form action="sistema?op=listaFiltro" method="post" class="form-inline">
	    		    <div class="form-group">
					    <label for="pesNome">Nome</label>
					    <input type="text" class="form-control" id="pesNome" name="txt" placeholder="Pesquisar por nome" autocomplete = "off">					    
					 </div>
	        		 <input class="btn btn-info" type="submit" value="pesquisar" />
	        		</form>
	    		</c:if>
	           
		        <table class="table table-striped">
		        <c:forEach var="cli" items="${lista}" varStatus="id">
		            <tr>
		                <td>${cli.getNome()}</td> 
		                <td>${cli.getCpfCnpj()}</td>
		                <td>${cli.getDataNascFormatada()}</td>
		            </tr>
		        </c:forEach>
		    	</table>	    	
    	</div>
       </div>
      </div>
    </body>    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/sistema.js"></script>
</html>