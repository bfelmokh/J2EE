

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
		HttpSession session = request.getSession();
		out.println("Hello "+session.getAttribute("login")+",<br>");
		try{
			
			Connection conn = (Connection) session.getAttribute("conn");
			if (conn == null){
				response.sendRedirect("index.html");
			}
			Statement stmt = conn.createStatement();
			System.out.println(request.getParameter("idp"));
			String req= "SELECT * FROM tp2_produit where id="+request.getParameter("idp")+" and qte >= "+request.getParameter("qte");
			System.out.println(req);
			ResultSet rs = stmt.executeQuery(req);
			if (rs.next()){
				out.println("Stock Dispo : <br> Nouveau Stock :"+(rs.getInt("qte") - Integer.parseInt(request.getParameter("qte"))));
				req= "update tp2_produit set qte=? where id=? " ;
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(req);
				pstmt.setInt(1,rs.getInt("qte")-Integer.parseInt(request.getParameter("qte")) );
				pstmt.setInt(2,Integer.parseInt(request.getParameter("idp")));
				//pstmt.setInt(2, );
				System.out.println(req);
				int rows = pstmt.executeUpdate();
				System.out.println(rows);
				if (rows > 0){
						out.println(" <br> Produit "+rs.getString("nom")+" mis à jour");
				} else {
					out.println("<br> Erreur de mis à jour");
				}
			}
			else {
				out.println("<br> Quantité ou Produit n'existe pas");
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
		HttpSession session = request.getSession();
		out.println("Hello "+session.getAttribute("login")+",<br>");
		try{
			
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
					out.println("<br>Produit "+ request.getParameter("nomp")+" Ajouté");
			} else {
				out.println("<br>Erreur d'ajout");
			}
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}
	}
}
