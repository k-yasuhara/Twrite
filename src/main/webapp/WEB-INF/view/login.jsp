<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ログイン画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<div class="login-container">
		<img src="<%=request.getContextPath()%>/images/Twriteロゴ.png"
			alt="logo" width="200" style="display: block; margin: auto;"> <br>

		<!-- アラートメッセージ -->
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger" role="alert">
				ログインに失敗しました。<br>IDまたはパスワードを確認してください。
			</div>
		</c:if>
		<!-- アラートメッセージ -->

		<form action="" method="POST">
			<div class="form-group">
				<label for="login_id">ログインID</label> 
				<input class="form-control" type="text" name="loginId"aria-label="default input example" value="<c:out value="${loginId}" />" required>
			</div>
			<div class="form-group">
				<label for="password">パスワード</label>
				<input class="form-control" type="password" name="loginPass" aria-label="default input example" required>
			</div>
			<button type="submit" class="login-btn">ログイン</button>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>