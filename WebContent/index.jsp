<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<meta charset="ISO-8859-1">
	<title>Cliente</title>
    <body>
        <form action="sistema?op=adiciona" method="post">
            Nome: <input type="text" name="nome" /><br />
            CPF/CNPJ: <input type="text" name="cpfCnpj" /><br />
            Data Nascimento: <input type="text" name="dataNasc" /><br />

            <input type="submit" value="Gravar" />
        </form>
        
        <hr />
        
        <form action="sistema?op=listaFiltro" method="post">
        	<c:if test="${empty lista}">
        		<a href="sistema?op=lista">Listar</a>
    		</c:if>
    		<c:if test="${not empty lista}">
        		Nome: <input type="text" name="txt" /> <input type="submit" value="pesquisar" />
    		</c:if>
           
	        <table border="1">
	        <c:forEach var="cli" items="${lista}" varStatus="id">
	            <tr bgcolor="#${id.count % 2 == 0 ? 'aacccc' : 'ffffff' }" >
	                <td>${cli.getNome()}</td> 
	                <td>${cli.getCpfCnpj()}</td>
	                <td>${cli.getDataNascFormatada()}</td>
	            </tr>
	        </c:forEach>
	    	</table>
    	</form>
    </body>
</html>