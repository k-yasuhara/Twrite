package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;

@WebServlet("/viewlist")
public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示（ヘッダー用）
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));
		//メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);

		//modal用のクエリパラメータを取得、リクエスト処理
		if (request.getParameter("Modal") != null && request.getParameter("Modal").equals("permit")) {
			request.setAttribute("approvalMsg", "承認しました");
		} else if (request.getParameter("Modal") != null && request.getParameter("Modal").equals("remand")) {
			request.setAttribute("approvalMsg", "差し戻しました");
		}

		try {
			RecordDao recorddao = DaoFactory.createRecordDao();
			//クエリパラメータを取得
			String loginNumber = request.getParameter("loginNumber");

			Integer approval = null;
			if (request.getParameter("approval") != null) {
				approval = Integer.parseInt(request.getParameter("approval"));
			}
				
				//全ての記録：パラメータ（loginNumber）= null  
			if (loginNumber == null) {
				request.setAttribute("recordList", recorddao.findAll());

				//未承認/差し戻し/承認済みの記録：パラメータ（loginNumber）!= null
				//差し戻し・承認済みの記録
			} else if (loginNumber != null && approval != null) {
				request.setAttribute("recordList", recorddao.findAll(loginNum, approval));

				//未承認の記録
			} else if (loginNumber != null && approval == null) {
				request.setAttribute("recordList", recorddao.findAll(loginNum));

			}

			request.getRequestDispatcher("/WEB-INF/view/viewlist.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
