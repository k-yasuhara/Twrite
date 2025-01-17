package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;

@WebServlet("/permit")
public class PermitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));
		//クエリパラメータを取得
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		try {
			RecordDao recorddao = DaoFactory.createRecordDao();
			recorddao.permit(id);
			
			response.sendRedirect("viewlist?Modal=permit");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
