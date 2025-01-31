package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;
import dao.SymptomDao;
import dto.RecordDB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アカウント名表示
		request.setAttribute("loginName", request.getSession().getAttribute("loginName"));
		//メニュー欄切り替え用にアカウントの管理番号を取得
		Integer loginNum = (Integer) request.getSession().getAttribute("loginNum");
		request.setAttribute("loginNumber", loginNum);

		request.getRequestDispatcher("/WEB-INF/view/register.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//DB:records用の入力値の取得
		Integer registerId = (Integer) request.getSession().getAttribute("loginNum");
		String strStart = request.getParameter("start_at");
		strStart = strStart.replace("T", " ");
		String strEnd = request.getParameter("end_at");
		strEnd = strEnd.replace("T", " ");
		Integer patientPattern = Integer.parseInt(request.getParameter("patient_pattern"));
		String consContent = request.getParameter("consultation");
		String respContent = request.getParameter("response");
		Integer staffId = Integer.parseInt(request.getParameter("staff_id"));

		//バリデーション変数
		boolean isValid = true;

		//相談終了時間が開始時間より前なら
		Date startAt = null;
		Date endAt = null;
		try {
			startAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strStart);
			System.out.println(startAt);
			endAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strEnd);
			System.out.println(endAt);
		} catch (ParseException e) {
			throw new ServletException(e);
		}

		if (endAt.before(startAt)) {
			request.setAttribute("errorMsg", "※相談終了時間の修正：　相談開始時間よりも後の日時を入力してください※");
			isValid = false;
		}

		try {
			//バリデーションNG、フォワード
			if (!isValid) {
				request.setAttribute("start", strStart);
				request.setAttribute("end", strEnd);
				request.setAttribute("patientPattern", patientPattern);
				request.setAttribute("consContent", consContent);
				request.setAttribute("respContent", respContent);
				request.setAttribute("staffId", staffId);

				//チェックボックスの入力を配列に格納しlist型にしてリクエストに格納
				String[] selectedSymptoms = request.getParameterValues("symptoms");
				//未チェックなら処理不要
				if (selectedSymptoms != null) {
					request.setAttribute("selectedSymptoms", Arrays.asList(selectedSymptoms));
				}

				request.getRequestDispatcher("/WEB-INF/view/register.jsp")
						.forward(request, response);
				return;
			}

			//バリデーションOK
			//dtoにデータ格納
			RecordDB record = new RecordDB(null, registerId, null, null, startAt, endAt, patientPattern, consContent,
					respContent, null, staffId, null, null, null, null);

			//DBにデータ追加と自動採番IDを格納
			RecordDao recordDao = DaoFactory.createRecordDao();
			Integer recordsId = recordDao.insert(record);

			//symptomsへのinsert
			//DB:symptoms用の入力値の取得
			//チェックボックスの入力を配列に格納

			String[] strSymptomList = request.getParameterValues("symptoms");

			//入力ありの場合
			SymptomDao symptomDao = DaoFactory.creatSymptomDao();
			if (strSymptomList != null) {
				//insertしたレコードの管理番号をもとにsymptomsDBにデータを格納
				symptomDao.insert(recordsId, strSymptomList);
			} else {
				String options []= {"0"};
				symptomDao.insert(recordsId, options);
			}

			//topにリダイレクト
			response.sendRedirect("top");

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
