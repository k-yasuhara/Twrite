package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;

@WebServlet("/remand")
public class RemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//クエリパラメータでレコード管理番号を取得
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		try {
			//recordsDBのapproval_statusを更新
			RecordDao recorddao = DaoFactory.createRecordDao();
			recorddao.remand(id);
			
			response.sendRedirect("viewlist?Modal=remand");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
