package com.shinhan.controller4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jsp/order.go")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String menuName = request.getParameter("lunch");
		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println(menuName + " ==> " + count);
		
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<String, Integer>();
			cart.put(menuName, count);
		} else {
			if(cart.containsKey(menuName)) {
				cart.put(menuName, cart.get(menuName) + count);
			}else {
				cart.put(menuName, count);
			}
		}
		session.setAttribute("cart", cart);
		
//		response.sendRedirect("order.go");
		request.getRequestDispatcher("orderResult.jsp").forward(request, response);
	}

}
