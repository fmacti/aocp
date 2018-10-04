package br.com.fernando.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fernando.dao.ClienteDao;
import br.com.fernando.dao.ContatoDao;
import br.com.fernando.dao.EnderecoDao;
import br.com.fernando.model.Cliente;
import br.com.fernando.model.Contato;
import br.com.fernando.model.Endereco;

@WebServlet("/sistema")
public class ClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("op");
        ClienteDao dao = new ClienteDao();

        if (acao.equals("adiciona")) {
        	
        	Cliente cli = new Cliente(request.getParameter("nome"), request.getParameter("cpfCnpj"), request.getParameter("dataNasc"));
            int clienteId = dao.salvar(cli);
            
            salvaContatoEndereco(request, clienteId);
            
            request.setAttribute("lista", dao.getLista());
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } 
        else if (acao.equals("lista")) {
        	request.setAttribute("lista", dao.getLista());
        	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        	rd.forward(request, response);
        } 
        else if (acao.equals("listaFiltro")) {
        	request.setAttribute("lista",dao.getListaFone(request.getParameter("txt")));
        	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        	rd.forward(request, response);
        }
        else if (acao.equals("jaCadastrado")) {
        	ClienteDao cDao = new ClienteDao();
        	String vl = cDao.getCastrado(request.getParameter("valor"));
        	response.setContentType("text/plain");
        	response.getWriter().write(vl);
        }
    }
	
	private static void salvaContatoEndereco(HttpServletRequest request, int clienteId) {
		String[] fixos = request.getParameterValues("Fixo");
    	String[] celulares = request.getParameterValues("Celular");
    	String[] logradouros = request.getParameterValues("Logradouro");
    	String[] cidades = request.getParameterValues("Cidade");
    	String[] ufs = request.getParameterValues("Uf");
    	
    	if(fixos != null) {
    		ContatoDao cDao = new ContatoDao();
        	for (int i = 0; i < fixos.length; i++) {
    			Contato c = new Contato(fixos[i], celulares[i], clienteId);
    			cDao.salvar(c);
    		}
    	}
    	
    	if(logradouros != null) {
    		EnderecoDao eDao = new EnderecoDao();
        	for (int i = 0; i < logradouros.length; i++) {
    			Endereco e = new Endereco(logradouros[i], cidades[i], ufs[i], clienteId);
    			eDao.salvar(e);
    		}
    	}
	}
}