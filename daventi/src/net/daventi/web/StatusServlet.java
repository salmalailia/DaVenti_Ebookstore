package net.daventi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daventi.dao.StatusDAO;
import net.daventi.model.Status;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private StatusDAO statusDAO;
	
	public void init() {
		statusDAO = new StatusDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "DELETE":
				deleteStatus(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listStatus(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("type");

		Status status = new Status();
		status.setId_status(request.getParameter("id_status"));
		status.setName_status(request.getParameter("name_status"));
		
		System.out.println(action);
		System.out.println(request.getParameter("id_status"));
		System.out.println(request.getParameter("name_status"));
		
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				statusDAO.insertStatus(status);
				break;
			case "UPDATE":
				statusDAO.updateStatus(status);
				break;
			default:
				listStatus(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listStatus(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void listStatus(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Status> listStatus = statusDAO.selectAllStatus();
		request.setAttribute("listStatus", listStatus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("status/status-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id_status = request.getParameter("id_status");
		
		Status existingStatus = statusDAO.selectStatus(id_status);
		request.setAttribute("status", existingStatus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("status/status-form.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteStatus(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {

		String id_status = request.getParameter("id_status");
		statusDAO.deleteStatus(id_status);
		response.sendRedirect("status");
	}
}
