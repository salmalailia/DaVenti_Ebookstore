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

import net.daventi.dao.MethodDAO;
import net.daventi.model.Method;

/**
 * Servlet implementation class MethodServlet
 */
@WebServlet("/method")
public class MethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MethodDAO methodDAO;
	
	public void init() {
		methodDAO = new MethodDAO();
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
				deleteMethod(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listMethod(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("type");

		Method method = new Method();
		method.setId_method(request.getParameter("id_method"));
		method.setName_method(request.getParameter("name_method"));
		method.setDest_bank(request.getParameter("dest_bank"));

		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				methodDAO.insertMethod(method);
				break;
			case "UPDATE":
				methodDAO.updateMethod(method);
				break;
			default:
				listMethod(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listMethod(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void listMethod(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Method> listMethod = methodDAO.selectAllMethod();
		request.setAttribute("listMethod", listMethod);
		RequestDispatcher dispatcher = request.getRequestDispatcher("method/method-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id_method = request.getParameter("id_method");
		
		Method existingMethod = methodDAO.selectMethod(id_method);
		System.out.println(existingMethod.getId_method());
		System.out.println(existingMethod.getName_method());
		System.out.println(existingMethod.getDest_bank());

		request.setAttribute("method", existingMethod);
		RequestDispatcher dispatcher = request.getRequestDispatcher("method/method-form.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteMethod(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {

		String id_method = request.getParameter("id_method");
		methodDAO.deleteMethod(id_method);
		response.sendRedirect("method");
	}
}
