package net.daventi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daventi.dao.AuthorDAO;
import net.daventi.model.Author;

/**
 * Servlet implementation class AuthorServlet
 */
@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthorDAO authorDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		authorDao = new AuthorDAO();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("type");

		Author author = new Author();
		author.setId_author(request.getParameter("id_author"));
		author.setName_author(request.getParameter("name_author"));
		
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				authorDao.insertAuthor(author);
				break;
			case "UPDATE":
				authorDao.updateAuthor(author);
				break;
			default:
				listAuthor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listAuthor(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "DELETE":
				deleteAuthor(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listAuthor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	// edit author
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		String id_author = request.getParameter("id_author");
		
		Author existingAuthor;
		try {
			existingAuthor = authorDao.selectAuthor(id_author);
			RequestDispatcher dispatcher = request.getRequestDispatcher("author/author-form.jsp");
			request.setAttribute("author", existingAuthor);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete author
	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_author = request.getParameter("id_author");
		try {
			authorDao.deleteAuthor(id_author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("author");
	}
	
	// default
	private void listAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			List<Author> listAuthor = authorDao.selectAllAuthor();
			request.setAttribute("listAuthor", listAuthor);;
			RequestDispatcher dispatcher = request.getRequestDispatcher("author/author-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
