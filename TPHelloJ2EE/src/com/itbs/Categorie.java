package com.itbs;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class Categorie
 */
@WebServlet({ "/Categorie", "/Cat" })
public class Categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String driver= config.getInitParameter("db-driver");
		String url= config.getInitParameter("db-url");
		String login= config.getInitParameter("db-login");
		String mdp= config.getInitParameter("db-passwd");
		System.out.println(url);
		try {
			Class.forName (driver);
		
		conn = DriverManager.getConnection (url,login, mdp);
		}catch (Exception c){
			System.out.println("Problème SQL" + c);
		}
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
			session.setAttribute("conn", conn);
			Statement stmt = conn.createStatement();
			String req= "SELECT * FROM categorie";
			ResultSet rs = stmt.executeQuery(req);
			while(rs.next())
			out.println("<a href=\"./produit?id="+rs.getString("id")+"\">"+rs.getString("nom")+"</a></br>");
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}

		
	}

}
