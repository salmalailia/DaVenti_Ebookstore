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

import net.daventi.dao.PromoDAO;
import net.daventi.model.Promo;
import java.sql.Date;

/**
 * Servlet implementation class PromoServlet
 */
@WebServlet("/promo")
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PromoDAO promoDAO;
	
	public void init() {
		promoDAO = new PromoDAO();
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
				deletePromo(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			default:
				listPromo(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("type");

		Promo promo = new Promo();
		promo.setId_promo(request.getParameter("id_promo"));
		promo.setName_promo(request.getParameter("name_promo"));
		promo.setDiscount(Float.valueOf(request.getParameter("discount")));
		promo.setStart_discount(request.getParameter("start_discount"));
		promo.setEnd_discount(request.getParameter("end_discount"));
		
		System.out.println(promo.getStart_discount());

		if(action == null) {
			action = "list";
		}
		try {
			switch (action) {
			case "INSERT":
				promoDAO.insertPromo(promo);
				break;
			case "UPDATE":
				promoDAO.updatePromo(promo);
				break;
			default:
				listPromo(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		try {
			listPromo(request, response);
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void listPromo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Promo> listPromo = promoDAO.selectAllPromo();
		request.setAttribute("listPromo", listPromo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("promo/promo-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id_promo = request.getParameter("id_promo");
		
		Promo existingPromo = promoDAO.selectPromo(id_promo);
		request.setAttribute("promo", existingPromo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("promo/promo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void deletePromo(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {

		String id_promo = request.getParameter("id_promo");
		promoDAO.deletePromo(id_promo);
		response.sendRedirect("promo");
	}

}
