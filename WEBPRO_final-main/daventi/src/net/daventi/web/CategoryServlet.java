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

import net.daventi.dao.CategoryDAO;
import net.daventi.model.Category;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	
	public void init() {
		categoryDAO = new CategoryDAO();
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
				deleteCategory(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listCategory(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("type");

		Category category = new Category();
		category.setId_category(request.getParameter("id_category"));
		category.setName_category(request.getParameter("name_category"));
		
		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				categoryDAO.insertCategory(category);
				break;
			case "UPDATE":
				categoryDAO.updateCategory(category);
				break;
			default:
				listCategory(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listCategory(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Category> listCategory = categoryDAO.selectAllCategory();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category/category-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id_category = request.getParameter("id_category");
		
		Category existingCategory = categoryDAO.selectCategory(id_category);
		request.setAttribute("category", existingCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category/category-form.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {

		String id_category = request.getParameter("id_category");
		categoryDAO.deleteCategory(id_category);
		response.sendRedirect("category");
	}
}