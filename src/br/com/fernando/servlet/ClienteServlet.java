package br.com.fernando.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fernando.dao.ClienteDao;
import br.com.fernando.model.Cliente;

@WebServlet("/sistema")
public class ClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("op");
        ClienteDao dao = new ClienteDao();

        if (acao.equals("adiciona")) {
        	Cliente cli = new Cliente(request.getParameter("nome"), request.getParameter("cpfCnpj"), request.getParameter("dataNasc"));
            dao.salvar(cli);            
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
    }
}