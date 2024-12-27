package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.DaoFactory;
import dto.Admin;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("loginId", null);
		request.getRequestDispatcher("/WEB-INF/view/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String loginId = request.getParameter("loginId");
			String loginPass = request.getParameter("loginPass");

			AdminDao adminDao = DaoFactory.createAdminDao();
			Admin admin = adminDao.findByLoginIdAndPass(loginId, loginPass);

			if (admin != null) {
				request.getSession().setAttribute("loginId", admin.getLoginId());
				response.sendRedirect("top");
			//ログイン失敗
			} else {
				request.setAttribute("loginId", loginId);
				request.setAttribute("errorMsg", true);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
