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
import dao.SymptomDao;
import dto.RecordDB;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//クエリパラメータを取得
			Integer id = Integer.parseInt(request.getParameter("id"));

			//recordsDBのデータを取得、リクエストに格納
			RecordDao reDao = DaoFactory.createRecordDao();
			RecordDB record = reDao.findById(id);
			request.setAttribute("record", record);

			SymptomDao symDao = DaoFactory.creatSymptomDao();
			List<String> symptoms = symDao.findById(id);

			if (symptoms != null && symptoms.size() != 0) {
				request.setAttribute("selectedSymptoms", symptoms);
			}

			request.getRequestDispatcher("/WEB-INF/view/view.jsp")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
