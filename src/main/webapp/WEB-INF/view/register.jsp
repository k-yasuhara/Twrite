<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Twrite 新規入力画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/top.style.css">
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
					<li class="nav-item"><a class="nav-link disabled">※救急相談OP01※</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="logout">ログアウト</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- end of ヘッダー -->

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
						<li class="nav-item"><a class="nav-link" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">全ての記録</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">未承認</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">差し戻し</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <svg
									xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
                                <path
										d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783" />
                            </svg> <span class="ms-2">承認済み</span>
						</a></li>
					</ul>
				</div>
			</nav>
			<!-- End of サイドバー -->

			<!-- main -->
			<main class="col-10 col-sm px-4 py-4">
				<c:if test="${not empty errorMsg}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${errorMsg}" />
					</div>
				</c:if>
				<!-- topic -->
				<h1 class="h2 fw-bold">◆新規入力</h1>

				<form method="post">
					<!-- 相談開始時間 -->
					<div class="row my-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="start_at" class="col col-form-label ms-3 fw-bold">相談開始時間</label>
								<p class="col-form-label ms-3" style="color: red; width: 150px;">(必須)</p>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="col-sm-auto ms-3 py-2">
							<input type="datetime-local" class="form-control" id="start_at"
								name="start_at" value="${start}" required>
						</div>
					</div>
					<!-- end of 相談開始時間  -->

					<!-- スタッフ名 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="starff_id" class="col col-form-label ms-3 fw-bold">スタッフ名</label>
								<p class="col-form-label ms-3" style="color: red; width: 150px;">(必須)</p>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="col-sm-auto ms-3 py-2">
							<select class="form-select" name="staff_id" id="staff_id"
								required>
								<c:if test="${empty staffId}">
									<option selected disabled>選択してください</option>
								</c:if>
								<option value="1">渡邊</option>
								<option value="2">高比良</option>
								<option value="3">松井</option>
							</select>
							<c:if test="${not empty staffId}">
									<c:forEach var="i" begin="1" end="3">
										<c:if test="${staffId == i}">
											<script>
													var select = document
															.getElementById("staff_id");
													select.options[${i-1}].selected = true;
												</script>
										</c:if>
									</c:forEach>
								</c:if>
						</div>
					</div>
					<!-- end of スタッフ名 -->

					<!-- 急病者続柄 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="patient_pattern"
									class="col col-form-label ms-3 fw-bold">急病者 側柄</label>
								<p class="col-form-label ms-3" style="color: red; width: 150px;">(必須)</p>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="col-sm-auto ms-3 py-2">
							<select class="form-select" name="patient_pattern"
								id="patient_pattern" required>
								<c:if test="${empty patientPattern}">
									<option selected disabled>選択してください</option>
								</c:if>
								<option value="1">本人</option>
								<option value="2">娘</option>
								<option value="3">息子</option>
								<option value="4">父</option>
								<option value="5">母</option>
								<option value="6">親戚</option>
								<option value="7">その他</option>
								<c:if test="${not empty patientPattern}">
									<c:forEach var="i" begin="1" end="7">
										<c:if test="${patientPattern == i}">
											<script>
													var select = document
															.getElementById("patient_pattern");
													select.options[${i-1}].selected = true;
												</script>
										</c:if>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<!-- end of 急病者続柄 -->

					<!-- 症状種別 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label class="col-form-label ms-3 fw-bold">症状種別</label>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="col-sm-auto form-check ms-3 py-2">
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="1"
									name="symptoms" id="fever"> <label for="fever"
									class="form-check-label">熱発</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="2"
									name="symptoms" id="cough"> <label for="cough"
									class="form-check-label">咳嗽</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="3"
									name="symptoms" id="stuffy_nose"> <label
									for="stuffy_nose" class="form-check-label">鼻汁・鼻閉</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="4"
									name="symptoms" id="sore_throat"> <label
									for="sore_throat" class="form-check-label">咽頭痛</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="5"
									name="symptoms" id="throat_discomform"> <label
									for="throat_discomform" class="form-check-label">咽頭違和感</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="6"
									name="symptoms" id="stomachache"> <label
									for="stomachache" class="form-check-label">腹痛</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="7"
									name="symptoms" id="diarrhea"> <label for="diarrhea"
									class="form-check-label">下痢</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="8"
									name="symptoms" id="nausea_vomiting"> <label
									for="nausea_vomiting" class="form-check-label">嘔気・嘔吐</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="9"
									name="symptoms" id="burn"> <label for="burn"
									class="form-check-label">熱傷</label>
							</div>
							<div class="form-check mb-2">
								<input class="form-check-input" type="checkbox" value="10"
									name="symptoms" id="bruise"> <label for="bruise"
									class="form-check-label">打撲</label>
							</div>
						</div>
					</div>
					<!-- end of 症状種別 -->

					<!-- 相談内容 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="consultation"
									class="col col-form-label ms-3 fw-bold">相談内容</label>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="ms-3 py-2" style="width: 500px;">
							<textarea class="form-control" name="consultation"
								id="consultation" rows="3" value="${consContent}"></textarea>
						</div>
					</div>
					<!-- end of 相談内容 -->

					<!-- 対応内容 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="response" class="col col-form-label ms-3 fw-bold">対応内容</label>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="ms-3 py-2" style="width: 500px;">
							<textarea class="form-control" name="response" id="response"
								rows="3" value="${respContent}"></textarea>
						</div>
					</div>
					<!-- end of 対応内容 -->

					<!-- 相談終了時間 -->
					<div class="row mb-3">
						<!-- label枠 -->
						<div class="col-sm-2 ms-3 py-2 bg-body-secondary">
							<div class="row">
								<label for="end_at" class="col col-form-label ms-3 fw-bold">相談終了時間</label>
								<p class="col-form-label ms-3" style="color: red; width: 150px;">(必須)</p>
							</div>
						</div>
						<!-- end of label枠 -->
						<div class="col-sm-auto ms-3 py-2">
							<input type="datetime-local" class="form-control" id="end_at"
								name="end_at" value="${end}" required>
						</div>
					</div>
					<!-- end of 相談終了時間  -->

					<button type="submit" class="btn btn-primary ms-1 mb-3">確定</button>
				</form>

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
