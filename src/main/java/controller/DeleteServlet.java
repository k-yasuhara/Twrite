package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
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

			request.setAttribute("recordList", recorddao.findAll());
			request.setAttribute("title", "削除");

			if (request.getParameter("id") == null) {
				//一覧画面をフォワード
				request.getRequestDispatcher("/WEB-INF/view/viewlist.jsp")
						.forward(request, response);
				return;
			} else {
				Integer id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("record", recorddao.findById(id));
				request.getRequestDispatcher("/WEB-INF/view/delete.jsp")
						.forward(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RecordDao recorddao = DaoFactory.createRecordDao();
			recorddao.delete(Integer.parseInt(request.getParameter("id")));

		} catch (Exception e) {
			throw new ServletException(e);
		}
		//リダイレクト
		request.getRequestDispatcher("/WEB-INF/view/deleteDone.jsp")
				.forward(request, response);

	}

}
