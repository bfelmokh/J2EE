

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Produit
 */
@WebServlet("/Produit")
public class Produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Produit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			HttpSession session = request.getSession();
			conn =(Connection) session.getAttribute("conn");
			if (session.getAttribute("nom")!=null){
			String nomp = request.getParameter("nomp");
			String qte = request.getParameter("qte");
			//Statement stmt = conn.createStatement();
			//String req= "SELECT count(*) FROM user where login=\""+nomp+"\" and mdp=\""+qte+"\"";
			String req= "insert into produit (nom,qte) values ( ? , ? )";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(req);
			stmt.setString(1, request.getParameter("nomp"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("qte")));
			ResultSet rs = stmt.executeQuery(req);
			rs.next();
			System.out.println(rs.getInt(1));
			if (rs.getInt(1)==1) {
				session.setAttribute("nomp", nomp);
				System.out.print(nomp +"Added !");
				response.sendRedirect("ajout.jsp");
			}
			}
			else {
				response.sendRedirect("index.jsp");
			}
			
		}
		catch (Exception c) { 
			System.out.println ("problème SQL"+c); 
		}
	}

}
