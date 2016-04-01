package com.aula_04_arquidesis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aula_04_arquidesis.dao.Extrato;
import com.aula_04_arquidesis.model.Conta;
import com.aula_04_arquidesis.model.Movimento;

/**
 * Servlet implementation class ExtratoController
 */
@WebServlet("/extrato")
public class ExtratoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtratoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Conta conta = (Conta) session.getAttribute("conta");
		
		Extrato extrato = new Extrato();
		
		List<Movimento> movimentos = extrato.consultarExtrato(conta);
		RequestDispatcher rd = request.getRequestDispatcher("/views/atm/extrato.jsp");

		request.setAttribute("movimentos", movimentos);
		
		rd.forward(request, response);
		
	}

}
