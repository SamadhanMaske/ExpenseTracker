package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.db.HibernateUtil;
import com.entity.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("fullName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String about = req.getParameter("about");
		
		User u = new User(name, email, password, about);
		
		UserDAO dao = new UserDAO(HibernateUtil.getSessionFactory());
		boolean f = dao.saveuser(u);
		
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("msg", "Registration successful");
			resp.sendRedirect("register.jsp");
			//System.out.println("Register sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
			resp.sendRedirect("register.jsp");

			//System.out.println("Something wrong on server");
		}
	}

}
