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

		//他アカウントが作成したレコードを非表示、メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);

		//modal用にクエリパラメータを取得
		//modal==nullなら全ての記録を表示
		//modal!=nullならmodal表示、未承認一覧を表示
		try {
			RecordDao recorddao = DaoFactory.createRecordDao();
			if (request.getParameter("Modal") == null) {
				request.setAttribute("recordList", recorddao.findAll());
				request.setAttribute("title", "全ての記録");
			} else {
				if (request.getParameter("Modal").equals("permit")) {
					request.setAttribute("approvalMsg", "承認しました");
				} else if (request.getParameter("Modal").equals("remand")) {
					request.setAttribute("approvalMsg", "差し戻しました");
				}
				//opアカウントの場合opアカウントで作成したレコードのみ表示
				request.setAttribute("recordList", recorddao.findAll(loginNum));
				request.setAttribute("title", "未承認");
			}
			//一覧画面をフォワード
			request.getRequestDispatcher("/WEB-INF/view/viewlist.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示（ヘッダー用）
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));

		//他アカウントが作成したレコードを非表示、メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);

		try {
			RecordDao recorddao = DaoFactory.createRecordDao();

			Integer approval = null;
			if (request.getParameter("approval") != null) {
				approval = Integer.parseInt(request.getParameter("approval"));
			}
			//差し戻し・承認済みの記録
			if (approval != null) {
				request.setAttribute("recordList", recorddao.findAll(loginNum, approval));
				if (approval == 1) {
					request.setAttribute("title", "承認済み");
				} else if (approval == 2) {
					request.setAttribute("title", "差し戻し");
				}
				//未承認の記録
			} else {
				//opアカウントの場合opアカウントで作成したレコードのみ表示
				request.setAttribute("recordList", recorddao.findAll(loginNum));
				request.setAttribute("title", "未承認");
			}

			//フォワード
			request.getRequestDispatcher("/WEB-INF/view/viewlist.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
