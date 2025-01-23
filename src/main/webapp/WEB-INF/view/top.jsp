<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Twrite トップページ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/top.style.css">
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@4.4.7/dist/chart.umd.min.js"></script>
</head>
<body>
	<!-- ヘッダー 常に上部固定 -->
	<header>
		<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary border">
			<div class="container-fluid">
				<a class="navbar-brand" href=""> <img
					src="<%=request.getContextPath()%>/images/Twriteshortlogo_transparent.png"
					alt="Logo" width="22" height="24"
					class="d-inline-block align-text-top"> 〇〇救急相談センター
				</a>
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link disabled"><c:out
								value="${loginName}" /></a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="logout">ログアウト</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- ヘッダー -->

	<div class="container-fluid">
		<div class="row">
						<!-- サイドバー 常に左側に固定 -->
			<nav id="sidebar" class="col-sm-2 d-sm-block bg-light sidebar border">
				<div class="position-sticky">
					<ul class="nav flex-column py-2">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="top"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-home">
									<path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
									<polyline points="9 22 9 12 15 12 15 22"></polyline></svg> <span
								class="ms-2">ダッシュボード</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="register">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-pencil-square"
									viewBox="0 0 16 16">
                                <path
										d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                <path fill-rule="evenodd"
										d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
                            </svg> <span class="ms-2">新規入力</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="viewlist">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">全ての記録</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="submitForm()"> <script>
								function submitForm() {
									const form = document.createElement('form');
									form.method = 'POST';
									form.action = 'viewlist'; // 送信先URLを指定
									form.style.display = 'none';

									// クエリパラメータをhidden要素で追加
									const params = {
										loginNumber : ${loginNumber}
									};

									for ( const key in params) {
										const input = document
												.createElement('input');
										input.type = 'hidden';
										input.name = key;
										input.value = params[key];
										form.appendChild(input);
									}

									document.body.appendChild(form);
									form.submit();
								}
							</script> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">未承認</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="submitFormApproval(2)"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">差し戻し</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="submitFormApproval(1)"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">承認済み</span>
						</a></li>
						<script>
								function submitFormApproval(i) {
									const form = document.createElement('form');
									form.method = 'POST';
									form.action = 'viewlist'; // 送信先URLを指定
									form.style.display = 'none';

									// クエリパラメータをhidden要素で追加
									const params = {
										loginNumber : ${loginNumber},
										approval : i
									};

									for ( const key in params) {
										const input = document
												.createElement('input');
										input.type = 'hidden';
										input.name = key;
										input.value = params[key];
										form.appendChild(input);
									}

									document.body.appendChild(form);
									form.submit();
								}
						</script>
					</ul>
				</div>
			</nav>
			<!-- end of サイドバー -->
			<!-- main -->
			<main class="col-10 col-sm px-4 py-4">
				<!-- topic -->
				<h1 class="h2 fw-bold">◆ダッシュボード</h1>
				<!-- ダッシュボード カード挿入 -->
				<div class="d-flex flex-row mb-3">
					<!-- 相談件数 numberOfConsulutations -->
					<div
						class="p-2 m-2 text-center fs-4 p-3 fw-bold shadow border-end border-bottom"
						style="background-color: #f4f4f4;">
						本日の相談件数/前日比
						<p class="fs-1 m-2 numberOfConsultations">
							${cToday} 件<br>/
							<c:if test="${(cToday - cYesterday)>0}">
							+
							</c:if>
							${cToday - cYesterday}
						</p>
					</div>
					<!-- 相談件数 -->
					<!-- 相談傾向 -->
					<div
						class="p-2 m-2 mx-5 text-center fs-4 p-3 fw-bold shadow border-end border-bottom "
						style="background-color: #f4f4f4; width: 500px;">
						相談傾向
						<div class="row">
							<c:choose>
								<c:when test="${empty countSymptomMsg}">
									<div class="col-6">
										<c:forEach var="i" begin="0"
											end="${fn:length(countSymptomToday)-1}">
											<p class="fs-3 m-2 text-start">${i+1}.
												<c:out value="${countSymptomToday[i].symptomsName}" />
											</p>
										</c:forEach>
									</div>
									<div class="col-6">
										<c:forEach var="i" begin="0"
											end="${fn:length(countSymptomToday)-1}">
											<p class="fs-3 m-2 text-end">
												( ${countSymptomToday[i].countSymptom} 件/
												<c:if
													test="${(countSymptomToday[i].countSymptom - countSymptomYesterday[i].countSymptom) > 0}">
												+
												</c:if>
												${countSymptomToday[i].countSymptom - countSymptomYesterday[i].countSymptom})
											</p>
										</c:forEach>
									</div>
								</c:when>
								<c:when test="${not empty countSymptomMsg}">
									<p>
										<c:out value="${countSymptomMsg}" />
									</p>
								</c:when>
							</c:choose>
						</div>
					</div>
					<!-- 相談傾向 -->
				</div>

				<div class="d-flex flex-row mb-3">
					<div
						class="p-2 m-2 text-center fs-4 p-3 fw-bold shadow border-end border-bottom"
						style="background-color: #f4f4f4;">
						相談件数
						<div style="width: 500px;">
							<canvas id="chart"></canvas>
						</div>
						<script>
							var ctx = document.getElementById("chart");
							var myLineChart = new Chart(
									ctx,
									{
										// グラフの種類：折れ線グラフを指定
										type : 'line',
										data : {
											// x軸の各メモリ
											labels : [ '月曜日', '火曜日', '水曜日',
													'木曜日', '金曜日', '土曜日', '日曜日' ],
											datasets : [
													{
														label : '今週の相談件数',
														data : [ 
															<c:forEach items="${countRecordThisWeek}" var="thisWeek">
															"${thisWeek}",
															</c:forEach>
															 ],
														borderColor : "#ec4343",
														backgroundColor : "#00000000"
													},
													{
														label : '先週の相談件数',
														data : [ 
															<c:forEach items="${countRecordLastWeek}" var="lastWeek">
															"${lastWeek}",
															</c:forEach>
															
															 ],
														borderColor : "#2260ea",
														backgroundColor : "#00000000"
													} ],
										},
									});
						</script>
					</div>
				</div>
				<!-- ダッシュボード カード -->
			</main>
			<!-- main -->
		</div>
	</div>
	<footer>
		<p>&copy; 2024 Twrite. All rights reserved.</p>
	</footer>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>

</body>
</html>
