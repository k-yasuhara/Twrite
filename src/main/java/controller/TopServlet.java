package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.SymptomDao;

@WebServlet("/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));
		//メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);

		try {
			SymptomDao sympDao = DaoFactory.creatSymptomDao();
			//今日を0、昨日を-1とする
			request.setAttribute("countSympToday", sympDao.countSymptom(0));
			request.setAttribute("countSympYesterday", sympDao.countSymptom(-1));

		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/view/top.jsp")
				.forward(request, response);
	}

}
