

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Traitement
 */
@WebServlet("/Traitement")
public class Traitement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Traitement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
				response.sendRedirect("index.html");
			}
			Statement stmt = conn.createStatement();
			System.out.println(request.getParameter("idp"));
			String req= "SELECT qte FROM tp2_produit where id="+request.getParameter("idp");
			System.out.println(req);
			ResultSet rs = stmt.executeQuery(req);
			if (rs.next()){
				if (rs.getInt("qte") >= Integer.parseInt(request.getParameter("qte"))) {
					out.println("Stock Dispo");
				}
				else {
					out.println("Stock non dispo : \n Stock Actuelle :"+rs.getInt("qte"));
				}
			} else {
				out.println("Produit n'existe pas");
			}
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			HttpSession session = request.getSession();
			Connection conn = (Connection) session.getAttribute("conn");
			if (conn == null){
				response.sendRedirect("index.html");
			}
			String req= "insert into tp2_produit (nom,qte) values ( ? , ? )";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(req);
			stmt.setString(1, request.getParameter("nomp"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("qte")));
			System.out.println(req);
			stmt.execute();
			long add=stmt.getLastInsertID();
			System.out.println(add);
			if (add > 0){
					out.println("Ajouter");
			} else {
				out.println("Erreur d'ajout");
			}
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}
	}
}
