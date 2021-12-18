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

import net.daventi.dao.PublisherDAO;
import net.daventi.model.Publisher;

/**
 * Servlet implementation class PublisherServlet
 */
@WebServlet("/publisher")
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PublisherDAO publisherDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		publisherDao = new PublisherDAO();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("type");

		Publisher publisher = new Publisher();
		publisher.setId_publisher(request.getParameter("id_publisher"));
		publisher.setName_publisher(request.getParameter("name_publisher"));
		publisher.setEmail_publisher(request.getParameter("email_publisher"));
		
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				publisherDao.insertPublisher(publisher);
				break;
			case "UPDATE":
				publisherDao.updatePublisher(publisher);
				break;
			default:
				listPublisher(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listPublisher(request, response);
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
				deletePublisher(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listPublisher(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	// edit publisher
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		String id_publisher = request.getParameter("id_publisher");
		
		Publisher existingPublisher;
		try {
			existingPublisher = publisherDao.selectPublisher(id_publisher);
			RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/publisher-form.jsp");
			request.setAttribute("publisher", existingPublisher);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete publisher
	private void deletePublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_publisher = request.getParameter("id_publisher");
		try {
			publisherDao.deletePublisher(id_publisher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("publisher");
	}
	
	// default
	private void listPublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			List<Publisher> listPublisher = publisherDao.selectAllPublisher();
			request.setAttribute("listPublisher", listPublisher);
			RequestDispatcher dispatcher = request.getRequestDispatcher("publisher/publisher-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
