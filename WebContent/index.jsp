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
				 
				 <button type="submit" class="btn btn-primary">Salvar</button>           
	        </form>
	        
	        <hr />
	        
	       
	        	<c:if test="${empty lista}">
	        		<a href="sistema?op=lista" class="btn btn-default">Listar Clientes Cadastrados</a>
	    		</c:if>
	    		<c:if test="${not empty lista}">
	    		   <form action="sistema?op=listaFiltro" method="post" class="form-inline">
	    		    <div class="form-group">
					    <label for="pesNome">DDD</label>
					    <input type="text" class="form-control ddd" id="pesNome" name="txt" placeholder="Pesquisar por DDD" autocomplete = "off" maxlength="2">					    
					 </div>
	        		 <input class="btn btn-info" type="submit" value="pesquisar" />
	        		</form>
	    		</c:if>
	           
		        <table class="table table-striped">
		        	<tr>
		            	<th>Nome</th>
		            	<th>CPF/CNPJ</th>
		            	<th>Data</th>
		            	<th>Telefones</th>
		            	<th>Endereços</th>
		            </tr>
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
    </body>    
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/jquery.mask.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/sistema.js"></script>
    <script type="text/javascript">
    $(function () {
	    $('#cpfCnpj').blur(function () {	    	
	    	var valor = $(this).val();
	    	if (valor == '') {
	            return;
	        }
	        if (valor.length == 14 || valor.length == 18) {
	        	existeCadastro(valor, "cpfCnpj");
	        }else{
	        	$("#cpfCnpj").attr("data-toggle", "popover");
                $("#cpfCnpj").attr("data-placement", "bottom");
                $("#cpfCnpj").attr("data-content", "A quantidade de numeros está errada.");
                $("#cpfCnpj").popover('show');
                $("#cpfCnpj").val("");
	        }	                
	        
	    });
	    $('#data').change(function () {	
	    	
	        if ($(this).val() == '') {	        	
	            return;
	        }
	        if(!validaDate($(this).val())){
	        	$("#data").val("");
	        	return;
	        }
	        var msg = "Só pode se cadastrar empresas com mais de 3 anos!"
	        var maior = 3;
	        var cpfCnpj = $('#cpfCnpj').val();
	        if(cpfCnpj.length <= 14) { 
	        	maior = 18;
	        	msg = "Só pode se cadastrar pessoas com mais de 18 anos!"
	        }
	        var dtNasc = $(this).val().split('/');
	        var vlIdade = idade(new Date(dtNasc[2], dtNasc[1], dtNasc[0]))
	        if(vlIdade < maior){
	        		$("#data").attr("data-toggle", "popover");
	                $("#data").attr("data-placement", "bottom");
	                $("#data").attr("data-content", msg);
	                $("#data").popover('show');
	                $("#data").val("");
	        }
	        else{
        		$("#data").attr("data-toggle", "popover");
                $("#data").popover('hide');
        	}	        
	    });	    
    });
    </script>
</html>