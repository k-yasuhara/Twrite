package controller;

import java.io.IOException;
import java.util.ArrayList;
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
import dto.Symptom;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));
		//メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);
		try {
			//クエリパラメータを取得
			Integer id = Integer.parseInt(request.getParameter("id"));

			//クエリパラメータからrecordsDBのデータを取得、リクエストに格納
			RecordDao reDao = DaoFactory.createRecordDao();
			RecordDB record = reDao.findById(id);
			request.setAttribute("record", record);

			//symptomsDBのデータを取得、リクエストに格納
			SymptomDao symDao = DaoFactory.creatSymptomDao();
			List<Integer> symptoms = new ArrayList<>();
			for (Symptom s : symDao.findById(id)) {
				symptoms.add(s.getSymptomsId());
			}

			//listがnullの場合リクエスト処理不要
			if (symptoms != null && symptoms.size() != 0) {
				request.setAttribute("selectedSymptoms", symptoms);
			}

			//セッションオブジェクト(loginId)を取得
			String loginId = (String) request.getSession().getAttribute("loginId");
			request.setAttribute("loginId", loginId);

			request.getRequestDispatcher("/WEB-INF/view/view.jsp")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
