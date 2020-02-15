package com.itbs;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class Product
 */
@WebServlet({ "/Product", "/Prod", "/produit" })
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			HttpSession session = request.getSession();
			Connection conn = (Connection) session.getAttribute("conn");
			if (conn == null){
				response.sendRedirect(request.getContextPath()+"/Categorie");
			}
			Statement stmt = conn.createStatement();
			System.out.println(request.getParameter("id"));
			String req= "SELECT * FROM Produit where id_cat="+request.getParameter("id");
			System.out.println(req);
			ResultSet rs = stmt.executeQuery(req);
			out.println("<ul>");
			while(rs.next()){
			out.println("<li>"+rs.getString("nom")+"</li>");
			}
			out.println("</ul>");
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}
	}

}
