

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String driver= config.getInitParameter("db-driver");
		String url= config.getInitParameter("db-url");
		String login= config.getInitParameter("db-login");
		String mdp= config.getInitParameter("db-passwd");
		try {
			Class.forName (driver);
		
		conn = DriverManager.getConnection (url,login, mdp);
		}catch (Exception c){
			System.out.println("Probl�me SQL" + c);
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
			if (session.getAttribute("nom")!=null){
				response.sendRedirect("menu.jsp");
			}
			String nom = request.getParameter("nom");
			String mdp = request.getParameter("mdp");
			Statement stmt = conn.createStatement();
			String req= "SELECT count(*) FROM user where login=\""+nom+"\" and mdp=\""+mdp+"\"";
			//System.out.println(req);
			ResultSet rs = stmt.executeQuery(req);
			rs.next();
			System.out.println(rs.getInt(1));
			if (rs.getInt(1)==1) {
				session.setAttribute("nom", nom);
				System.out.print(nom +"Logged !");
				//out.print("<a href=\"ajout.html\"> Ajouter </a> <br>");
				//out.print("<a href=\"chercher.html\"> Chercher </a> <br>");
				response.sendRedirect("menu.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
			
		}
		catch (Exception c) { 
			System.out.println ("probl�me SQL"+c); 
		}

	}

}
