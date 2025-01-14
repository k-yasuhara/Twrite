package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;
import dto.RecordDB;


@WebServlet("/viewlist")
public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			RecordDao recorddao = DaoFactory.createRecordDao();
			List<RecordDB> recordList = recorddao.findAll();
			request.setAttribute("recordList", recordList);
			
			request.getRequestDispatcher("/WEB-INF/view/viewlist.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
