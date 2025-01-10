package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.RecordDao;
import dto.RecordDB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/register.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//入力値の取得
		String registerId = (String) request.getSession().getAttribute("loginId");
		String strStart = request.getParameter("start_at");
		strStart = strStart.replace("T", " ");
		String strEnd = request.getParameter("end_at");
		strEnd = strEnd.replace("T", " ");
		Integer patientPattern = Integer.parseInt(request.getParameter("patient_pattern"));
		String consContent = request.getParameter("consultation");
		String respContent = request.getParameter("response");
		Integer staffId = Integer.parseInt(request.getParameter("staff_id"));
		//バリデーション
		boolean isValid = true;

		//相談終了時間が開始時間より前なら
		Date startAt = null;
		Date endAt = null;
		try {
			startAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strStart);
			endAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (endAt.before(startAt)) {
			request.setAttribute("errorMsg", "※相談終了時間の修正：　相談開始時間よりも後の日時を入力してください※");
			isValid = false;
		}

		try {
			//バリデーションNG、フォワード
			if (!isValid) {
//				System.out.println(staffId);
//				System.out.println(patientPattern);
				request.setAttribute("start", strStart);
				request.setAttribute("end", strEnd);
				request.setAttribute("patient", patientPattern);
				request.setAttribute("consContent", consContent);
				request.setAttribute("respContent", respContent);
				request.setAttribute("staff", staffId);

				request.getRequestDispatcher("/WEB-INF/view/register.jsp")
						.forward(request, response);
				return;
			}

			//バリデーションOK
			//dtoにデータ格納
			RecordDB record = new RecordDB(null, registerId, null, null, startAt, endAt, patientPattern, consContent,
					respContent, null, staffId);

			//DBにデータ追加
			RecordDao recordDao = DaoFactory.createRecordDao();
			recordDao.insert(record);
			response.sendRedirect("top");

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
