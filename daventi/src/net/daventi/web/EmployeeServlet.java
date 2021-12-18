package net.daventi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daventi.dao.EmployeeDAO;
import net.daventi.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDAO employeeDAO = new EmployeeDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher dispatcher = request.getRequestDispatcher("authentication/authentication-register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_employee = request.getParameter("id_employee");
		String name_employee = request.getParameter("name_employee");
		String email_employee = request.getParameter("email_employee");
		String password_employee = request.getParameter("password_employee");
		String gender = request.getParameter("gender");
		String phone_num_employee = request.getParameter("phone_num_employee");
		String role = request.getParameter("role");
		
		Employee employee = new Employee();
		
		employee.setId_employee(id_employee);
		employee.setName_employee(name_employee);
		employee.setEmail_employee(email_employee);
		employee.setPassword_employee(password_employee);
		employee.setGender(gender);
		employee.setPhone_num_employee(phone_num_employee);
		employee.setRole(role);
		
		try {
			employeeDAO.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("authentication/authentication-login.jsp");
		dispatcher.forward(request, response);
	}

}
