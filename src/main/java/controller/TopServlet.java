package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;
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

		//相談件数
		try {
			RecordDao reDao = DaoFactory.createRecordDao();
			//今日を0、昨日を-1とする
			request.setAttribute("cToday", reDao.findAll(0).size());
			request.setAttribute("cYesterday", reDao.findAll(-1).size());
		} catch (Exception e) {
			throw new ServletException(e);
		}

		//相談傾向カードのデータ取得
		try {
			SymptomDao sympDao = DaoFactory.creatSymptomDao();
			//今日を0、昨日を-1とする
			request.setAttribute("countSymptomToday", sympDao.countSymptom(0));
			request.setAttribute("countSymptomYesterday", sympDao.countSymptom(-1));
		} catch (Exception e) {
			throw new ServletException(e);
		}

		//相談件数グラフ先週比
		int[] countRecordLast = new int[7];
		int[] countRecordThis = new int[7];
		try {
			RecordDao reDao = DaoFactory.createRecordDao();
			//先週（月曜日～日曜日）7～1
			for (int i = 7; i > 0; --i) {
				countRecordLast[7 - i] = reDao.findAllWeek(i).size();
			}
			request.setAttribute("countRecordLastWeek", countRecordLast);

			//今週（月曜日～日曜日）0～-6
			for (int i = 0; i > -6; --i) {
				countRecordThis[-(i)] = reDao.findAllWeek(i).size();
			}
			request.setAttribute("countRecordThisWeek", countRecordThis);

		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/view/top.jsp")
				.forward(request, response);
	}

}
